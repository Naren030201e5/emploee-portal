package com.element5.service;

import java.util.List;
import com.element5.model.Trainer;

/**
 *<h2> Employee Portal </h2>
 * This interface is used to define the abstract methods for 
 * trainerServiceImpl class.
 *
 * @author Narendranath.s
 * @version 1.0
 * 
 */
public interface TrainerService {

    /**
     * This method will add a new trainer's details in the database.
     *
     * @param trainer The trainer parameter is passed in the insertTrainer() method 
     * which consists of the trainer's details and the parameter type is Employee.
     * 
     * @return String this metjod will return a message if the trainer
     * details are added syuccessfully.
     *
     *
     */
    String addTrainerDetails(Trainer trainer);

    /**
     * This method is used to get list of trainers details from employeeDao.
     *
     * @return List<Trainer> this returns the list of trainers details.
     *
     */
    List<Trainer> showTrainersDetails();

    /**
     * This method will remove particular trainer's details by trainer id in the database.
     *
     * @param id The id parameter is passed in the deteleTrainerById() method
     * which consists of the trainer's id and the parameter type is int
     *
     */
    String deleteTrainerDetails(int id);

    /**
     * This method will is used to get the particular trainer's details by trainer id.
     * 
     * @param trainerId this is the parameter passed into the retrieveTrainerDetailsById()
     * method and the parameter type is string so that the condition is checked and the employee details is retrieved.
     *
     * @return Trainer this will return the trainer's details for the id which is passed.
     *
     */
    Trainer showTrainerDetailsById(int trainerId);

    /**
     * This method is used to update a particular trainer's details by trainer id.
     *
     * @param id The id parameter is passed into the updateTrainerById() method
     * which consists of the trainer's id and the parameter type is string.
     *
     * @param trainer this trainer parameter is passed into the updateTrainerById() method
     * which consists of the trainer's details that needs to be updated for the particular id that is passed.
     *
     * @return this will return a message when the trainer details
     * is updated successfully.
     *
     */
    String changeTrainerDetailsById(int id, Trainer trainerDetails);
}