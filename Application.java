import java.util.Scanner;
import java.util.InputMismatchException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.element5.controller.TrainerController;
import com.element5.controller.TraineeController;

/**
 *<h2> Employee Portal </h2>
 * This class is used to display the menu and calls 
 * the TrainerController or Traineecontroller class for the required method selected.
 *
 * @author Narendranath.s
 * @version 1.0
 * 
 */
public class Application {

    private static Scanner scanner = new Scanner(System.in);
    private static TrainerController tr = new TrainerController();
    private static TraineeController te = new TraineeController();
    private static Logger logger = LoggerFactory.getLogger(Application.class);
    public static void main(String args[]) {

        System.out.println("Welcome to Element5 Employee Portal");
        employeeSelection();  
    }

    public static void employeeSelection() {
    
        System.out.println("Select An Employee");
        System.out.println("1) Trainer");
        System.out.println("2) Trainee");
        System.out.println("3) Exit");
        int option = scanner.nextInt();
        switch(option) {

            case 1:
                tr.displayMenu();
                employeeSelection();
                break;

            case 2:
                te.displayMenu();
                employeeSelection();
                break;

            case 3:
                System.out.println("Thank You");
                break;

            default:
                logger.error("Please Enter a valid option");
                employeeSelection();
                break;
        }
    }
}