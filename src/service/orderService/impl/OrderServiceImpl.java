package service.orderService.impl;

import message.Message;
import model.FoodItem;
import model.Order;
import model.Restaurant;
import repository.orderRepository.impl.OrderRepositoryImpl;
import service.orderService.OrderService;
import service.restaurantService.impl.RestaurantServiceImpl;

import java.util.ArrayList;

public class OrderServiceImpl implements OrderService {

    private static OrderServiceImpl instance;
    public static synchronized OrderServiceImpl getInstance(){
        if(instance==null){
            instance=new OrderServiceImpl();

        }
        return instance;
    }

    private OrderRepositoryImpl orderRepository = OrderRepositoryImpl.getInstance();
    private RestaurantServiceImpl restaurantService = RestaurantServiceImpl.getInstance();


   public boolean placeOrder(String customerId, String id, String restaurantId, String status, long totalPrice , ArrayList<FoodItem> foodItems,Message msg){
       Restaurant restaurant=restaurantService.isRestaurant(restaurantId);
       if(restaurant!=null){
           boolean isOrder=orderRepository.isOrderExists(id);
           if(!isOrder) {
               Order order = new Order(customerId, id, restaurantId, status, totalPrice,foodItems);
               orderRepository.saveOrder(order);
               order.setStatus("PENDING");
               restaurant.getOrders().add(order);
               return  true;
           }
           else{
               msg.setMessage("the order is already placed");
               return false;
           }
       }
       else{
           msg.setMessage("the restaurant id is wrong");
           return false;
       }
   }
   public Order  getOrdersByCustomerId(String customerId){
      Order order = orderRepository.findOrderById(customerId);
      if(order!=null){
          return order;
      }
        return null;
   }
    public ArrayList<Order >getOrdersByRestaurantId(String restaurantId){
        Restaurant restaurant = restaurantService.isRestaurant(restaurantId);
        if(restaurant!=null){
          return  restaurant.getOrders();
        }
        return null;
    }

    public boolean updateOrderStatus(String orderId, String status ,Message msg) {
       Order order=orderRepository.findOrderById(orderId);
       if(order!=null){
           order.setStatus(status);
           return true;
       }
       msg.setMessage("Order doesn't exists");
       return false;
    }
}
