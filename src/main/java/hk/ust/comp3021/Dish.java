package hk.ust.comp3021;

import java.math.BigDecimal;

public class Dish {
  private final Long id;

  private final String name;

  private final String desc;

  private final BigDecimal price;

  private final Long restaurantId;

  public Dish(long id, String name, String desc, BigDecimal price, long restaurantId) {
    this.id = id;
    this.name = name;
    this.desc = desc;
    this.price = price;
    this.restaurantId = restaurantId;
  }
}
