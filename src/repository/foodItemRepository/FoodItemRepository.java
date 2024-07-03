package repository.foodItemRepository;

import model.FoodItem;

public interface FoodItemRepository {
    void save(FoodItem foodItem);
    FoodItem findFoodItemById(String foodItemId);
    void deleteFoodItem(String foodItemId);
}
