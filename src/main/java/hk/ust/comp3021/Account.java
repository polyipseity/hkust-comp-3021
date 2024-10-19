package hk.ust.comp3021;

import java.util.ArrayList;
import java.util.List;

public abstract class Account {

    protected final Long id;

    protected final String accountType;

    protected final String name;

    protected final String contactNumber;

    protected final Location location;

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
        }

        /// Hint: Do not forget to add the account to the registeredAccounts list.
        public void addCustomer(Customer customer) {
        }

        public Customer getCustomerById(Long id) {
        }

        /// Hint: Do not forget to add the account to the registeredAccounts list.
        public void addRestaurant(Restaurant restaurant) {
        }

        public Restaurant getRestaurantById(Long id) {
        }

        /// Hint: Do not forget to add the account to the registeredAccounts list.
        public void addRider(Rider rider) {
        }

        public Rider getRiderById(Long id) {
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

}
