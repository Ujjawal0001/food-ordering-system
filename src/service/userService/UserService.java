package service.userService;

import uiClient.Message;
import model.User;
public interface UserService {
     User register(String userName, String password , String id , String email , String role , Message msg);
     User loginByUserName(String userName ,String password , Message msg);
     User loginByEmail(String email ,String password ,Message msg);
     User userProfile(String userId );
}
