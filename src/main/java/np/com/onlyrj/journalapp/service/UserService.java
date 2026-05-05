package np.com.onlyrj.journalapp.service;

import np.com.onlyrj.journalapp.entity.User;
import np.com.onlyrj.journalapp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    // Service for Create entry
    public User saveEntry(User user){
        userRepository.save(user);
        return user;
    }

    public Optional <User> getUserById(ObjectId id){
        return userRepository.findById(id);
    }

    // Get all entries
    public List<User> getAllUser(){
        return userRepository.findAll();
    }


    public User findByUserName(String userName){
        return userRepository.findByUserName(userName);
    }

    public void deleteById(ObjectId id){
        userRepository.deleteById(id);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }
}
