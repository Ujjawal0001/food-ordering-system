package repository.restaurantRepository.impl;

import model.Restaurant;
import model.User;
import repository.restaurantRepository.RestaurantRepository;
import service.restaurantService.impl.RestaurantServiceImpl;

import java.util.ArrayList;

public class RestaurantRepositoryImpl implements RestaurantRepository {
    ArrayList<Restaurant> restaurants=new ArrayList<>();
    ArrayList<User> users = new ArrayList<>();
    private static RestaurantRepositoryImpl instance;
    public static synchronized RestaurantRepositoryImpl getInstance(){
        if(instance==null){
            instance=new RestaurantRepositoryImpl();

        }
        return instance;
    }

    public void saveRestaurant(Restaurant restaurant){
        restaurants.add(restaurant);
    }
    public ArrayList< Restaurant> findRestaurantByOwnerId(String ownerId) {
            for(Restaurant restaurant : restaurants){
                if(restaurant.getOwnerId().equals(ownerId)){
                    return restaurants;
                }
            }
            return null;
    }

    public Restaurant findRestaurantById(String id){
        for(Restaurant restaurant : restaurants){

            if(restaurant.getId().equals(id)){
                return restaurant;
            }
        }
        return null;
    }

    public void deleteRestaurant(String restaurantId){
        for(Restaurant restaurant : restaurants){
            if(restaurant.getId().equals(restaurantId)){
                restaurants.remove(restaurant);
                return;
            }
        }
    }
    public ArrayList<Restaurant>  getAllRestaurants(){
        return restaurants;
    }
}
