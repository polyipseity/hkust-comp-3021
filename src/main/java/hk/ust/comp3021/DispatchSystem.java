package hk.ust.comp3021;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import hk.ust.comp3021.rank.CustomerPriorityRank;
import hk.ust.comp3021.rank.OrderCreateTimeRank;
import hk.ust.comp3021.rank.RestaurantToCustomerDistanceRank;
import hk.ust.comp3021.rank.RiderMonthTaskCountRank;
import hk.ust.comp3021.rank.RiderRatingRank;
import hk.ust.comp3021.rank.RiderToRestaurantRank;

public class DispatchSystem {

    /// The singleton you will use in the project.
    private static volatile DispatchSystem dispatchSystem;

    /// The field represents the current time stamp, we assume the current time
    /// stamp is 3600 seconds.
    private Long currentTimestamp = 3600L;

    /// The list stores the dishes parsed from the file.
    private List<Dish> availableDishes;

    /// The list stores the orders parsed from the file.
    private List<Order> availableOrders;

    /// The list stores the orders that is dispatched this time, and the orders
    /// should have a non-null rider field and calculated estimated time.
    private List<Order> dispatchedOrders;

    /// Task 1: Implement the constructor of the singleton pattern for the
    /// DispatchSystem class.
    /// Hint: Check if the dispatchSystem is null or not null, skip it when not
    /// null. Initialize the fields.

    private DispatchSystem() {
        if (dispatchSystem != null) {
            throw new IllegalStateException("`DispatchSystem` has already been created before");
        }
        this.availableDishes = new ArrayList<>();
        this.availableOrders = new ArrayList<>();
        this.dispatchedOrders = new ArrayList<>();
    }

    /// Task 1: Implement the getInstance() method for the singleton pattern.
    /// Hint: Check if the dispatchSystem is null or not null and create a new
    /// instance here.
    public static DispatchSystem getInstance() {
        if (dispatchSystem == null) {
            dispatchSystem = new DispatchSystem();
        }
        return dispatchSystem;
    }

    public Dish getDishById(Long id) {
        for (Dish dish : availableDishes) {
            if (dish.id == id)
                return dish;
        }
        return null;
    }

    public Boolean checkDishesInRestaurant(Restaurant restaurant, Long[] dishIds) {
        return Stream.of(dishIds).allMatch(id -> {
            if (id == null)
                return false;
            Dish dish = getDishById(id);
            if (dish == null)
                return false;
            return dish.restaurantId == restaurant.id;
        });
    }

