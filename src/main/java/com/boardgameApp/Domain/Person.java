package com.boardgameApp.Domain;

import com.boardgameApp.Repositories.PersonRepository;
import com.boardgameApp.Service.MyGenerator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
// marks a property to be ignored
@JsonIgnoreProperties({"hibernateLazyInitializer"})
// @Table = This annotation tells the program to call the table 'users'.
@Table(name = "users")
public class Person {


    private PersonType userTypeID;
    private String userName;
    // String is valid as long as it's not null
    private @NotBlank String emailAddress;
    private @NotBlank String password;
    private @NotBlank boolean loggedIn;

    @Id
    @GeneratedValue(generator = MyGenerator.generatorName)
    @GenericGenerator(name = MyGenerator.generatorName, strategy = "com.boardgameApp.Service.MyGenerator")
    private String userID;


    public Person() {
    }

    public Person(String name, @NotBlank String emailAddress, @NotBlank String password, PersonType userTypeID, String userID, boolean loggedIn) {
        this.userName = name;
        this.emailAddress = emailAddress;
        this.password = password;
        this.userTypeID = userTypeID;
        this.userID = userID;
        this.loggedIn = loggedIn;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public PersonType getUserTypeID() {
        return userTypeID;
    }

    public void setUserTypeID(PersonType userTypeID) {
        this.userTypeID = userTypeID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }


    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    @Override
    /*
    We need to use this at a later point to compare an object that's given in by the user, with an object from our database.
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person person)) return false;
        return Objects.equals(emailAddress, person.emailAddress) &&
                Objects.equals(password, person.password);
    }
    @Override
    /*
    This function is used to generate a hash value of the object.
     */
    public int hashCode() {
        return Objects.hash(userID, userTypeID, emailAddress, password,
                loggedIn);
    }
    @Override
    /*
    Makes a string of the information about the object.
     */
    public String toString() {
        return "Person{" +
                "id=" + userID +
                ", emailAddress='" + emailAddress + '\'' +
                ", password='" + password + '\'' +
                ", loggedIn=" + loggedIn +
                '}';
    }

}
