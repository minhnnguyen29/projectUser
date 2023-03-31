package com.example.project01.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;


@Controller //indicates that class acts as API layer that interacts with Service layer & the web  using API calls 
public class UserController {

    @Autowired 
    private UserService userService; //create a reference to service layer 

    // ========================== CREATE ============================
    
    @GetMapping(path = "/signupForm")
    public String showSignupForm(User newUser){
        return "signupForm"; 
    }

    @PostMapping(path = "/register") //MAP a POST request to add a new user 
    public String register(@Valid User newUser, BindingResult result, Model model)
    {
        if(result.hasErrors()) { //if newUser is not valid (BindingResult unsuccessful)
            return "signupForm"; //show signupForm again 
        }
        userService.addNewUser(newUser);
        return "redirect:/";//returns template 'register' that allows user to sign up 
    }



    // ========================== READ ============================== 
    @GetMapping(value = "/all")
    public String showUsers(Model model)
    {   
        //bind attr 'allUsers' with value of all users
        model.addAttribute("allUsers", userService.findAllUsers());
        return "all";//returns template 'all' that display the table listing all users 
    }

    @GetMapping(value = "/login")
    public String login(Model model) 
    {
        return "login";
    }

    @GetMapping(path = "/{id}")
    public String showThisUser(Model model, @PathVariable("id") Long id)
    {
        model.addAttribute("currentUser", userService.findThisUser(id));
        return "user"; //return user template to show 
    }

    // ========================= UPDATE ===================================     
    @GetMapping(path = "/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, 
                             Model model) 
    {
        //find user to update 
        User currentUser = userService.findThisUser(id);
        
        //bind this user as model attribute to return user 
        model.addAttribute("currentUser", currentUser);
        return "update";
    }

    @PostMapping(path = "updating/{id}")
    public String updateUser(@PathVariable("id") Long id, @ModelAttribute("currentUser") User currentUser, BindingResult result, Model model)
    {
        if(result.hasErrors()) {
            currentUser.setId(id);
            return "update";
        }

        //save this data to db 
        userService.updateUserDetails(currentUser);
        return "redirect:/";
    }

    // ======================== DELETE ==================================== 
    @GetMapping(path = "/delete/{id}")
    public String deleteUser(Model model, @PathVariable("id") Long id)
    {
        model.addAttribute("chosenUser", id);
        userService.deleteUserAccount(id);  
        return "redirect:/all";  
    }

}