    /// Task 2: Implement the parseAccounts() method to parse the accounts from the
    /// file.
    /// Hint: Do not forget to register the accounts into the static manager.
    public void parseAccounts(String fileName) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            // Read the file and parse the accounts.
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.isEmpty()) {
                    continue;
                }

                String[] fields = line.split(",");
                if (fields.length < 2) {
                    throw new IOException("The account file is not well formatted!");
                }

                for (int i = 0; i < fields.length; i++) {
                    fields[i] = fields[i].trim();
                }

                String accountType = fields[1];
                long id = Long.parseLong(fields[0]);
                String name = fields[2];
                String contactNumber = fields[3];
                String[] locationStrings = fields[4].substring(1, fields[4].length() - 1).split(" ");
                Location location = new Location(Double.parseDouble(locationStrings[0]),
                        Double.parseDouble(locationStrings[1]));

                Account account = null;
                switch (accountType) {
                    case "CUSTOMER":
                        account = new Customer(id, name, contactNumber, location, Integer.parseInt(fields[5]),
                                fields[6], fields[7]);
                        break;
                    case "RESTAURANT":
                        account = new Restaurant(id, name, contactNumber, location, fields[5], fields[6]);
                        break;
                    case "RIDER":
                        account = new Rider(id, name, contactNumber, location, fields[5], Integer.parseInt(fields[6]),
                                Double.parseDouble(fields[7]), Integer.parseInt(fields[8]));
                        break;
                    default:
                        throw new IllegalArgumentException("File with invalid account type");
                }
                account.register();
            }
        }
    }

    /// Task 3: Implement the parseDishes() method to parse the dishes from the
    /// file.
    /// Hint: Do not forget to add the dishes to the corresponding restaurant and
    /// the availableDishes list.
    public void parseDishes(String fileName) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            // Read the file and parse the dishes.
            String line;
            while ((line = bufferedReader.readLine()) != null && !line.isEmpty()) {
                String[] fields = line.split(",");
                if (fields.length < 2) {
                    throw new IOException("The dish file is not well formatted!");
                }

                for (int i = 0; i < fields.length; i++) {
                    fields[i] = fields[i].trim();
                }

                long id = Long.parseLong(fields[0]);
                String name = fields[1];
                String desc = fields[2];
                BigDecimal price = new BigDecimal(fields[3]);
                long restaurantId = Long.parseLong(fields[4]);

                Dish dish = new Dish(id, name, desc, price, restaurantId);
                availableDishes.add(dish);
                Restaurant.getRestaurantById(restaurantId).addDish(dish);
            }
        }
    }

    /// Task 4: Implement the parseOrders() method to parse the orders from the
    /// file.
    /// Hint: Do not forget to add the order to the availableOrders list and check
    /// if the dishes ordered are in the same restaurant, skip the record if not.
    /// You can use getDishById(), checkDishesInRestaurant(), and etc. here.
    public void parseOrders(String fileName) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            // Read the file and parse the orders.
            String line;
            while ((line = bufferedReader.readLine()) != null && !line.isEmpty()) {
                String[] fields = line.split(",");
                if (fields.length < 2) {
                    throw new IOException("The order file is not well formatted!");
                }

                for (int i = 0; i < fields.length; i++) {
                    fields[i] = fields[i].trim();
                }

                long id = Long.parseLong(fields[0]);
                int status = Integer.parseInt(fields[1]);
                Restaurant restaurant = Restaurant.getRestaurantById(Long.parseLong(fields[2]));
                Customer customer = Customer.getCustomerById(Long.parseLong(fields[3]));
                long createTime = Long.parseLong(fields[4]);
                boolean isPayed = Integer.parseInt(fields[5]) != 0;
                String[] orderedDishesStrings = fields[6].substring(1, fields[6].length() - 1).split(" ");
                Long[] orderedDishIds = Stream.of(orderedDishesStrings).map(Long::parseLong)
                        .toArray(Long[]::new);
                if (!checkDishesInRestaurant(restaurant, orderedDishIds)) {
                    continue;
                }
                List<Dish> orderedDishes = Stream.of(orderedDishIds).map(this::getDishById).toList();
                Rider rider = "NA".equals(fields[7]) ? null : Rider.getRiderById(Long.parseLong(fields[7]));

                Order order = new Order(id, status, restaurant,
                        customer, createTime, isPayed, orderedDishes,
                        rider);
                availableOrders.add(order);
            }
        }
    }

    /// Task 5: Implement the getAvailablePendingOrders() method to get the
    /// available pending orders.
    /// Hint: The available pending orders should have the status of PENDING_ORDER,
    /// is payed, and the rider is null.
    public List<Order> getAvailablePendingOrders() {
        return new ArrayList<>(availableOrders.stream()
                .filter(order -> order.getStatus() == Constants.PENDING_ORDER && order.isPayed
                        && order.getRider() == null)
                .toList());
    }

    /// Task 6: Implement the getRankedPendingOrders() method to rank the pending
    /// orders.
    /// Hint: Use the comparators you defined before, and sort the pending orders in
    /// order of the customer type (Top priority), order creation time (Second
    /// priority), and restaurant to customer distance (Least priority).
    public List<Order> getRankedPendingOrders(List<Order> pendingOrders) {
        List<Order> ret = new ArrayList<>(pendingOrders);
        ret.sort(new CustomerPriorityRank().thenComparing(new OrderCreateTimeRank())
                .thenComparing(new RestaurantToCustomerDistanceRank()));
        return ret;
    }

    /// Task 7: Implement the getAvailableRiders() method to get the available
    /// riders to dispatch.
    /// Hint: The available riders should have the status of RIDER_ONLINE_ORDER.
    public List<Rider> getAvailableRiders() {
        return new ArrayList<>(Account.getAccountManager().getRegisteredRiders().stream()
                .filter(rider -> rider.getStatus() == Constants.RIDER_ONLINE_ORDER).toList());
    }

    /// Task 8: Implement the matchTheBestTask() method to choose the best rider for
    /// the order.
    /// Hint: The best rider should have the highest rank ranked in order of the
    /// distance between the rider and the restaurant (Top priority), the rider's
    /// user rating (Second priority), and the rider's month task count (Least
    /// priority).
    /// Use the comparators you defined before, you will also use the Task class
    /// here and the availableRiders here should be the currently available riders.
    public Task matchTheBestTask(Order order, List<Rider> availableRiders) {
        return availableRiders.stream().map(rider -> new Task(order, rider))
                .sorted(new RiderToRestaurantRank().thenComparing(new RiderRatingRank())
                        .thenComparing(new RiderMonthTaskCountRank()))
                .findFirst().orElseThrow(() -> new IllegalArgumentException("No available riders"));
    }

    /// Task 9: Implement the dispatchFirstRound() method to dispatch the first
    /// round of orders.
    /// Hint: The strategy is that we assign the best rider to the orders ranked one
    /// by one until the orders or riders list is empty.
    /// Do not forget to 1. remove the dispatched rider every iteration, 2. change
    /// the status of the order and the rider after the order is dispatched, and 3.
    /// calculate the estimated time for the order.
    public void dispatchFirstRound() {
        List<Order> orders = getRankedPendingOrders(getAvailableOrders());
        List<Rider> riders = getAvailableRiders();
        while (!orders.isEmpty() && !riders.isEmpty()) {
            Order order = orders.remove(0);
            Task task = matchTheBestTask(order, riders);
            Rider rider = task.rider;

            riders.remove(rider);
            rider.setStatus(Constants.RIDER_DELIVERING);
            order.setRider(rider);
            order.setStatus(Constants.DISPATCHED_ORDER);
            order.setEstimatedTime(order.calculateEstimatedTime());

            dispatchedOrders.add(order);
        }
    }

    /// Do not modify the method. You should use the method to output orders for us
    /// to check the correctness of your implementation.
    public void writeOrders(String fileName, List<Order> orders) throws IOException {
        List<Order> orderedOrders = orders.stream().sorted(new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                return o1.getId().compareTo(o2.getId());
            }
        }).toList();

        // Write the dispatched orders to the file.
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            for (Order order : orderedOrders) {
                bufferedWriter.write(order.getId() + ", " + order.getStatus() + ", " + order.getRestaurant() + ", "
                        + order.getCustomer() + ", " + order.getCreateTime() + ", " + order.getIsPayed() + ", " +
                        order.getOrderedDishes() + ", " + order.getRider() + ", "
                        + String.format("%.4f", order.getEstimatedTime()) + "\n");
            }
        }
    }

    /// Do not modify the method.
    public void writeAccounts(String fileName, List<Account> accounts) throws IOException {
        List<Account> orderedAccounts = accounts.stream().sorted(new Comparator<Account>() {
            @Override
            public int compare(Account o1, Account o2) {
                return o1.getId().compareTo(o2.getId());
            }
        }).toList();

        // Write the dispatched orders to the file.
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            for (Account account : orderedAccounts) {
                bufferedWriter.write(account.toString() + "\n");
            }
        }
    }

    /// Do not modify the method.
    public void writeDishes(String fileName, List<Dish> dishes) throws IOException {
        List<Dish> orderedDishes = dishes.stream().sorted(new Comparator<Dish>() {
            @Override
            public int compare(Dish o1, Dish o2) {
                return o1.getId().compareTo(o2.getId());
            }
        }).toList();

        // Write the dispatched orders to the file.
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            for (Dish dish : orderedDishes) {
                bufferedWriter.write(dish.getId() + ", " + dish.getName() + ", " + dish.getDesc() + ", "
                        + dish.getPrice() + ", " + dish.getRestaurantId() + "\n");
            }
        }
    }

    /// Task 10: Implement the getTimeoutDispatchedOrders() method to get the
    /// timeout dispatched orders.
    /// Hint: Do not forget to take the current time stamp into consideration.
    public List<Order> getTimeoutDispatchedOrders() {
        return new ArrayList<>(dispatchedOrders.stream().filter(
                order -> order.getEstimatedTime() > Constants.DELIVERY_TIME_LIMIT)
                .toList());
    }

    /// Do not modify the method.
    public List<Order> getAvailableOrders() {
        return availableOrders;
    }

    /// Do not modify the method.
    public List<Order> getDispatchedOrders() {
        return dispatchedOrders;
    }

    /// Do not modify the method.
    public List<Account> getAccounts() {
        Account.AccountManager manager = Account.getAccountManager();
        return manager.getRegisteredAccounts();
    }

    /// Do not modify the method.
    public List<Dish> getDishes() {
        return availableDishes;
    }

    /// Finish the main method to test your implementation.a
    public static void main(String[] args) {
        DispatchSystem system = getInstance();
        try {
            system.parseAccounts("Accounts.txt");
            system.parseDishes("Dishes.txt");
            system.parseOrders("Orders.txt");
            system.writeOrders("availableOrders.txt", system.availableOrders);

            system.dispatchFirstRound();

            system.writeOrders("firstRoundDispatchedOrders.txt", system.dispatchedOrders);
            List<Order> timeoutOrders = system.getTimeoutDispatchedOrders();

            system.writeOrders("timeoutDispatchedOrders.txt", timeoutOrders);

        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }

    public Long getCurrentTimestamp() {
        return currentTimestamp;
    }
}
