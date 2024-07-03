package controller;

import uiClient.Message;
import model.Restaurant;
import service.restaurantService.impl.RestaurantServiceImpl;

import java.util.ArrayList;

public class RestaurantController {
    private static RestaurantController instance;
    public static synchronized RestaurantController getInstance(){
        if(instance==null){
            instance=new RestaurantController();
        }
        return instance;
    }
    private RestaurantServiceImpl restaurantService = RestaurantServiceImpl.getInstance();

    public  boolean createRestaurant(String name , String id , String ownerId , String address , String phoneNumber , Message msg){
        return restaurantService.createRestaurant(name, id, ownerId, address, phoneNumber, msg);
    }

    public boolean updateRestaurantName(String restaurantId, String name , Message msg ){
        return restaurantService.updateRestaurantName(restaurantId,name,msg);
    }

    public boolean updateRestaurantPhoneNumber(String restaurantId, String phoneNumber , Message msg ){
        return restaurantService.updateRestaurantPhoneNumber(restaurantId,phoneNumber,msg);
    }

    public  boolean updateRestaurantAddress(String restaurantId, String address , Message msg ){
        return restaurantService.updateRestaurantAddress(restaurantId, address, msg);

    }

    public   boolean updateRestaurant(String restaurantId, String name ,String address , String phoneNumber ,Message msg ){
        return restaurantService.updateRestaurant(restaurantId, name, address, phoneNumber, msg);
    }

    public boolean deleteRestaurant(String restaurantId , Message msg){
        return restaurantService.deleteRestaurant(restaurantId, msg);
    }

    public  ArrayList< Restaurant> getRestaurantsByOwnerId(String ownerId , Message msg){
        return restaurantService.getRestaurantsByOwnerId(ownerId, msg);
    }
   public  Restaurant isRestaurant(String restaurantId){
        return restaurantService.isRestaurant(restaurantId);
    }

    public ArrayList<Restaurant> getAllRestaurants() {
        return restaurantService.getAllRestaurants();
    }
}

