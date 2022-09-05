package com.element5.service.impl;

import java.util.List;

import com.element5.model.Trainee;
import com.element5.service.TraineeService;
import com.element5.dao.TraineeDao;
import com.element5.dao.impl.TraineeDaoImpl;

/**
 *<h2> Employee Portal </h2>
 * This class is used to call the TraineeDao impl class.
 *
 * @author Narendranath.s
 * @version 1.0
 * 
 */
public class TraineeServiceImpl implements TraineeService {

    private TraineeDao traineeDao = new TraineeDaoImpl();

    /**
     * This method will add a new trainee's details in the database.
     *
     * @param trainee The trainee parameter is passed in the insertTrainee() method 
     * which consists of the trainee's details and the parameter type is Employee.
     *
     * @return String This method will return a message if the trainee's 
     * details is added successfully.
     *
     *
     */
    @Override
    public String addTraineeDetails(Trainee trainee) {

        return traineeDao.insertTraineeDetails(trainee);
    }

    /**
     * This method is used to get the list of trainees details from employeeDao.
     *
     * @return List<Trainee> This returns the list of  trainees details.
     *
     */
    @Override
    public List<Trainee> showTraineesDetails() {

        return traineeDao.retrieveTraineesDetails();
    }

    /**
     * This method will remove particular trainee's details by trainee id in the database.
     *
     * @param id The id parameter is passed in the deteleTraineeById() method
     * which consists of the trainee's id and the parameter type is String.
     *
     */
    @Override
    public String deleteTraineeDetails(int id) {

        return traineeDao.removeTraineeDetails(id);
    }

    /**
     * This method will remove particular trainer's details by trainer id in the database.
     *
     * @param traineeId The id parameter is passed in the deteleTrainerById() method
     * which consists of the trainer's id and the parameter type is String.
     *
     */
    @Override
    public Trainee showTraineeDetailsById(int traineeId) {

        return traineeDao.displayTraineeDetailsById(traineeId);
    }

    /**
     * This method is used to update a particular trainee's details by trainer id.
     *
     * @param traineeId The id parameter is passed into the updateTraineeById() method
     * which consists of the trainee's id and the parameter type is int.
     *
     * @param trainee This trainee parameter is passed into the updateTraineeById() method
     * which consists of the trainee's details that needs to be updated for the particular id that is passed.
     *
     */
    @Override
    public String changeTraineeDetailsById(int traineeId, Trainee traineeDetails) {

        return traineeDao.updateTraineeDetails(traineeId, traineeDetails);
    }
}