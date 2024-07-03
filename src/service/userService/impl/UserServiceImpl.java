package service.userService.impl;

import message.Message;
import model.User;
import repository.userRepository.impl.UserRepositoryImpl;
import service.userService.UserService;

public class UserServiceImpl implements UserService {
    private UserRepositoryImpl userRepository = UserRepositoryImpl.getInstance();
    private static UserServiceImpl instance;
    public static synchronized UserServiceImpl getInstance(){
        if(instance==null){
            instance=new UserServiceImpl();

        }
        return instance;
    }



    @Override
    public User register(String userName, String password , String id , String email , String role , Message msg) {
        User user=userRepository.findByUsername(userName,password,msg);
        if(user==null){
            User newUser= new User(userName,password,id,email,role);
            newUser.setEmail(email);
            newUser.setId(id);
            newUser.setUserName(userName);
            newUser.setPassword(password);
            newUser.setRole(role);
            userRepository.save(newUser);
            return newUser;
        }
        return null;
    }

    public User loginByUserName(String userName ,String password , Message msg){
        User user=userRepository.findByUsername(userName,password ,msg);
        if(user!=null){
            return user;
        }
        else {
            msg.setMessage("login failed!");
            return null;
        }
    }

    public User loginByEmail(String email ,String password ,Message msg){
        User user=userRepository.findByEmail(email,password);
        if(user!=null){
            return user;
        }
        else {
            msg.setMessage("login failed!");
            return null;
        }
    }

    public User userProfile(String userId) {
        User userProfile=userRepository.findByUserId(userId);
        if(userProfile!=null){
            return userProfile;
        }
        return null;
    }



}
