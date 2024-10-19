package hk.ust.comp3021.rank;

import hk.ust.comp3021.Order;

public class RestaurantToCustomerDistanceRank implements PendingOrderRank {
  @Override
  public int compare(Order source, Order target) {
    return -Double.compare(source.customer.location.distanceTo(source.restaurant.location),
        target.customer.location.distanceTo(target.restaurant.location));
  }
}
