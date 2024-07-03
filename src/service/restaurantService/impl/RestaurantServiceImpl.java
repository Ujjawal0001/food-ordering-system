package service.restaurantService.impl;

import model.User;
import uiClient.Message;
import model.Restaurant;
import repository.restaurantRepository.impl.RestaurantRepositoryImpl;
import service.restaurantService.RestaurantService;
import service.userService.impl.UserServiceImpl;

import java.util.ArrayList;

public class RestaurantServiceImpl implements RestaurantService {
    private RestaurantRepositoryImpl restaurantRepository = RestaurantRepositoryImpl.getInstance();
    private UserServiceImpl userService=UserServiceImpl.getInstance();
    private static RestaurantServiceImpl instance;
    public static synchronized RestaurantServiceImpl getInstance(){
        if(instance==null){
            instance=new RestaurantServiceImpl();

        }
        return instance;
    }


    public boolean createRestaurant(String name , String id , String ownerId , String address , String phoneNumber , Message msg) {
//        Restaurant isRestaurant=restaurantRepository.findRestaurantByOwnerId(ownerId).
        Restaurant isRestaurantExists=  restaurantRepository.findRestaurantById(id);
        if(isRestaurantExists==null){
            Restaurant newRestaurant = new Restaurant(name,id,ownerId,address,phoneNumber);
            newRestaurant.setName(name);
            newRestaurant.setAddress(address);
            newRestaurant.setPhoneNumber(phoneNumber);
            newRestaurant.setOwnerId(ownerId);
            newRestaurant.setId(id);

            restaurantRepository.saveRestaurant(newRestaurant);
            User user = userService.userProfile(ownerId);
            if (user != null) {
                user.getRestaurants().add(newRestaurant);
            }
            return true;
        }
        msg.setMessage("Restaurant already exists ");
        return false;
    }


    public boolean updateRestaurantName(String restaurantId, String name , Message msg ){
        Restaurant restaurant=  restaurantRepository.findRestaurantById(restaurantId);
        if(restaurant!=null) {
            restaurant.setName(name);
            return true;
        }
        msg.setMessage("Restaurant doesn't exist");
        return false;
    }

    public boolean updateRestaurantPhoneNumber(String restaurantId, String phoneNumber , Message msg ){
        Restaurant restaurant=  restaurantRepository.findRestaurantById(restaurantId);
        if(restaurant!=null) {
            restaurant.setPhoneNumber(phoneNumber);
            return true;
        }
        msg.setMessage("Restaurant doesn't exist");
        return false;
    }


    public boolean updateRestaurantAddress(String restaurantId, String address , Message msg ){
        Restaurant restaurant=  restaurantRepository.findRestaurantById(restaurantId);
        if(restaurant!=null) {
            restaurant.setAddress(address);
            return true;
        }
        msg.setMessage("Restaurant doesn't exist");
        return false;
    }

    public boolean updateRestaurant(String restaurantId, String name ,String address , String phoneNumber ,Message msg ){
        Restaurant restaurant=  restaurantRepository.findRestaurantById(restaurantId);
        if(restaurant!=null) {
            restaurant.setName(name);
            restaurant.setAddress(address);
            restaurant.setPhoneNumber(phoneNumber);
            return true;
        }
        msg.setMessage("Restaurant doesn't exist");
        return false;
    }


    public boolean deleteRestaurant(String restaurantId , Message msg){
        Restaurant restaurant=  restaurantRepository.findRestaurantById(restaurantId);
        if(restaurant!=null) {
           String user1= restaurant.getOwnerId();
            User user = userService.userProfile(user1);
            if (user != null) {
                user.getRestaurants().remove(restaurant);
            }
            restaurantRepository.deleteRestaurant(restaurantId);
            return true;
        }
        msg.setMessage("Restaurant doesn't exist");
        return false;
    }

    public  ArrayList<Restaurant> getRestaurantsByOwnerId(String ownerId , Message msg){
        ArrayList<Restaurant> isRestaurant =  restaurantRepository.findRestaurantByOwnerId(ownerId);
        if(isRestaurant!=null){
            return restaurantRepository.findRestaurantByOwnerId(ownerId);
        }
        msg.setMessage("Restaurant doesn't exist for the given owner id");
        return null;
    }
    public Restaurant isRestaurant(String restaurantId){
        Restaurant restaurant=restaurantRepository.findRestaurantById(restaurantId);
        if(restaurant!=null){
            return restaurant;
        }
        return null;
    }

    public ArrayList<Restaurant> getAllRestaurants(){
        return restaurantRepository.getAllRestaurants();
    }
}
