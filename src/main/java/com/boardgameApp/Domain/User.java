package com.boardgameApp.Domain;

import com.boardgameApp.Service.MyGenerator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
// marks a property to be ignored
@JsonIgnoreProperties({"hibernateLazyInitializer"})
// This annotation tells the program to call the table 'users'.
@Table(name = "users")
public class User {


    private UserType userType;

    // String is valid as long as it's not null
    @Id
    private @NotBlank String userName;

    private @NotBlank String emailAddress;
    private @NotBlank String password;
    private @NotBlank boolean loggedIn;

    public User() {
    }

    public User(String userName, @NotBlank String emailAddress, @NotBlank String password, UserType userType, boolean loggedIn) {
        this.userName = userName;
        this.emailAddress = emailAddress;
        this.password = password;
        this.userType = userType;
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

    public UserType getUserTypeID() {
        return userType;
    }

    public void setUserTypeID(UserType userType) {
        this.userType = userType;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
    /*
        Overrides the standard equals method
    */
    @Override

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return Objects.equals(emailAddress, user.emailAddress) &&
                Objects.equals(password, user.password);
    }
    /*
    Overrides the standard hashCode() method.
     */
    @Override
    public int hashCode() {
        return Objects.hash(userName, userType, emailAddress, password,
                loggedIn);
    }
    /*
    Overrides the standard toString() method.
     */
    @Override

    public String toString() {
        return "User{" +
                "userName=" + userName +
                ", emailAddress='" + emailAddress + '\'' +
                ", password='" + password + '\'' +
                ", loggedIn=" + loggedIn +
                '}';
    }

}
