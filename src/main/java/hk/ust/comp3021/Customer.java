package hk.ust.comp3021;

public class Customer extends Account {
    private final Integer customerType;

    private final String gender;

    private final String email;

    public Customer(long id, String name, String contactNumber, Location location, int customerType, String gender,
            String email) {
        super(id, "CUSTOMER", name, contactNumber, location);
        this.customerType = customerType;
        this.gender = gender;
        this.email = email;
    }

    @Override
    public void register() {
        Account.getAccountManager().addCustomer(this);
    }

    public static Customer getCustomerById(Long id) {
    }

    /// Do not modify this method.
    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", accountType='" + accountType + '\'' +
                ", name='" + name + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", location=" + location +
                ", customerType=" + customerType +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}
