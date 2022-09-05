package com.element5.model;

import java.util.List;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * This class is used to define the Trainee data type which holds the trainee and employee data.
 *
 */
@Entity
@Table(name = "trainees")
public class Trainee extends Employee{

    @Column(name = "passoutyear")
    private Date passOutYear;

    @Column(name = "collegename")
    private String collegeName;

     @ManyToMany(targetEntity = Trainer.class, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
     @JoinTable(name = "trainers_trainees",
               joinColumns = {@JoinColumn(name= "trainee_id")})
     private List<Trainer> trainer;

    public Trainee() {

    }

    public Trainee(Date passOutYear, String collegeName, String trainerId) {
        super();
        this.passOutYear = passOutYear;
        this.collegeName = collegeName;
    }

    public void setPassOutYear(Date passOutYear) {
        this.passOutYear = passOutYear;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public Date getPassOutYear() {
        return passOutYear;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setTrainerDetails(List<Trainer> trainer) {
        this.trainer = trainer;
    }

    public List<Trainer> getTrainerDetails() {
        return trainer;
    }
}