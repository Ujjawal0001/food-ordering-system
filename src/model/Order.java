package model;

import repository.foodItemRepository.impl.FoodItemRepositoryImpl;

import java.util.ArrayList;

public class Order {
    private String id;
    private String customerId;
    private String restaurantId;
    private ArrayList<FoodItem> foodItems;
    private long totalPrice;
    private String status="PENDING";
    private static Order instance;
    public Order(String customerId,  String id, String restaurantId, String status, long totalPrice ,ArrayList<FoodItem> foodItems ) {
        this.customerId = customerId;
        this.id = id;
        this.restaurantId = restaurantId;
        this.status = status;
        this.totalPrice = totalPrice;
        this.foodItems=foodItems;
    }


    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public ArrayList<FoodItem> getFoodItems() {
        return foodItems;
    }

    public void setFoodItems(ArrayList<FoodItem> foodItems) {
        this.foodItems = foodItems;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(long totalPrice) {
        this.totalPrice = totalPrice;
    }
}

