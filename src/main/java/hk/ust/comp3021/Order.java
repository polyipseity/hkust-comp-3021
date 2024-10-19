package hk.ust.comp3021;

public class Order {
  private Long id;

  private Integer status;

  private Restaurant restaurant;

  private Customer customer;

  private Long createTime;

  private Boolean isPayed;

  private List<Dish> orderedDishes;

  private Rider rider;

  private Double estimatedTime;

  public double calculateEstimatedTime() {
  }
}
