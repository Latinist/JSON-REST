package com.antonromanov.angular4.spa4.model;


import org.hibernate.validator.constraints.NotBlank;


public class RequestValidation {

    @NotBlank(message = "username can't empty!")
    String firstName;

    String LastName;

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getUsername() {
        return firstName;
    }

    public void setUsername(String firstName) {
        this.firstName = firstName;
    }

}
