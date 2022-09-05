package com.element5.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.sql.Date;

import java.time.DateTimeException;
import java.time.LocalDate;

import com.element5.exceptions.InvalidExperienceException;


/**
 * <h2> Element5 Employee portal </h2>
 *
 * <p> 
 * This class has the methods to validate name, email, mobilenumber etc.
 * </p>
 *
 * @author Narendranath
 * @version 1.0
 * @since 2022-08-26
 *
 */
public class Utils {

    /**
     * This method is used to validate wheather the Employee name consists of numbers or not.
     *
     * @param name The name parameter is passed in the validateName() method,
     * the parameter type is string and this consists of the employee's name.
     *
     * @return boolean This will return true is the name doesn't contains any numeric values
     * and return false if the name contains any numeric values.
     *
     */
    public boolean validateName(String name) {

        char[] userName = name.toCharArray();
        boolean nameCheck = true;

        for (char check : userName) {

            if(Character.isDigit(check)) {
                nameCheck = false;
                break;
            }
        }
        return nameCheck;
    }

    /**
     * This will validate wheather the mobile number is of length 10.
     *
     * @param number The parameter number is passed in the validateMobileNumber() method, 
     * the parameter type is long and the number consists of the employee mobilenumber.
     * 
     * @return boolean Tshis will return true is the mobile number length is 10 else will
     * return false is the mobile number is not in length 10.
     * 
     */
    public boolean validateMobileNumber(long number) {

        String mobileNumber = String.valueOf(number);
        boolean checkNumber = true;

        if (mobileNumber.length() != 10) {
            checkNumber = false;
        }
        return checkNumber;
    }

    /**
     * This method is used to validate the gender details of an employee.
     *
     * @param gender The parameter gender is passed in the validateGender() method
     * the parameter type is string and the parameter consists of the employee gender.
     * 
     * @return boolean This method will return true if the employee gender is either male 
     * or female else false if someother gender is given.
     *
     */
    public boolean validateGender(String gender) {

        boolean checkGender = false;

        if (gender.equals("male")) {
            checkGender = true;

        } else if (gender.equals("female")) {
            checkGender = true;
        }

        return checkGender;
    }

    /**
     * This method is used to validate employee's email.
     *
     * @param email This email parameter is passed into the vaildateEmail() method
     * the parameter type is string and it consists of employee email.
     *
     * @return boolean This will return true is the email is according to the pattern else false
     * if the email is not in the pattern.
     *
     */
    public boolean validateEmail(String email) {

        String regex = "^[A-Za-z0-9+_.-]+@(.+)+(com)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher  = pattern.matcher(email); 
        boolean checkEmail = matcher.matches();
    
        return checkEmail;  
    }

    /**
     * This method is used to validate the date of birth of the employee.
     *
     * @param date This date is passed to the method validaetDateOfBirth()
     * the type is int and it holds the date.
     *
     * @param month This month is passed to the method validateDateOfBirth()
     * the type is int and it holds the month.
     *
     * @param year This year is passed to the method vaildateDateOfBirth()
     * the type is int and it holds year.
     *
     * @return boolean This method will return true if the date month and year are correct else false.
     *
     */
    public boolean validateDateOfBirth(int date, int month, int year) {

        boolean isDobValidated = false;

        try {
            LocalDate.of(year, month, date);
            isDobValidated = true; 

        } catch (DateTimeException e) {
            isDobValidated = false;
        }
        return isDobValidated;
    }

    /**
     * This method is used to validate employee's Experience.
     *
     * @param age This age parameter is passed into the vaildateExperience() method
     * the parameter type is int and it consists of employee age.
     *
     * @param experience This experience parameter is passed into the validateExperience() method
     * the parameter type is int and consists of the employee experience.
     *
     * @return boolean This will return true is the experience is according to the condition else false
     * if the experience is not accod=rding to the condition.
     *
     */
    public boolean validateExperience(int age, int experience) throws InvalidExperienceException {

        boolean isExperienceValidated =false;

        if (age <=23 && experience == 0) {
            isExperienceValidated = true;

        } else if(age > 23 && (experience >= 0 && experience <= age - 23)){
            isExperienceValidated = true;

        } else {
            throw new InvalidExperienceException("Experience is not valid for this age");
        }

        return isExperienceValidated;
    }
}
