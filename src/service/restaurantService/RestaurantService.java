package service.restaurantService;

import message.Message;
import model.Restaurant;

import java.util.ArrayList;

public interface RestaurantService {
    boolean createRestaurant(String name , String id , String ownerId , String address , String phoneNumber , Message msg);
    boolean updateRestaurantName(String restaurantId, String name , Message msg );
    boolean updateRestaurantPhoneNumber(String restaurantId, String phoneNumber , Message msg );
    boolean updateRestaurantAddress(String restaurantId, String address , Message msg );
    boolean updateRestaurant(String restaurantId, String name ,String address , String phoneNumber ,Message msg );
    boolean deleteRestaurant(String restaurantId , Message msg);
    ArrayList< Restaurant> getRestaurantsByOwnerId(String ownerId , Message msg);

}
