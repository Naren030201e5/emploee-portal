package com.element5.dao;

import java.util.List;
import com.element5.model.Trainer;


public interface TrainerDao {

    /**
     * This method is used to get list of trainers details.
     *
     * @return List<Trainer> this returns the list of trainers details
     *
     */
    List<Trainer> retrieveTrainersDetails();

    /**
     * This method will add a new trainer's details in the database.
     *
     * @param trainer The trainer parameter is passed in the insertTrainer() method 
     * which consists of the trainer's details and the parameter type is Employee.
     * 
     * @return String this method will return message when the details is added is successfully;
     *
     *
     */
    String insertTrainerDetails(Trainer trainer);

    /**
     * This method will remove particular trainer's details by trainer id in the database.
     *
     * @param id The id parameter is passed in the deteleTrainerById() method
     * which consists of the trainer's id and the parameter type is String.
     *
     */
    String removeTrainerDetails(int id);

    /**
     * This method is used to get the particular trainer's details by trainer id.
     * 
     * @param trainerId This is the parameter passed into the retrieveTrainerDetailsById()
     * method and the parameter type is string so that the condition is checked and the employee details is added.
     *
     * @return Trainer this will return the trainer's details for the id which is passed.
     *
     */
    Trainer displayTrainerDetailsById(int trainerId);

    /**
     * This method is used to update a particular trainer's details by trainer id.
     *
     * @param trainerId The id parameter is passed into the updateTrainerById() method
     * which consists of the trainer's id and the parameter type is string.
     *
     * @param trainerDetails this trainer parameter is passed into the updateTrainerById() method
     * which consists of the trainer's details that needs to be updated for the particular id that is passed.
     *
     * @return String this method will return a message when the details is updated successfully.
     *
     */
    String updateTrainerDetails(int trainerId, Trainer trainerDetails);
}