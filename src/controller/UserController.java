package controller;

import message.Message;
import model.User;
import service.userService.impl.UserServiceImpl;

public class UserController {

    private static UserController instance;
    public static synchronized UserController getInstance(){
        if(instance==null){
            instance=new UserController();
        }
        return instance;
    }
    private UserServiceImpl userService = UserServiceImpl.getInstance();

    public User register(String userName, String password , String id , String email , String role , Message msg){
        return userService.register(userName,password,id,email,role,msg);
    }

    public User loginByUserName(String userName ,String password , Message msg){
        return userService.loginByUserName(userName, password, msg);
    }

    public User loginByEmail(String email ,String password ,Message msg){
        return userService.loginByEmail(email,password, msg);
    }

    public   User userProfile(String userId ){
        return userService.userProfile(userId);
    }
}
