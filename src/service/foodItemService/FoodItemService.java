package service.foodItemService;

import uiClient.Message;
import model.FoodItem;

import java.util.ArrayList;

public interface FoodItemService {
    boolean addFoodItem(String restaurantId , String name, double price, String id, String description , Message msg);
    boolean updateFoodItemByName(String foodItemId,String name, Message msg );
    boolean updateFoodItemByPrice(String foodItemId,double price, Message msg );
    boolean updateFoodItemDescription(String foodItemId,String description, Message msg );
    boolean updateFoodItem(String foodItemId,String name, double price, String description , Message msg );
    boolean deleteFoodItem(String foodItemId , Message msg);
    ArrayList<FoodItem> getFoodItemsByRestaurantId(String restaurantId);
}
