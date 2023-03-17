package com.example.project01.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;


@Entity //tell Hibernate to make a table out of this class with name 'users'
@Table(name = "users")
public class User {
    
    @Id
    @SequenceGenerator (//create a sequence for ID so that its auto-incremented by 1 each 
        name = "user_sequence", 
        sequenceName = "user_sequence", 
        allocationSize = 1
    )
    @GeneratedValue (
        strategy = GenerationType.SEQUENCE,
        generator = "user_sequence"    
    )
    @Column(//@Column is used to mention the details of each column/variable 
        name = "id", //name of the column 
        updatable = false 
    )
    private Long id; 
    
    
    @Column( //column userName is NOT NULL, unique & has type: varchar(255)
        name = "username", 
        nullable = false, 
        unique = true, 
        columnDefinition = "varchar(255)" 
    )
    private String username; 

    @Column( //column password is NOT NULL & has type: varchar(255)
        name = "passsword", 
        nullable = false, 
        columnDefinition = "varchar(255)"
    )
    private String password; 
    
    @Column( //column status is NOT NULL & has type boolean with default value: false
        name = "status", 
        nullable = false, 
        columnDefinition = "Boolean default false" //when user is created, loggedIn is set to FALSE 
    )
    private boolean loggedIn; 

    //not creating default contructor as all user object needs to be created with username & password 
    public User(String username, String password) 
    {
        this.username = username; 
        this.password = password; 
    }


    //Getters & Setters 
    public Long getId() 
    {
        return id; 
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getUsername()
    {
        return username; 
    }
    
    public void setPassword(String password)
    {
        this.password = password; 
    }

    public String getPassword()
    {
        return password; 
    }

    public void setStatus()
    {
        loggedIn ^= true; //invert boolean val using XOR operator 
    }

    public boolean getStatus() 
    {
        return loggedIn; 
    }

    @Override
    public String toString()
    {
        return ("User with ID " + id + " has the following info:" + "\n" +
                "Username: " + username + "\n" + 
                "Password: " + password);
    }

}
