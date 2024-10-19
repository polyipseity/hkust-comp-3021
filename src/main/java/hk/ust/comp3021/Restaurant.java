package hk.ust.comp3021;

import java.util.List;

public class Restaurant extends Account {
    private String district;

    private String street;

    private List<Dish> dishes;

    @Override
    public void register() {
    }

    public void addDish(Dish dish) {
    }

    public static Restaurant getRestaurantById(Long id) {
    }

    /// Do not modify this method.
    @Override
    public String toString() {
        List<Long> dishIds = new LinkedList<>(dishes.stream().map(Dish::getId).toList());
        dishIds.sort(Long::compareTo);
        return "Restaurant{" +
                "id=" + id +
                ", accountType='" + accountType + '\'' +
                ", name='" + name + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", location=" + location +
                ", district='" + district + '\'' +
                ", street='" + street + '\'' +
                ", dishIds='" + dishIds + '\'' +
                '}';
    }

}
