package com.element5.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.MappedSuperclass;

import java.sql.Date;

/**
 * This class is used to define the Employee data type which holds the trainers and trainees data.
 *
 */
@MappedSuperclass
public class Employee {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "mobilenumber")
    private long mobileNumber;

    @Column(name = "role")
    private String role;

    @Column(name = "email")
    private String email;

    @Column(name = "gender")
    private String gender;

    @Column(name = "dateofbirth")
    private Date dateOfBirth;

    @Column(name = "isdeleted")
    private boolean isDeleted;

    public Employee(){

    }

    public Employee(String name, int id, long mobileNumber, String role, String email, String gender, Date dateOfBirth, boolean isDeleted){
        this.name = name;
        this.id = id;
        this.mobileNumber = mobileNumber;
        this.role = role;
        this.email = email;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.isDeleted = isDeleted;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setMobileNumber(long mobileNumber){
        this.mobileNumber = mobileNumber;
    }

    public void setRole(String role){
        this.role = role;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setDateOfBirth(Date dateOfBirth){
        this.dateOfBirth = dateOfBirth;
    }

    public void setGender(String gender){
        this.gender = gender;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public String getName(){
        return name;
    }

    public int getId(){
        return id;
    }

    public long getMobileNumber(){
        return mobileNumber;
    }

    public String getRole(){
        return role;
    }

    public String getEmail(){
        return email;
    }

    public String getGender(){
        return gender;
    }

    public Date getDateOfBirth(){
        return dateOfBirth;
    }

}