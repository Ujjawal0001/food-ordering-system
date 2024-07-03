package service.foodItemService.impl;

import model.Order;
import uiClient.Message;
import model.FoodItem;
import model.Restaurant;
import repository.foodItemRepository.impl.FoodItemRepositoryImpl;
import service.foodItemService.FoodItemService;
import service.restaurantService.impl.RestaurantServiceImpl;

import java.util.ArrayList;

public class FoodItemServiceImpl implements FoodItemService {

    private static FoodItemServiceImpl instance;
    public static synchronized FoodItemServiceImpl getInstance(){
        if(instance==null){
            instance=new FoodItemServiceImpl();
        }
        return instance;
    }
    private FoodItemRepositoryImpl foodItemRepository = FoodItemRepositoryImpl.getInstance();

    private RestaurantServiceImpl restaurantService = RestaurantServiceImpl.getInstance();


    public boolean addFoodItem(String restaurantId , String name, double price, String id, String description , Message msg){
        Restaurant restaurant=restaurantService.isRestaurant(restaurantId);
        if(restaurant!=null) {
            FoodItem foodItem = new FoodItem(restaurantId, name, price, id, description);
            restaurant.getFoodItems().add(foodItem);
            foodItem.setAvailability(true);
            foodItemRepository.save(foodItem);
            return true;
        }
        System.out.println("outer");
        msg.setMessage("Wrong restaurant id");
        return false;
    }
    public FoodItem getFoodItemById(String restaurantId,String foodItemId ,Message msg){
        Restaurant restaurant=restaurantService.isRestaurant(restaurantId);
        if(restaurant!=null){
            FoodItem foodItem =foodItemRepository.findFoodItemById(foodItemId);
            if(foodItem!=null){
                return foodItem;
            }
            else {
                msg.setMessage("food item not available");
                return null;
            }
        }
        msg.setMessage("Restaurant not available");
        return null;
    }
    public boolean updateFoodItemByName(String foodItemId,String name, Message msg ){
        FoodItem foodItem=foodItemRepository.findFoodItemById(foodItemId);
        if(foodItem!=null){
            foodItem.setName(name);
            return true;
        }
        msg.setMessage("Selected Fooditem is not available");
        return false;
    }

    public boolean updateFoodItemByPrice(String foodItemId,double price, Message msg ){
        FoodItem foodItem=foodItemRepository.findFoodItemById(foodItemId);
        if(foodItem!=null){
            foodItem.setPrice(price);
            return true;
        }
        msg.setMessage("Selected Fooditem is not available");
        return false;
    }

    public boolean updateFoodItemDescription(String foodItemId,String description, Message msg ){
        FoodItem foodItem=foodItemRepository.findFoodItemById(foodItemId);
        if(foodItem!=null){
            foodItem.setDescription(description);
            return true;
        }
        msg.setMessage("Selected Fooditem is not available");
        return false;
    }

    public boolean updateFoodItem(String foodItemId,String name, double price, String description , Message msg ){
        FoodItem foodItem=foodItemRepository.findFoodItemById(foodItemId);
        if(foodItem!=null){
            foodItem.setName(name);
            foodItem.setPrice(price);
            foodItem.setDescription(description);
            return true;
        }
        msg.setMessage("Selected Fooditem is not available");
        return false;
    }


    public boolean updateFoodItemAvailability(String restaurantId, String foodItemId,boolean availability, Message msg){
        Restaurant restaurant=restaurantService.isRestaurant(restaurantId);
        if(restaurant!=null){
            FoodItem foodItem =foodItemRepository.findFoodItemById(foodItemId);
            if(foodItem!=null){
                foodItem.setAvailability(availability);
                return true;
            }
            else {
                msg.setMessage("food item not available");
                return false;
            }
        }
        msg.setMessage("Restaurant not available");
        return false;
    }
    public boolean deleteFoodItem(String foodItemId , Message msg){
        FoodItem foodItem = foodItemRepository.findFoodItemById(foodItemId);
        if(foodItem!=null){
            foodItemRepository.deleteFoodItem(foodItemId);
            return true;
        }
        msg.setMessage("Selected Fooditem is not available");
        return false;
    }

    public ArrayList<FoodItem> getFoodItemsByRestaurantId(String restaurantId){
        Restaurant restaurant = restaurantService.isRestaurant(restaurantId);
        if(restaurant!=null){
            System.out.println(restaurant.getFoodItems().get(0).getName());
            return restaurant.getFoodItems();
        }
        return null;
    }




}
