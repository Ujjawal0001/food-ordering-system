package repository.orderRepository;

import model.FoodItem;
import model.Order;

import java.util.ArrayList;

public interface OrderRepository {
    void saveOrder(Order order);
    boolean isOrderExists(String id);
    ArrayList<FoodItem> getOrders(String customerId);
}
