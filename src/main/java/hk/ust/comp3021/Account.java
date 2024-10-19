package hk.ust.comp3021;

import java.util.ArrayList;
import java.util.List;

public abstract class Account {

    public final Long id;

    public final String accountType;

    public final String name;

    public final String contactNumber;

    public final Location location;

    protected Account(long id, String accountType, String name, String contactNumber, Location location) {
        this.id = id;
        this.accountType = accountType;
        this.name = name;
        this.contactNumber = contactNumber;
        this.location = location;
    }

    /// This is where the registered accounts are stored.
    protected static class AccountManager {

        private List<Account> registeredAccounts;

        private List<Customer> registeredCustomers;

        private List<Restaurant> registeredRestaurants;

        private List<Rider> registeredRiders;

        public AccountManager() {
            this.registeredAccounts = new ArrayList<>();
            this.registeredCustomers = new ArrayList<>();
            this.registeredRestaurants = new ArrayList<>();
            this.registeredRiders = new ArrayList<>();
        }

        /// Do not modify this method.
        public List<Account> getRegisteredAccounts() {
            return registeredAccounts;
        }

        public Account getAccountById(Long id) {
            if (id == null)
                throw new NullPointerException("ID is null");
            for (Account account : registeredAccounts) {
                if (account.id == id)
                    return account;
            }
            throw new IllegalArgumentException("ID not found");
        }

        /// Hint: Do not forget to add the account to the registeredAccounts list.
        public void addCustomer(Customer customer) {
            registeredAccounts.add(customer);
            registeredCustomers.add(customer);
        }

        public Customer getCustomerById(Long id) {
            if (id == null)
                throw new NullPointerException("ID is null");
            for (Customer customer : registeredCustomers) {
                if (customer.id == id)
                    return customer;
            }
            throw new IllegalArgumentException("ID not found");
        }

        /// Hint: Do not forget to add the account to the registeredAccounts list.
        public void addRestaurant(Restaurant restaurant) {
            registeredAccounts.add(restaurant);
            registeredRestaurants.add(restaurant);
        }

        public Restaurant getRestaurantById(Long id) {
            if (id == null)
                throw new NullPointerException("ID is null");
            for (Restaurant restaurant : registeredRestaurants) {
                if (restaurant.id == id)
                    return restaurant;
            }
            throw new IllegalArgumentException("ID not found");
        }

        /// Hint: Do not forget to add the account to the registeredAccounts list.
        public void addRider(Rider rider) {
            registeredAccounts.add(rider);
            registeredRiders.add(rider);
        }

        public Rider getRiderById(Long id) {
            if (id == null)
                throw new NullPointerException("ID is null");
            for (Rider rider : registeredRiders) {
                if (rider.id == id)
                    return rider;
            }
            throw new IllegalArgumentException("ID not found");
        }

        public List<Customer> getRegisteredCustomers() {
            return registeredCustomers;
        }

        public List<Restaurant> getRegisteredRestaurants() {
            return registeredRestaurants;
        }

        public List<Rider> getRegisteredRiders() {
            return registeredRiders;
        }
    }

    protected static AccountManager accountManager = new AccountManager();

    /// Task 2: Implement the register method.
    public abstract void register();

    public static Account getAccountById(Long id) {
        return accountManager.getAccountById(id);
    }

    /// Do not modify this method.
    public static AccountManager getAccountManager() {
        return accountManager;
    }

    public Long getId() {
        return id;
    }

    public String getAccountType() {
        return accountType;
    }

    public String getName() {
        return name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public Location getLocation() {
        return location;
    }
}
