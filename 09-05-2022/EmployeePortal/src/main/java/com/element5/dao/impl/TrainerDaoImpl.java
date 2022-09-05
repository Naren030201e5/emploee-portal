package com.element5.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import java.lang.NullPointerException;

import org.hibernate.Criteria;
import org.hibernate.HibernateException; 
import org.hibernate.criterion.Restrictions;
import org.hibernate.Session; 
import org.hibernate.Transaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.element5.utils.Factory;
import com.element5.dao.TrainerDao;
import com.element5.model.Trainer;


/**
 *<h2> Employee Portal </h2>
 * This class is used to handle the database operations like 
 * insert, delete, update, get etc.
 *
 * @author Narendranath.s
 * @version 1.0
 * 
 */
public class TrainerDaoImpl implements TrainerDao {

    private Logger logger = LoggerFactory.getLogger(TrainerDaoImpl.class);

    /**
     * This method is used to get list of trainers details.
     *
     * @return List<Trainer> this returns the list of trainers details
     *
     */
    @Override
    public List<Trainer> retrieveTrainersDetails() {

        List<Trainer> trainersList = new ArrayList<>();
        Transaction tx = null;

        try (Session session = Factory.getFactory().openSession();) {
            Criteria criteria = session.createCriteria(Trainer.class).add(Restrictions.eq("isDeleted", false));
            List<Trainer> results = criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
            trainersList = results;
        } catch(HibernateException e) {
            tx.rollback();
            logger.error("{}", e);
        }
        return trainersList;
    }

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
    @Override
    public String insertTrainerDetails(Trainer trainer) {
        Transaction tx = null;
        String message = "Trainer Details not added successfully";

        try (Session session = Factory.getFactory().openSession();) {
            tx = session.beginTransaction();
            session.save(trainer);
            tx.commit();
            message = "Trainer Details added successfully";
        } catch(HibernateException e) {
            tx.rollback();
            logger.error("{}", e);
        }
        return message;
    }

    /**
     * This method will remove particular trainer's details by trainer id in the database.
     *
     * @param id The id parameter is passed in the deteleTrainerById() method
     * which consists of the trainer's id and the parameter type is String.
     *
     */
    @Override
    public String removeTrainerDetails(int id) {

        Transaction tx = null;
        String message = "Trainer Details not deleted";

        try (Session session = Factory.getFactory().openSession();) {
            tx = session.beginTransaction();
            Trainer trainer = (Trainer) session.get(Trainer.class, id);
            trainer.setIsDeleted(true);
            session.update(trainer);
            message = "trainer details deleted successfully";
            tx.commit();
        } catch (HibernateException e) {
            tx.rollback();
            logger.error("{}", e);
        }
        return message;
    }

    /**
     * This method is used to get the particular trainer's details by trainer id.
     * 
     * @param trainerId This is the parameter passed into the retrieveTrainerDetailsById()
     * method and the parameter type is string so that the condition is checked and the employee details is added.
     *
     * @return Trainer this will return the trainer's details for the id which is passed.
     *
     */
    @Override
    public Trainer displayTrainerDetailsById(int trainerId) {

        Transaction tx = null;
        Trainer trainer = null;

        try (Session session = Factory.getFactory().openSession();) {
            tx = session.beginTransaction();
            Trainer trainerDetails = (Trainer) session.get(Trainer.class, trainerId);

            if(trainerDetails != null  && !trainerDetails.getIsDeleted()) {
                trainer = trainerDetails;
            } 
            tx.commit();
        } catch(HibernateException e) {
            tx.rollback();
            logger.error("{}", e);
        }
        return trainer;

    }

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
    @Override
    public String updateTrainerDetails(int trainerId, Trainer trainerDetails) {

        Transaction tx = null;
        String message = "Trainer details not updated successfully";

        try (Session session = Factory.getFactory().openSession();) {
            tx = session.beginTransaction();
            Trainer trainer = (Trainer) session.get(Trainer.class, trainerId);
            trainer.setName(trainerDetails.getName());
            trainer.setEmail(trainerDetails.getEmail());
            trainer.setDateOfBirth(trainerDetails.getDateOfBirth());
            trainer.setGender(trainerDetails.getGender());
            trainer.setMobileNumber(trainerDetails.getMobileNumber());
            trainer.setRole(trainerDetails.getRole());
            trainer.setPreviousCompanyName(trainerDetails.getPreviousCompanyName());
            trainer.setExperience(trainerDetails.getExperience());
            trainer.setTraineeDetails(trainerDetails.getTraineeDetails());
            session.update(trainer);
            message = "Trainer Details updated Successfully";
            tx.commit();
        } catch (HibernateException e) {
            tx.rollback();
            logger.error("{}", e);
        }
        return message;

    }  
}