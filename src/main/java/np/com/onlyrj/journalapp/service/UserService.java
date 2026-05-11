package np.com.onlyrj.journalapp.service;

import jakarta.annotation.Nonnull;
import np.com.onlyrj.journalapp.entity.User;
import np.com.onlyrj.journalapp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public User saveEntry(@Nonnull User user){
        user.setPassword(passwordEncoder().encode(user.getPassword()));
        user.setRole(Arrays.asList("USER"));
        userRepository.save(user);
        return user;
    }

    public void saveNewUser(User user){
        userRepository.save(user);
    }

    public Optional <User> getUserById(ObjectId id){
        return userRepository.findById(id);
    }



    public User findByUserName(String userName){
        return userRepository.findByUserName(userName);
    }

    public void deleteById(ObjectId id){
        userRepository.deleteById(id);
    }
}
