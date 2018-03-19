package com.antonromanov.angular4.spa4.model;

import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;


public class Request {

    private static final AtomicInteger count = new AtomicInteger(0);
    private long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private Date birthday;
    private String email;
    //private boolean isFilled;

    public Request(){

    }

    private void incID() {
        //isFilled = false;
        this.id = count.incrementAndGet();
        System.out.println("Мы установили ID = " + this.id);
    }
    public Request(Long id, String firstName, String middleName, String lastName, Date birthday, String email) {

        //this.incID();
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
     //   this.id = count.set(id);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }





    @Override
    public int hashCode() {
        final int prime = 31;
        long result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return (int)result;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Request req = (Request) o;


        if (firstName != null ? !firstName.equals(req.firstName) : req.firstName != null) return false;
        if (middleName != null ? !middleName.equals(req.middleName) : req.middleName != null) return false;
        if (lastName != null ? !lastName.equals(req.lastName) : req.lastName != null) return false;
      //  if (birthday != null ? !birthday.equals(req.birthday) : req.birthday != null) return false;
        if (email != null ? !email.equals(req.email) : req.email != null) return false;


        return true;
    }



}
