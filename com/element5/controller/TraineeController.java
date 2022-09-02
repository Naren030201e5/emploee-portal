package com.element5.controller;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

import java.time.LocalDate;
import java.time.Period;

import java.sql.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.element5.exceptions.InvalidExperienceException;
import com.element5.model.Trainee;
import com.element5.model.Trainer;
import com.element5.service.TraineeService;
import com.element5.service.impl.TraineeServiceImpl;
import com.element5.service.TrainerService;
import com.element5.service.impl.TrainerServiceImpl;
import com.element5.utils.Utils;

/**
 *
 * This class is used to handle all the user input operations and calls 
 * the TrainerServiec class for the required method selected.
 *
 * @author Narendranath.s
 * @version 1.0
 * 
 */
public class TraineeController {

    private static Scanner scanner = new Scanner(System.in);
    private static TrainerController trainerController = new TrainerController();
    private static TraineeService traineeService = new TraineeServiceImpl();
    private static TrainerService trainerService = new TrainerServiceImpl();
    private static Logger logger = LoggerFactory.getLogger(TrainerController.class);
    private static Utils validation = new Utils();

    /**
     * This metod is used to display the menu and the user will select 
     * the options and the required method is called.
     *
     */
    public static void displayMenu() {

        System.out.println("Select an Option To proceed");
        System.out.println("1) Display Trainee Details");
        System.out.println("2) Insert Trainee Details");
        System.out.println("3) Delete Trainee Details");
        System.out.println("4) Show Specific Trainee Details");
        System.out.println("5) Update Trainee Details");
        System.out.println("6) assign trainer to trainee");
        System.out.println("12) Exit");
        int option = scanner.nextInt();

        switch(option) {

            case 1:
                displayTrainees();
                displayMenu();
                break;

            case 2:
                getTraineeDetails();
                displayMenu();
                break;

            case 3:
                deleteTrainee();
                displayMenu();
                break;

            case 4:
                displayTraineeById();
                displayMenu();
                break;

            case 5:
                updateTrainee();
                displayMenu();
                break;

            case 6:
                assignTrainerToTrainee();
                displayMenu();
                break;

            case 12:
                logger.info("Thank ypu for using employee portal");
        }
    }

    /**
     *
     * This method is used to display all the Trainers details.
     *
     */
    public static void displayTrainees() {

        List<Trainee> trainees = traineeService.showTraineesDetails();

        if (trainees.size() != 0) {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Trainees Details~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

            trainees.forEach(trainee->
                {
                     System.out.println("Trainee Id: " + trainee.getId());
                     System.out.println("Trainee Name: " + trainee.getName());
                     System.out.println("Trainee email: " + trainee.getEmail());
                     System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                });

        } else {
            logger.info ("No trainees Details");
        }
    }

    /**
     *
     * This method is used to get the Trainer's details, validate the trainer's details
     * and the details are added in the dao by calling the service class.
     *
     */
    public static void getTraineeDetails() {

        Trainee trainee = new Trainee();

        scanner.nextLine();
        boolean isNameValidated = false;

        while (!isNameValidated) {
            System.out.println("Enter the name of the Trainer");
            String name = scanner.nextLine();

            if (!name.isEmpty()) {

                if (validation.validateName(name)) {
                    trainee.setName(name);
                    isNameValidated = true;
                    logger.info("Trainee name added");
                }

            } else {
                logger.error("Name not entered enter a name to proceed next");
            }
        }

        boolean isNumberValidated = false;

        while (!isNumberValidated) {
            System.out.println ("Enter Mobile Number");
            Long mobileNumber = scanner.nextLong();

            if (validation.validateMobileNumber(mobileNumber)) {
                trainee.setMobileNumber(mobileNumber);
                isNumberValidated = true;
                logger.info("trainee mobile number added");

            } else {
                logger.error("Number not a valid number");
            }
        }

        System.out.println("Enter the role");
        String role = scanner.nextLine();
        role+= scanner.nextLine();
        trainee.setRole(role);
        logger.info("Trainee role added");

        boolean isDobValidated = false;
        int date = 0, month = 0, year = 0;

        while (!isDobValidated) {
            System.out.println ("Enter Date Of Birth");
            System.out.println ("Enter the date");
            date = scanner.nextInt();
            System.out.println ("Enter the month");
            month = scanner.nextInt();
            System.out.println ("Enter the year");
            year = scanner.nextInt();

            if (validation.validateDateOfBirth(date, month, year)) {
                Date dob= Date.valueOf(LocalDate.of(year, month, date));
                trainee.setDateOfBirth(dob);
                isDobValidated = true;
                logger.info("DOB added");

            } else {
                logger.error("Enter a valid date of birth");
            }
        }

        int age = Period.between(LocalDate.of(year, month, date), LocalDate.now()).getYears();
        System.out.println("Your Age is: " + age); 

        scanner.nextLine();
        boolean isEmailValidated = false;

        while (!isEmailValidated) {
            System.out.println("Enter email");
            String email = scanner.nextLine();

            if (validation.validateEmail(email)) {
                trainee.setEmail(email);
                isEmailValidated = true;
                logger.info("Trainee email added");

            } else {
                logger.error("Enter a valid Email");
            }
        }

        boolean isGenderValidated = false;

        while (!isGenderValidated) {
            System.out.println("Enter gender");
            String gender = scanner.nextLine();

            if (validation.validateGender(gender)) {
                trainee.setGender(gender);
                isGenderValidated = true;
                logger.info("Trainee gender added");

            } else {
                logger.error("Enter a valid gender");
            }
        }

        System.out.println("Enter the college name");
        String collegeName = scanner.nextLine();
        trainee.setCollegeName(collegeName);
        logger.info("college name added");
        
        System.out.println("Enter the passout year");
        System.out.println("enter date");
        int passDate = scanner.nextInt();
        System.out.println("enter month");
        int passMonth = scanner.nextInt();
        System.out.println("enter year");
        int passYear = scanner.nextInt();
        Date datePass = Date.valueOf(LocalDate.of(passYear, passMonth, passDate));
        trainee.setPassOutYear(datePass);
        logger.info("Pass out Year added");

        logger.info("{}", traineeService.addTraineeDetails(trainee));
    }

