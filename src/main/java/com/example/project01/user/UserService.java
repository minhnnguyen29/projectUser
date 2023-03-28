package com.example.project01.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@Service //indicates that class acts as service layer to interact with API layer to extract data from DB access layer 
public class UserService {

    @Autowired 
    private UserRepository userRepository; //create a reference to the db layer 

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
    public void updateUserDetails(User user){

        //retrieve candidate with ID below 
        if ( userRepository.existsById(user.getId()) ){
            userRepository.save(user);
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
