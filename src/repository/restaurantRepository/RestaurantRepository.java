package repository.restaurantRepository;

import model.Restaurant;

import java.util.ArrayList;

public interface RestaurantRepository {
    void saveRestaurant(Restaurant restaurant);
    ArrayList< Restaurant>  findRestaurantByOwnerId(String ownerId);
    Restaurant findRestaurantById(String id);
    void deleteRestaurant(String restaurantId);
}
