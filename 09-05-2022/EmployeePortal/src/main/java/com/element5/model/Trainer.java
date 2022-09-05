package com.element5.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * This class is used to define the Trainer data type which holds the trainers and employee data.
 *
 */
@Entity
@Table(name = "trainers")
public class Trainer extends Employee {

    @Column(name = "experience")
    private int experience;

    @Column(name = "companyname")
    private String previousCompanyName;

    @ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "trainers_trainees",
               joinColumns = {@JoinColumn(name= "trainer_id")})
    private List<Trainee> trainee;

    public Trainer() {

    }

    Trainer(int experience, String previousCompanyName) {
        super();
        this.experience = experience;
        this.previousCompanyName = previousCompanyName;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public void setPreviousCompanyName(String previousCompanyName) {
        this.previousCompanyName = previousCompanyName;
    }

    public int getExperience() {
        return experience;
    }

    public String getPreviousCompanyName() {
        return previousCompanyName;
    }

    public List<Trainee> getTraineeDetails() {
        return trainee;
    }

    public void setTraineeDetails(List<Trainee> trainee) {
        this.trainee = trainee;
    }

}
