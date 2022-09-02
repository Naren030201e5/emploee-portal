package com.element5.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;

import com.element5.utils.Factory;

import com.element5.model.Trainee;
import com.element5.dao.TraineeDao;

public class TraineeDaoImpl implements TraineeDao {

    /**
     * This method is used to get list of trainees details.
     *
     * @return List<Trainee> this returns the list of trainees details
     *
     */
    @Override
    public List<Trainee> retrieveTraineesDetails() {

        List<Trainee> traineesList = new ArrayList<>();
        Transaction tx = null;
        try (Session session = Factory.getFactory().openSession();) {

            tx = session.beginTransaction();
            List traineesDetails = session.createQuery("from Trainee where isDeleted = false").list();

            for (Iterator iterator = traineesDetails.iterator(); iterator.hasNext();) {

                Trainee trainee = (Trainee) iterator.next();
                traineesList.add(trainee);
            }
            tx.commit();

        } catch (HibernateException e) {
            tx.rollback();
            e.printStackTrace();
        }
        return traineesList;
    }

    /**
     * This method is used to get the particular trainee's details by trainee id.
     * 
     * @param traineeId This is the parameter passed into the retrievetraineeDetailsById()
     * method and the parameter type is string so that the condition is checked and the employee details is added.
     *
     * @return trainee this will return the trainee's details for the id which is passed.
     *
     */
    @Override
    public String insertTraineeDetails(Trainee trainee) {

        Transaction tx = null;
        String message = "Trainee Details not added successfully";

        try (Session session = Factory.getFactory().openSession();) {

            tx = session.beginTransaction();
            session.save(trainee);
            tx.commit();
            message = "Trainee Details added successfully";

        } catch(HibernateException e) {
            tx.rollback();
            e.printStackTrace();
        }
        return message;
    }

    /**
     * This method will remove particular trainee's details by trainee id in the database.
     *
     * @param id The id parameter is passed in the deteletraineeById() method
     * which consists of the trainee's id and the parameter type is String.
     *
     */
    @Override
    public String removeTraineeDetails(int id) {

        Transaction tx = null;
        String message = "Trainee Details not deleted";

        try (Session session = Factory.getFactory().openSession();) {

            tx = session.beginTransaction();
            Trainee trainee = (Trainee) session.get(Trainee.class, id);
            trainee.setIsDeleted(true);
            session.update(trainee);
            message = "trainee details deleted successfully";
            tx.commit();

        } catch (HibernateException e) {
            tx.rollback();
            e.printStackTrace();
        }
        return message;
    }

    /**
     * This method is used to get the particular trainee's details by trainee id.
     * 
     * @param traineeId This is the parameter passed into the retrievetraineeDetailsById()
     * method and the parameter type is string so that the condition is checked and the employee details is added.
     *
     * @return trainee this will return the trainee's details for the id which is passed.
     *
     */
    @Override
    public Trainee displayTraineeDetailsById(int traineeId) {

        Transaction tx = null;
        Trainee trainee = null;

        try (Session session = Factory.getFactory().openSession();) {

            tx = session.beginTransaction();
            Trainee traineeDetails = (Trainee) session.get(Trainee.class, traineeId);

            if(traineeDetails != null && !traineeDetails.getIsDeleted()) {
                trainee = traineeDetails;
            }
            
            tx.commit();

        } catch(HibernateException e) {
            tx.rollback();
            e.printStackTrace();
        }
        return trainee;
    }

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
    @Override
    public String updateTraineeDetails(int traineeId, Trainee traineeDetails) {

        Transaction tx = null;
        String message = "Trainee details not updated successfully";

        try (Session session = Factory.getFactory().openSession();) {

            tx = session.beginTransaction();
            Trainee trainee = (Trainee) session.get(Trainee.class, traineeId);
            trainee.setName(traineeDetails.getName());
            trainee.setEmail(traineeDetails.getEmail());
            trainee.setDateOfBirth(traineeDetails.getDateOfBirth());
            trainee.setGender(traineeDetails.getGender());
            trainee.setMobileNumber(traineeDetails.getMobileNumber());
            trainee.setRole(traineeDetails.getRole());
            trainee.setCollegeName(traineeDetails.getCollegeName());
            trainee.setPassOutYear(traineeDetails.getPassOutYear());
            trainee.setTrainerDetails(traineeDetails.getTrainerDetails());
            session.persist(trainee);
            message = "trainee Details updated Successfully";
            tx.commit();

        } catch(HibernateException e) {
            tx.rollback();
            e.printStackTrace();
        }
        return message;
    }
}