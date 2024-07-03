package repository.userRepository;

import uiClient.Message;
import model.User;

public interface UserRepository {
   User findByUsername(String username , String password , Message msg);
   User findByUserId(String userId);
   User findByEmail(String email,String password );
}
