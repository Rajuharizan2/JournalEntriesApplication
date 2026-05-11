package np.com.onlyrj.journalapp.controller;

import np.com.onlyrj.journalapp.entity.User;
import np.com.onlyrj.journalapp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User inUserDB = userService.findByUserName(userName);
        inUserDB.setUserName(user.getUserName());
        inUserDB.setPassword(user.getPassword());
        userService.saveEntry(inUserDB);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
