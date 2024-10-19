package hk.ust.comp3021;

import java.util.ArrayList;
import java.util.List;

public class Order {
  public final Long id;

  private Integer status;

  public final Restaurant restaurant;

  public final Customer customer;

  public final Long createTime;

  public final Boolean isPayed;

  public final List<Dish> orderedDishes;

  private Rider rider;

  private Double estimatedTime;

  public Order(long id, int status, Restaurant restaurant, Customer customer, long createTime, boolean isPayed,
      List<Dish> orderedDishes, Rider rider) {
    this.id = id;
    this.status = status;
    this.restaurant = restaurant;
    this.customer = customer;
    this.createTime = createTime;
    this.isPayed = isPayed;
    this.orderedDishes = new ArrayList<>(orderedDishes);
    this.rider = rider;
    this.estimatedTime = Double.POSITIVE_INFINITY;
  }

  public Rider getRider() {
    return rider;
  }

  public void setRider(Rider rider) {
    this.rider = rider;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public double getEstimatedTime() {
    return estimatedTime;
  }

  public void setEstimatedTime(double estimatedTime) {
    this.estimatedTime = estimatedTime;
  }

  public double calculateEstimatedTime() {
    return (rider.location.distanceTo(restaurant.location) + restaurant.location.distanceTo(customer.location))
        / Constants.DELIVERY_SPEED;
  }

  public long getId() {
    return id;
  }

  public Restaurant getRestaurant() {
    return restaurant;
  }

  public Customer getCustomer() {
    return customer;
  }

  public long getCreateTime() {
    return createTime;
  }

  public boolean getIsPayed() {
    return isPayed;
  }

  public List<Dish> getOrderedDishes() {
    return orderedDishes;
  }
}
