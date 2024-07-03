package repository.orderRepository.impl;

import model.FoodItem;
import model.Order;
import repository.orderRepository.OrderRepository;
import service.orderService.impl.OrderServiceImpl;

import java.util.ArrayList;

public class OrderRepositoryImpl implements OrderRepository {
    private static OrderRepositoryImpl instance;
    public static synchronized OrderRepositoryImpl getInstance(){
        if(instance==null){
            instance=new OrderRepositoryImpl();

        }
        return instance;
    }
    private ArrayList<Order> orders = new ArrayList<>();
    public void saveOrder(Order order){
        orders.add(order);
    }

    public boolean isOrderExists(String id){
        for(Order order : orders){
            if(order.getId().equals(id)){
                return true;
            }
        }
        return false;
    }

    public Order findOrderById(String id){
        for(Order order : orders){
            if(order.getId().equals(id)){
                return order;
            }
        }
        return null;
    }

    public ArrayList<FoodItem> getOrders(String customerId){
        for(Order order : orders){
            if(order.getCustomerId().equals(customerId)){
                return order.getFoodItems();
            }
        }
        return null;
    }
}
