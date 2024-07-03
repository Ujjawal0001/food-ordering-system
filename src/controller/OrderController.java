package controller;

import message.Message;
import model.FoodItem;
import model.Order;
import service.orderService.impl.OrderServiceImpl;

import java.util.ArrayList;

public class OrderController {
    private static OrderController instance;
    public static synchronized OrderController getInstance(){
        if(instance==null){
            instance=new OrderController();
        }
        return instance;
    }
    private OrderServiceImpl orderService = OrderServiceImpl.getInstance();
    public   boolean placeOrder(String customerId, String id, String restaurantId, String status, long totalPrice , ArrayList<FoodItem> foodItems, Message msg){
        return orderService.placeOrder(customerId, id, restaurantId, status, totalPrice, foodItems, msg);
    }

    public Order getOrdersByCustomerId(String customerId){
        return orderService.getOrdersByCustomerId(customerId);
    }
    public ArrayList<Order> getOrderHistory(String customerId){
        return orderService.getOrderHistory(customerId);
    }
    public ArrayList<Order>  getOrdersByRestaurantId(String restaurantId){
        return orderService.getOrdersByRestaurantId(restaurantId);
    }

    public  boolean updateOrderStatus(String orderId, String status ,Message msg){
        return orderService.updateOrderStatus(orderId, status, msg);
    }
}


