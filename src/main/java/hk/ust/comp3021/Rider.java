package hk.ust.comp3021;

public class Rider extends Account {
    private String gender;

    private Integer status;

    private Double userRating;

    private Integer monthTaskCount;

    @Override
    public void register() {
        Account.getAccountManager().addRider(this);
    }

    public static Rider getRiderById(Long id) {
    }

    /// Do not modify this method.
    @Override
    public String toString() {
        return "Rider{" +
                "id=" + id +
                ", accountType='" + accountType + '\'' +
                ", name='" + name + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", location=" + location +
                ", gender='" + gender + '\'' +
                ", status=" + status +
                ", userRating=" + userRating +
                ", monthTaskCount=" + monthTaskCount +
                '}';
    }
}
