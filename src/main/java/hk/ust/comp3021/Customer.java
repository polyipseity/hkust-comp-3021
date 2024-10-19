package hk.ust.comp3021;

public class Customer extends Account {
    public final Integer customerType;

    public final String gender;

    public final String email;

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
        return Account.getAccountManager().getCustomerById(id);
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

    public int getCustomerType() {
      return customerType;
    }

    public String getGender() {
      return gender;
    }

    public String getEmail() {
      return email;
    }
}
