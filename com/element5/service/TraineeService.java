package com.element5.service;

import java.util.List;
import com.element5.model.Trainee;

public interface TraineeService {

    /**
     * This method will add a new trainee's details in the database.
     *
     * @param trainee The trainee parameter is passed in the insertTrainee() method 
     * which consists of the trainee's details and the parameter type is Employee.
     *
     * @return String this method will return a message if the trainee's 
     * details is added successfully.
     *
     *
     */
    String addTraineeDetails(Trainee trainee);

    /**
     * This method is used to get the list of trainees details from employeeDao.
     *
     * @return List<Trainee> this returns the list of  trainees details.
     *
     */
    List<Trainee> showTraineesDetails();

    /**
     * This method will remove particular trainee's details by trainee id in the database.
     *
     * @param id The id parameter is passed in the deteleTraineeById() method
     * which consists of the trainee's id and the parameter type is String.
     *
     */
    String deleteTraineeDetails(int id);

    /**
     * This method will remove particular trainer's details by trainer id in the database.
     *
     * @param traineeId The id parameter is passed in the deteleTrainerById() method
     * which consists of the trainer's id and the parameter type is String.
     *
     */
    Trainee showTraineeDetailsById(int traineeId);

    /**
     * This method is used to update a particular trainee's details by trainer id.
     *
     * @param traineeId The id parameter is passed into the updateTraineeById() method
     * which consists of the trainee's id and the parameter type is int.
     *
     * @param trainee this trainee parameter is passed into the updateTraineeById() method
     * which consists of the trainee's details that needs to be updated for the particular id that is passed.
     *
     */
    String changeTraineeDetailsById(int traineeId, Trainee traineeDetails);

}