package com.example.project01.user;

import java.util.List;

import org.hibernate.type.internal.UserTypeJavaTypeWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;


@Controller //indicates that class acts as API layer that interacts with Service layer & the web  using API calls 
public class UserController {

    @Autowired 
    private UserService userService; //create a reference to service layer 

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
    @GetMapping(value = "/all")
    public String showUsers(Model model)
    {   
        //bind attr 'allUsers' with value of all users
        model.addAttribute("allUsers", userService.findAllUsers());
        return "all"; 
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
