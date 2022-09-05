package com.element5.controller;

import java.sql.Date;
import java.util.List;
import java.util.HashSet;
import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.element5.exceptions.InvalidExperienceException;
import com.element5.model.Trainer;
import com.element5.model.Trainee;
import com.element5.service.TrainerService;
import com.element5.service.impl.TrainerServiceImpl;
import com.element5.service.TraineeService;
import com.element5.service.impl.TraineeServiceImpl;
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
public class TrainerController {
    private static Scanner scanner = new Scanner(System.in);
    private static TraineeController traineeController = new TraineeController();
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

        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Select an Option To proceed \n")
                         .append("1) Display Trainer Details \n")
                         .append("2) Insert Trainer Details \n")
                         .append("3) Delete Trainer Details \n")
                         .append("4) Show Specific Trainer Details \n")
                         .append("5) Update Trainer Details \n")
                         .append("6) Assign Trainees To Trainer \n")
                         .append("12) Exit");     
            System.out.println(stringBuilder);
            String menu = scanner.next();
            int option = Integer.valueOf(menu);

            switch(option) {
                case 1:
                    displayTrainers();
                    displayMenu();
                    break;
                case 2:
                    getTrainerDetails();
                    displayMenu();
                    break;
                case 3:
                    deleteTrainer();
                    displayMenu();
                    break;
                case 4:
                    displayTrainerById();
                    displayMenu();
                    break;
                case 5:
                    updateTrainers();
                    displayMenu();
                    break;
                case 6:
                    assignTraineesToTrainer();
                    displayMenu();
                    break;
                case 12:
                    logger.info("Thank ypu for using employee portal");
                    break;
                default:
                    logger.error("Please enter a valid option");
                    displayMenu();
                    break;
            }
        } catch (NumberFormatException e) {
            logger.error("Invalid input");
            displayMenu();
        }
    }

    /**
     *
     * This method is used to display all the Trainers details.
     *
     */
    public static void displayTrainers() {
        List<Trainer> trainers = trainerService.showTrainersDetails();

        if (trainers.size() != 0) {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Trainers Details~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

            trainers.forEach(trainer->
                {
                     StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Trainer name: "+ trainer.getName() + "\n")
                                 .append("Trainer email: "+ trainer.getEmail() + "\n")
                                 .append("Trainer id: "+ trainer.getId() + "\n")
                                 .append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    System.out.println(stringBuilder);
                    
                 });
        } else {
            logger.info("No trainers Details");
        }
    }
    
    /**
     *
     * This method is used to get the Trainer's details, validate the trainer's details
     * and the details are added in the dao by calling the service class.
     *
     */
    public static void getTrainerDetails() {
        Trainer trainer = new Trainer();
        scanner.nextLine();
        boolean isNameValidated = false;

        while (!isNameValidated) {
            System.out.println("Enter the name of the Trainer");
            String name = scanner.nextLine();

            if ((!name.isEmpty()) && (validation.validateName(name))) {
                trainer.setName(name);
                isNameValidated = true;
                logger.info("Trainer name added");
            } else {
                logger.error("Name not entered enter a name to proceed next");
            }
        }

        boolean isNumberValidated = false;

        while (!isNumberValidated) {
            System.out.println("Enter Mobile Number");
            Long mobileNumber = scanner.nextLong();

            if (validation.validateMobileNumber(mobileNumber)) {
                trainer.setMobileNumber(mobileNumber);
                isNumberValidated = true;
                logger.info("Mobile Number added");
            } else {
                logger.error("Number not a valid number");
            }
        }

        System.out.println("Enter the role");
        String role = scanner.nextLine();
        role+= scanner.nextLine();
        trainer.setRole(role);

        boolean isDobValidated = false;
        int date = 0, month = 0, year = 0;

        while (!isDobValidated) {

            System.out.println("Enter Date Of Birth");
            System.out.println("Enter the date");
            date = scanner.nextInt();
            System.out.println("Enter the month");
            month = scanner.nextInt();
            System.out.println("Enter the year");
            year = scanner.nextInt();

            if (validation.validateDateOfBirth(date, month, year)) {
                Date dob= Date.valueOf(LocalDate.of(year, month, date));
                trainer.setDateOfBirth(dob);
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
                trainer.setEmail(email);
                isEmailValidated = true;
                logger.info("Trainer Email Added");
            } else {
                logger.error("Enter a valid Email");
            }
        }

        boolean isGenderValidated = false;

        while (!isGenderValidated) {
            System.out.println("Enter gender");
            String gender = scanner.nextLine();

            if (validation.validateGender(gender)){
                trainer.setGender(gender);
                isGenderValidated = true;
                logger.info("Trainer Gender added");
            } else {
                logger.error("Enter a valid gender");
            }
        }

        System.out.println("Enter Previous Company Name");
        String companyName = scanner.nextLine();
        trainer.setPreviousCompanyName(companyName);
        logger.info("Company Name Added");

        boolean isExperienceValidated = false;

        while (!isExperienceValidated) {
            System.out.println("Enter Experience");
            int experience = scanner.nextInt();

            try {

                if (validation.validateExperience(age, experience)) {
                    trainer.setExperience(experience);
                    isExperienceValidated = true;
                    logger.info("Trainer Experience Added");
                } 
            } catch(InvalidExperienceException e) {
                logger.error(e.getMessage());
            }
        }
        logger.info("{}", trainerService.addTrainerDetails(trainer));
    }

    /**
     * This method is used to remove the paritcular trainer's details
     * by calling the deleteTrainerDetailsById() method from service.
     *
     */
    public static void deleteTrainer() {

        System.out.println("enter the Trainer id to be Deleted");
        int id = scanner.nextInt();
        logger.info("{}", trainerService.deleteTrainerDetails(id));
    }


    /**
     * This method is used to display the paritcular trainee's details
     * by calling the showTrainerDetailsById() method from service.
     *
     */
    public static void displayTrainerById() {

        try {
            System.out.println("enter the Trainer id to be displayed");
            int id = scanner.nextInt();
            Trainer trainer =  trainerService.showTrainerDetailsById(id);

            if (trainer != null && !trainer.getIsDeleted()) {
                StringBuilder trainerDetails = new StringBuilder();
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~Trainer Details~~~~~~~~~~~~~~~~~");
                trainerDetails.append("Name: " + trainer.getName() + "\n")
                             .append("Email: " + trainer.getEmail() + "\n");
                System.out.println(trainerDetails);
                List<Trainee> trainee = trainer.getTraineeDetails();

                if (trainee.size() != 0){
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Trainee Details~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

                    for(int i=0; i<trainee.size(); i++) {

                        if (!trainee.get(i).getIsDeleted()) {
                            StringBuilder traineeDetails = new StringBuilder();
                            traineeDetails.append("name: " +trainee.get(i).getName() + "\n")
                                          .append("email: " +trainee.get(i).getEmail() + "\n")
                                          .append("Trainee Id: " + trainee.get(i).getId() + "\n")
                                          .append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                            System.out.println(traineeDetails);
                        }
                }

                 } else {
                logger.info("No trinees has been assigned to this trainer");
                }
            } else {
                logger.info("No trainer details available or trainer id invalid");
            }
        } catch (NullPointerException e) {
            logger.info("No trainer");
        }
    }

    /**
     * This method is used to update the paritcular trainer's details
     * by calling the changeTrainerDetailsById() method from service.
     *
     */
    public static void updateTrainers() {
        System.out.println("enter the Trainer id to be updated");
        int id = scanner.nextInt();
        Trainer trainer =  trainerService.showTrainerDetailsById(id);

        if (trainer != null) {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            logger.info("NOTE: IF THERE IS NO UPDATE JUST PRESS ENTER");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            scanner.nextLine();

            boolean isNameValidated = false;

            while (!isNameValidated) {
                System.out.println("Enter the updated name");
                String name = scanner.nextLine();

                if (!name.isEmpty() && validation.validateName(name)) {
                    trainer.setName(name);
                    isNameValidated = true;
                    logger.info("Trainer name updated");
                } else if(!validation.validateName(name)) {
                    logger.error("name should not contain numeric values");
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
                            trainer.setMobileNumber(mobile);
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
                trainer.setRole(updatedRole);
            } else {
                logger.info("Role not updated");
            }

            boolean isEmailValidated = false;

            while (!isEmailValidated) {
                System.out.println("Enter the updated email");
                String email = scanner.nextLine();

                if (!email.isEmpty()) {

                    if (validation.validateEmail(email)) {
                        trainer.setEmail(email);
                        isEmailValidated = true;
                        logger.info("email updaetd");
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
                        trainer.setGender(gender);
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
                        trainer.setDateOfBirth(dob);
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

            System.out.println("Enter the updated company name");
            String companyName = scanner.nextLine();

            if (!companyName.isEmpty()) {
                trainer.setPreviousCompanyName(companyName);
                logger.info("company name updated");
            } else {
                logger.info("Company name not updated");
            }
                
            boolean isExperienceValidated = false;

            while (!isExperienceValidated) {
                System.out.println("Enter the updated experience");
                String experience = scanner.nextLine();

                if (!experience.isEmpty()) {
                    int age = Period.between(trainer.getDateOfBirth().toLocalDate(), LocalDate.now()).getYears();
                    int updatedExperience = Integer.valueOf(experience);

                    try {

                        if (validation.validateExperience(age, updatedExperience)) {
                            trainer.setExperience(updatedExperience);
                            isExperienceValidated = true;
                        }
                     } catch (InvalidExperienceException e) {
                        logger.error("{}", e.getMessage());
                     }
                 } else {
                     logger.info("Experience not Updated");
                     isExperienceValidated = true;
                 }
            } 
            logger.info("{}", trainerService.changeTrainerDetailsById(id, trainer));
        } else {
                logger.error("Enter a valid Trainer id");
        }
    }

    /**
     * this method is used to assign multiple trainees for a particular
     * trainer and then assigned by calling changeTrainerDetailsById()
     * method from service class.
     * 
     */
    public static void assignTraineesToTrainer() {

        List<Trainee> trainee = traineeService.showTraineesDetails();
        System.out.println("enter Trainer id");
        int trainerId = scanner.nextInt();
        Trainer trainer = trainerService.showTrainerDetailsById(trainerId);

        if (trainer != null) {
            HashSet<String> message = new HashSet<>();
            traineeController.displayTrainees();
            System.out.println("enter Trainee id (example 1,2,3)");
            String[] traineeId = scanner.next().split(",");

            for (int i=0; i < traineeId.length; i++) {
                int id = Integer.valueOf(traineeId[i]);

                for(int j=0; j<trainee.size(); j++) {

                    if(trainee.get(j).getId() == id) {
                        trainer.getTraineeDetails().add(trainee.get(j));
                    } else {
                        message.add(String.format("Trainee %s is not available ", traineeId[i]));
                    }
                }
            }

            if(message.size() != 0) {
                message.forEach(info-> logger.info("{}", info));
            }
            trainerService.changeTrainerDetailsById(trainerId, trainer);
        } else {
            logger.info("Trainer not available");
        }
    }
}