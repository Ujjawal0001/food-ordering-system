package service.orderService;

import message.Message;
import model.FoodItem;
import model.Order;

import java.util.ArrayList;

public interface OrderService {
    boolean placeOrder(String customerId, String id, String restaurantId, String status, long totalPrice , ArrayList<FoodItem> foodItems, Message msg);
    Order getOrdersByCustomerId(String customerId);
    ArrayList<Order>  getOrdersByRestaurantId(String restaurantId);
    boolean updateOrderStatus(String orderId, String status ,Message msg);
    public ArrayList<Order>  getOrderHistory(String customerId);
}

