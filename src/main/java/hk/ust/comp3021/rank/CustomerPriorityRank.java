package hk.ust.comp3021.rank;

import hk.ust.comp3021.Constants;
import hk.ust.comp3021.Order;

public class CustomerPriorityRank implements PendingOrderRank {
  @Override
  public int compare(Order source, Order target) {
    // `VIP_CUSTOMER` has a smaller numeric value.
    return Integer.compare(source.customer.customerType, target.customer.customerType);
  }
}
