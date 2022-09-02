package com.element5.dao;

import java.util.List;
import com.element5.model.Trainee;

public interface TraineeDao {

    /**
     * This method is used to get list of trainees details.
     *
     * @return List<Trainee> this returns the list of trainees details
     *
     */
    List<Trainee> retrieveTraineesDetails();

    /**
     * This method will add a new trainee's details in the database.
     *
     * @param trainee The trainee parameter is passed in the insertTrainee() method 
     * which consists of the trainee's details and the parameter type is Employee.
     * 
     * @return String this method will return message when the details is added is successfully;
     *
     *
     */
    String insertTraineeDetails(Trainee trainee);

    /**
     * This method will remove particular trainee's details by trainee id in the database.
     *
     * @param id The id parameter is passed in the deteletraineeById() method
     * which consists of the trainee's id and the parameter type is String.
     *
     */
    String removeTraineeDetails(int id);

    /**
     * This method is used to get the particular trainee's details by trainee id.
     * 
     * @param traineeId This is the parameter passed into the retrievetraineeDetailsById()
     * method and the parameter type is string so that the condition is checked and the employee details is added.
     *
     * @return trainee this will return the trainee's details for the id which is passed.
     *
     */
    Trainee displayTraineeDetailsById(int traineeId);

    /**
     * This method is used to update a particular trainee's details by trainee id.
     *
     * @param traineeId The id parameter is passed into the updatetraineeById() method
     * which consists of the trainee's id and the parameter type is string.
     *
     * @param traineeDetails this trainee parameter is passed into the updatetraineeById() method
     * which consists of the trainee's details that needs to be updated for the particular id that is passed.
     *
     * @return String this method will return a message when the details is updated successfully.
     *
     */
    String updateTraineeDetails(int traineeId, Trainee traineeDetails);
}