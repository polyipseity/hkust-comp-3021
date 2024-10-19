package hk.ust.comp3021;

import java.util.ArrayList;
import java.util.List;

public class Order {
  public final Long id;

  public final Integer status;

  public final Restaurant restaurant;

  public final Customer customer;

  public final Long createTime;

  public final Boolean isPayed;

  public final List<Dish> orderedDishes;

  public final Rider rider;

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
    this.estimatedTime = null;
  }

  public double calculateEstimatedTime() {
  }
}
