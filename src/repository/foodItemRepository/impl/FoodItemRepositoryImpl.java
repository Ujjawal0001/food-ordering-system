package repository.foodItemRepository.impl;

import model.FoodItem;
import repository.foodItemRepository.FoodItemRepository;
import repository.userRepository.impl.UserRepositoryImpl;
import service.foodItemService.impl.FoodItemServiceImpl;

import java.util.ArrayList;

public class FoodItemRepositoryImpl implements FoodItemRepository {
    private static FoodItemRepositoryImpl instance;
    public static synchronized FoodItemRepositoryImpl getInstance(){
        if(instance==null){
            instance=new FoodItemRepositoryImpl();
        }
        return instance;
    }

//    private FoodItemRepositoryImpl foodItemRepository = FoodItemRepositoryImpl.getInstance();

    private ArrayList<FoodItem> foodItems = new ArrayList<>();

    public void save(FoodItem foodItem){
        foodItems.add(foodItem);
    }

    public FoodItem findFoodItemById(String foodItemId){
        for(FoodItem foodItem : foodItems){
            if(foodItem.getId().equals(foodItemId)){
                return foodItem;
            }
        }
        return null;
    }

    public void deleteFoodItem(String foodItemId){
        for(FoodItem foodItem : foodItems){
            if(foodItem.getId().equals(foodItemId)){
                foodItems.remove(foodItem);
                return ;
            }
        }
    }
}
