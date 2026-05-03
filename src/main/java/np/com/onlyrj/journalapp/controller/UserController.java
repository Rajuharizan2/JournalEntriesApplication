package np.com.onlyrj.journalapp.controller;

import np.com.onlyrj.journalapp.entity.User;
import np.com.onlyrj.journalapp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @GetMapping
    public List<User> getAllUser(){
        return userService.getAllUser();
    }

    @GetMapping("/id")
    public Optional<User> getUserById(@RequestBody User user, @PathVariable ObjectId id){
        return userService.getUserById(id);
    }

    @PutMapping("/userName")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable String userName){
        User inUserDB = userService.findByUserName(userName);

        if(inUserDB != null){
            inUserDB.setUserName(user.getUserName());
            inUserDB.setPassword(user.getPassword());
            userService.saveEntry(inUserDB);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
