package com.example.project01.user;

import java.util.List;

import org.hibernate.type.internal.UserTypeJavaTypeWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;


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
    //POST
    @PostMapping(path = "/register") //MAP a POST request to add a new user 
    public void register(@RequestBody User newUser)
    {
        if (newUser != null) 
        {
            userService.addNewUser(newUser);
        }
    }


    //GET 
    @GetMapping(path = "/all")
    public List<User> showUsers()
    {
        return userService.findAllUsers(); 
    }

    @GetMapping(path = "/{id}")
    public User showThisUser(@PathVariable("id") Long id)
    {
        return userService.findThisUser(id); 
    }

    //PUT 
    @PutMapping(path = "/update/{id}")
    public void updateUser(@PathVariable("id") Long id, 
                            @RequestParam(required = false) String password) 
    {
        userService.updateUserDetails(id, password);    
    }

    //DELETE 
    @DeleteMapping(path = "/delete/{id}")
    public void deleteUser(@PathVariable("id") Long id)
    {
        userService.deleteUserAccount(id);   
    }

}
