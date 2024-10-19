package hk.ust.comp3021;

public class Rider extends Account {
    public final String gender;

    public final Integer status;

    public final Double userRating;

    public final Integer monthTaskCount;

    public Rider(long id, String name, String contactNumber, Location location, String gender, int status,
            double userRating, int monthTaskCount) {
        super(id, "RIDER", name, contactNumber, location);
        this.gender = gender;
        this.status = status;
        this.userRating = userRating;
        this.monthTaskCount = monthTaskCount;
    }

    @Override
    public void register() {
        Account.getAccountManager().addRider(this);
    }

    public static Rider getRiderById(Long id) {
        return getAccountManager().getRiderById(id);
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