    /**
     * This method is used to remove the paritcular trainer's details
     * by calling the deleteTrainerDetailsById() method from service.
     *
     */
    public static void deleteTrainee() {

        System.out.println("enter the Trainer id to be Deleted");
        int id = scanner.nextInt();
        logger.info("{}", traineeService.deleteTraineeDetails(id));
    }

    /**
     * This method is used to display the paritcular trainee's details
     * by calling the showTrainerDetailsById() method from service.
     *
     */
    public static void displayTraineeById() {

        System.out.println("enter the Trainee id to be displayed");
        int id = scanner.nextInt();
        Trainee trainee =  traineeService.showTraineeDetailsById(id);

        if (trainee != null && !trainee.getIsDeleted()) {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~Trainee Details~~~~~~~~~~~~~~~~~");
            System.out.println("Name: " + trainee.getName());
            System.out.println("Email: " + trainee.getEmail() + "\n"); 

            List<Trainer> trainer = trainee.getTrainerDetails();

            if (trainer != null) {
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~Trainer Details~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

                trainer.forEach(trainers-> {
                    System.out.println("name: " + trainers.getName());
                    System.out.println("Email: " + trainers.getEmail());
                    System.out.println("Trainer Id: " +trainers.getId());
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + "\n");
                    });

            } else {
                logger.info("No trainer has been assigned to this trainee");
            }

        } else {
            logger.info("No trainee details available or trainer id invalid");
        }
    }

    /**
     * This method is used to update the paritcular trainer's details
     * by calling the changeTrainerDetailsById() method from service.
     *
     */
    public static void updateTrainee() {

        System.out.println("Enter the Trainee Id to be updaetd");
        int id = scanner.nextInt();
        Trainee trainee = traineeService.showTraineeDetailsById(id);

        if (trainee != null) {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            logger.info("NOTE: IF THERE IS NO UPDATE JUST PRESS ENTER");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            scanner.nextLine();

            boolean isNameValidated = false;

            while (!isNameValidated) {
                System.out.println("Enter the updated name");
                String name = scanner.nextLine();

                if (!name.isEmpty()) {

                    if (validation.validateName(name)) {
                            trainee.setName(name);
                            isNameValidated = true;
                            logger.info("Trainee name updated");
                    } else {
                        logger.error("name should not contain numeric values");
                    }
                } else {
                    logger.info("Name not updated");
                    isNameValidated = true;
                }
            }

            boolean isMobileNumberValidated = false;

            while (!isMobileNumberValidated) {
                System.out.println("Enter the updated mobile number");
                String number = scanner.nextLine();

                if (!number.isEmpty()) {
                    long mobile = Long.valueOf(number);

                    if (validation.validateMobileNumber(mobile)) {
                            trainee.setMobileNumber(mobile);
                            logger.info("mobile number updated");
                            isMobileNumberValidated = true;

                    } else {
                        logger.error("enter a valid mobile number");
                    }

                 } else {
                    logger.info("Mobile Number not updated");
                    isMobileNumberValidated = true;
                }
            }

            System.out.println("Ente the updated role");
            String updatedRole = scanner.nextLine();
            if (!updatedRole.isEmpty()) {
               trainee.setRole(updatedRole);
            } else {
                logger.info("Role not updated");
            }

            boolean isEmailValidated = false;

            while (!isEmailValidated) {
                System.out.println("Enter the updated email");
                String email = scanner.nextLine();

                if (!email.isEmpty()) {

                    if (validation.validateEmail(email)) {
                        trainee.setEmail(email);
                        isEmailValidated = true;
                        logger.info("email updated");
                    } else {
                        logger.error("enter a valid email"); 
                    }
                } else {
                    logger.info("email not updated");
                    isEmailValidated = true;
                }
            }

            boolean isGenderValidated = false;

            while (!isGenderValidated) {
                System.out.println("Enter the updaetd gender");
                String gender = scanner.nextLine();

                if (!gender.isEmpty()) {

                    if (validation.validateGender(gender)) {
                        trainee.setGender(gender);
                        logger.info("Gender updated");
                        isGenderValidated = true;

                    } else {
                        logger.error("enter the valid gender");
                    }

                } else {
                    logger.info("gender not updated");
                    isGenderValidated = true;
                }
            }

            boolean isDobValidated = false;

            while (!isDobValidated) {
                System.out.println("Enter the updated Date of birth");
                System.out.println("Enter the date");
                String date = scanner.nextLine();
 
                if (!date.isEmpty()) {
                    int updatedDate = Integer.valueOf(date);
                    System.out.println("Enter month");
                    int updatedMonth = scanner.nextInt();
                    System.out.println("enter year");
                    int updatedYear = scanner.nextInt();

                    if (validation.validateDateOfBirth(updatedDate, updatedMonth, updatedYear)) {
                        Date dob = Date.valueOf(LocalDate.of(updatedYear, updatedMonth, updatedDate));
                        trainee.setDateOfBirth(dob);
                        logger.info("DOB updated");
                        isDobValidated = true;

                    } else {
                        logger.error("enter a valid DOB");
                    }

                } else {
                    logger.info("Date not updated");
                    isDobValidated = true;
                }
            }

            scanner.nextLine();
            System.out.println("enter the updtaed college name");
            String collegeName = scanner.nextLine();

            if (!collegeName.isEmpty()) {
                trainee.setCollegeName(collegeName);
                logger.info("collegeName updated");

            } else {
                logger.info("college name not updated");
            }

            System.out.println("Enter the updated Pass Out Year");
            String passDate = scanner.nextLine();

            if (!passDate.isEmpty()) {                
                 int updatedPassOutDate = Integer.valueOf(passDate);
                 System.out.println("Enter the updated month");
                 int updatedPassOutMonth = scanner.nextInt();
                 System.out.println("Enter the updated year");
                 int updatedPassOutYear = scanner.nextInt();
                 Date passOut = Date.valueOf(LocalDate.of(updatedPassOutYear, updatedPassOutMonth, updatedPassOutDate));
                 trainee.setPassOutYear(passOut);
                 logger.info("Pass out year updated");

            } else {
                 logger.info("pass out year not updated");
            }

            logger.info("{}", traineeService.changeTraineeDetailsById(id, trainee));

        } else {
            logger.info("Enter a valid Trainee id or trainee not available");
        }
    }

    /**
     * this method is used to assign multiple trainees for a particular
     * trainer and then assigned by calling changeTrainerDetailsById()
     * method from service class.
     * 
     */
    public static void assignTrainerToTrainee() {

        System.out.println("Enter the trainee id");
        int traineeId = scanner.nextInt();
        Trainee trainee = traineeService.showTraineeDetailsById(traineeId);
        List<Trainer> trainer = trainerService.showTrainersDetails();

        if (trainee != null) {
            trainerController.displayTrainers();
            System.out.println("Enter the trainer id exampel(1,2,3)");
            String[] trainerId = scanner.next().split(",");

            for (int i=0; i<trainerId.length; i++) {
                int id = Integer.valueOf(trainerId[i]);

                //for (int j=0; j<trainer.size(); j++) {

                    if(trainerService.showTrainerDetailsById(id) != null) {
                        trainee.getTrainerDetails().add(trainerService.showTrainerDetailsById(id));
                    } else {
                        logger.info("Trainer Not Available");
                    }
                //}
            }
            traineeService.changeTraineeDetailsById(traineeId, trainee);

        } else {
            logger.info("No Trainee available");
        }
    }
}