package com.example.project01.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RestController //indicates that class acts as API layer that interacts with Service layer & the web  using API calls 
@RequestMapping(path = "/api/v1/users") //the path will start with  
public class UserController {

    private final UserService userService; //create a reference to service layer 

    //dependency injection: userService is being injected in UserController's constructor 
    @Autowired 
    public UserController(UserService userService)
    {
        this.userService = userService; //userService is instantiated with @Autowired
    }
    
    //============================================END POINTS===================================================
    @PostMapping(path = "/register") //MAP a POST request to add a new user 
    public void register(@RequestBody User newUser)
    {
        if (newUser != null) 
        {
            userService.addNewUser(newUser);
        }
    }

    @GetMapping(path = "/all")
    public List<User> showUsers()
    {
        return userService.findAllUsers(); 
    }


}
