package com.element5.exceptions;

import java.lang.Exception;

/**
 * <h2> Element5 Employee portal </h2>
 *
 * <p> 
 * This class is used to create InvalidExperienceException.
 * </p>
 *
 * @author Narendranath
 * @version 1.0
 * @since 2022-08-26
 *
 */
public class InvalidExperienceException extends Exception{

    public InvalidExperienceException(String experience){

        super(experience);
    }
}
