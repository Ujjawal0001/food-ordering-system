package controller;

import uiClient.Message;
import model.FoodItem;
import service.foodItemService.impl.FoodItemServiceImpl;

import java.util.ArrayList;

public class FoodItemController {
    private static FoodItemController instance;
    public static synchronized FoodItemController getInstance(){
        if(instance==null){
            instance=new FoodItemController();
        }
        return instance;
    }
    private FoodItemServiceImpl foodItemService = FoodItemServiceImpl.getInstance();
    public boolean addFoodItem(String restaurantId , String name, double price, String id, String description , Message msg){
        return foodItemService.addFoodItem(restaurantId, name, price, id, description, msg);
    }
    public FoodItem getFoodItemById(String restaurantId,String foodItemId ,Message msg){
        return foodItemService.getFoodItemById(restaurantId, foodItemId, msg);
    }
   public boolean updateFoodItemByName(String foodItemId,String name, Message msg ){
        return foodItemService.updateFoodItemByName(foodItemId,name,msg);
    }

   public  boolean updateFoodItemByPrice(String foodItemId,double price, Message msg ){
        return foodItemService.updateFoodItemByPrice(foodItemId,price,msg);
    }

    public boolean updateFoodItemDescription(String foodItemId,String description, Message msg ){
        return  foodItemService.updateFoodItemDescription(foodItemId,description,msg);
    }

    public boolean updateFoodItem(String foodItemId,String name, double price, String description , Message msg ){
        return foodItemService.updateFoodItem(foodItemId, name, price, description, msg);
    }
    public boolean updateFoodItemAvailability(String restaurantId, String foodItemId,boolean availability, Message msg){
        return foodItemService.updateFoodItemAvailability(restaurantId, foodItemId, availability, msg);
    }
    public  boolean deleteFoodItem(String foodItemId , Message msg){
        return foodItemService.deleteFoodItem(foodItemId, msg);
    }

    public ArrayList<FoodItem> getFoodItemsByRestaurantId(String restaurantId){
        return foodItemService.getFoodItemsByRestaurantId(restaurantId);
    }
}
