package com.element5.utils;

import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.element5.model.Trainer;
import com.element5.model.Trainee;

/**
 * <h2> Element5 Employee portal </h2>
 *
 * <p> 
 * This class is used to create a session factory and this class is used
 * in trainerDao and in traineeDao.
 * </p>
 *
 * @author Narendranath
 * @version 1.0
 * @since 2022-08-26
 *
 */
public class Factory {

    private static SessionFactory factory;
    private static Logger logger = LoggerFactory.getLogger(Factory.class);

    /** 
     * this method is used to configure the tables and create the session
     * factory and thos class is is used in Dao.
     *
     * @return SessionFactory after the successful configuration the method
     * will return session factory object.
     * 
     */
    public static SessionFactory getFactory() {

        try {

            factory = new Configuration().
                             configure().
                             addPackage("com.element5.model").
                             addAnnotatedClass(Trainer.class).
                             addAnnotatedClass(Trainee.class).buildSessionFactory();

        } catch (Throwable ex) {

            logger.error("Failed to create sessoionFactory object"+"{}", ex);
            throw new ExceptionInInitializerError(ex);
        }
        return factory;
    }
}
                 