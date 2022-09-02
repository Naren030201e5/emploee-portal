package com.element5.service.impl;

import java.util.List;

import com.element5.model.Trainer;
import com.element5.service.TrainerService;
import com.element5.dao.TrainerDao;
import com.element5.dao.impl.TrainerDaoImpl;

public class TrainerServiceImpl implements TrainerService {

    private TrainerDao trainerDao = new TrainerDaoImpl();

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
    @Override
    public String addTrainerDetails(Trainer trainer) {

        return trainerDao.insertTrainerDetails(trainer);
    }

    /**
     * This method is used to get list of trainers details from employeeDao.
     *
     * @return List<Trainer> this returns the list of trainers details.
     *
     */
    @Override
    public List<Trainer> showTrainersDetails() {

        return trainerDao.retrieveTrainersDetails();
    }

    /**
     * This method will remove particular trainer's details by trainer id in the database.
     *
     * @param id The id parameter is passed in the deteleTrainerById() method
     * which consists of the trainer's id and the parameter type is int
     *
     */
    @Override
    public String deleteTrainerDetails(int id) {

        return trainerDao.removeTrainerDetails(id);
    }

    /**
     * This method will is used to get the particular trainer's details by trainer id.
     * 
     * @param trainerId this is the parameter passed into the retrieveTrainerDetailsById()
     * method and the parameter type is string so that the condition is checked and the employee details is retrieved.
     *
     * @return Trainer this will return the trainer's details for the id which is passed.
     *
     */
    @Override
    public Trainer showTrainerDetailsById(int trainerId) {

        return trainerDao.displayTrainerDetailsById(trainerId);
    }

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
    @Override
    public String changeTrainerDetailsById(int id, Trainer trainerDetails) {

        return trainerDao.updateTrainerDetails(id, trainerDetails);
    }
}