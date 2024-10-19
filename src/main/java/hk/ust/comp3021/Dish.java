package hk.ust.comp3021;

import java.math.BigDecimal;

public class Dish {
  public final Long id;

  public final String name;

  public final String desc;

  public final BigDecimal price;

  public final Long restaurantId;

  public Dish(long id, String name, String desc, BigDecimal price, long restaurantId) {
    this.id = id;
    this.name = name;
    this.desc = desc;
    this.price = price;
    this.restaurantId = restaurantId;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getDesc() {
    return desc;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public Long getRestaurantId() {
    return restaurantId;
  }

  @Override
  public String toString() {
    return "Dish(" +
        "id=" + id +
        ", name=" + name +
        ", desc=" + desc +
        ", price=" + price +
        ", restaurantId=" + restaurantId +
        ')';
  }
}
