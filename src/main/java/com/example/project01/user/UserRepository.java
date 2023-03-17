package com.example.project01.user;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


@Repository //indiacates that interface acts as db access layer 
public interface UserRepository extends JpaRepository<User, Long> { //connected to table User id type Long


    //return a nullable user object with username as provided
    @Query("SELECT u from User u where u.id =?1")
    Optional<User> findById(Long id);

    //JPA defines @Modifying @Query so that we can write our update statement exlicitly 
    @Modifying
    @Query("UPDATE User u SET u.password = :password where u.id = :id")
    void updatePassword(@Param(value = "id") Long id, 
                            @Param(value = "password") String password); 
    
    
}
