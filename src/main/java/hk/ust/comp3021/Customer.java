package hk.ust.comp3021;

public class Customer extends Account {
    private Integer customerType;

    private String gender;

    private String email;

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
