package hk.ust.comp3021;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Restaurant extends Account {
    public final String district;

    public final String street;

    public final List<Dish> dishes;

    public Restaurant(long id, String name, String contactNumber, Location location, String district, String street) {
        super(id, "RESTAURANT", name, contactNumber, location);
        this.district = district;
        this.street = street;
        this.dishes = new ArrayList<>();
    }

    @Override
    public void register() {
        Account.getAccountManager().addRestaurant(this);
    }

    public void addDish(Dish dish) {
        dishes.add(dish);
    }

    public static Restaurant getRestaurantById(Long id) {
        return Account.getAccountManager().getRestaurantById(id);
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

    public String getDistrict() {
        return district;
    }

    public String getStreet() {
        return street;
    }

    public List<Dish> getDishes() {
        return dishes;
    }
}
