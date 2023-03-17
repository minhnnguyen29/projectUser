package com.example.project01.user;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


@Repository //indiacates that interface acts as db access layer 
public interface UserRepository extends JpaRepository<User, Long> { //connected to table User id type Long


    //return a nullable user object with username as provided
    @Query("SELECT u from User u where u.username = ?1")
    Optional<User> findByUsername(String username); 


    @Query("SELECT u from User u where u.id =?1")
    Optional<User> findById(Long id);
    
    
}
