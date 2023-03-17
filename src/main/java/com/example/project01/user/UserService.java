package com.example.project01.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@Service //indicates that class acts as service layer to interact with API layer to extract data from DB access layer 
public class UserService {

    private final UserRepository userRepository; //create a reference to the db layer 

    //dependency injection: userRepository is injected into UserService's constructor 
    @Autowired 
    public UserService(UserRepository userRepository)
    {
        this.userRepository = userRepository; //userRepository is instantiated with @Autowired
    }

    public List<User> findAllUsers()
    {
        return userRepository.findAll(); 
    }

    public User findThisUser(Long id)
    {
        User thisUser = userRepository.findById(id)
        .orElseThrow( () -> new IllegalStateException(
            "User with id " + id + " doesn't exist."
        ));
        return thisUser;
    }

    public void addNewUser(User newUser)
    {
        //check if username is taken before allowing to save into database
        Optional<User> userWithId = userRepository.findById(newUser.getId());
        if(userWithId.isPresent()) {
            throw new IllegalStateException("The chosen username is taken. Please provide new username:");
        }
        userRepository.save(newUser); 
    }

    //Apply @Transactional at Service Level with @Modifying @Query to update info
    @Transactional
    public void updateUserDetails(Long id, String password){

        //retrieve candidate with ID below 
        User userWithId = userRepository.findById(id)
            .orElseThrow( () -> new IllegalStateException(
                "User with id " + id + " doesn't exist. Can not be modified."
            ));

        //if user exists, update details 
        if(password != null && password.length() > 0 &&
            !userWithId.getPassword().equals(password)){
                userRepository.updatePassword(id, password);
            }

    }

    public void deleteUserAccount(Long id)
    {
        //retrieve candidate with ID below 
        boolean userExists = userRepository.existsById(id); 
        if (!userExists){
            throw new IllegalStateException(
                "User with id " + id + " doesn't exist. Can not be deleted."
            );
        }

        //delete 
        userRepository.deleteById(id);
        
    }
    
}
