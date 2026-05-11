package np.com.onlyrj.journalapp.controller;

import np.com.onlyrj.journalapp.entity.User;
import np.com.onlyrj.journalapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    @GetMapping("/checks")
    public String healthCheck(){
        return "All ok...";
    }

    @PostMapping("/create-user")
    public ResponseEntity<?> createUser(@RequestBody User user){
        User saveUser = userService.saveEntry(user);
        return new ResponseEntity<>(saveUser, HttpStatus.OK);
    }


}
