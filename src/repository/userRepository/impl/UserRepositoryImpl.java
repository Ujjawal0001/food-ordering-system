package repository.userRepository.impl;

import uiClient.Message;
import model.User;
import repository.userRepository.UserRepository;
import java.util.ArrayList;

public class UserRepositoryImpl implements UserRepository {
    ArrayList<User> users=new ArrayList<>();
    private static UserRepositoryImpl instance;
    public static synchronized UserRepositoryImpl getInstance(){
        if(instance==null){
            instance=new UserRepositoryImpl();
        }
        return instance;
    }

    public User save(User user){
        users.add(user);
        return user;
    }
    @Override
    public User findByUsername(String username , String password , Message msg)  {
        for(User user : users){
            if(user.getUserName().equals(username)){
                if(user.getPassword().equals(password)){
                    return user;
                }
            }
            else{
                msg.setMessage("wrong username");

            }
        }
        return null;
    }

    @Override
    public User findByUserId(String userId) {
        for(User user : users){
            if(user.getId().equals(userId)){
                return user;
            }
        }
        return null;
    }

    public User findByEmail(String email,String password){
        for(User user:users){
            if(user.getEmail().equals(email)&&user.getPassword().equals(password)){
                return user;
            }
        }
        return null;
   }
}
