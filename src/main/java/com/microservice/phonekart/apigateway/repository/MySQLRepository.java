package com.microservice.phonekart.apigateway.repository;

import org.springframework.stereotype.Repository;

import com.microservice.phonekart.apigateway.microService_model.User;
import com.microservice.phonekart.apigateway.model.JWTRequestForSignUP;


import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

@Repository
public class MySQLRepository {

	private final SessionFactory sessionFactory = buildSessionFactory();

	public MySQLRepository() {
	}
	
	private static SessionFactory buildSessionFactory() 
    {
        try
        {
            // Create the SessionFactory from hibernate.cfg.xml
            return new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

	@SuppressWarnings("deprecation")
	public boolean getUser(String userName) {
		Session session = null;
		Transaction tx;
		Query query;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			query = session.createQuery("select user_id from User where username  = :userName");
			query.setParameter("userName", userName);
			List<Object[]> list = query.getResultList();
			/*
			 * for (Object[] arr : list) { System.out.println(Arrays.toString(arr)); }
			 */

			return (list.size() > 0);

		} catch (Exception e) {

			e.printStackTrace();

		} finally {
		//	session.close();
		}
		return false;
	}
	
	public void createNewRecord(JWTRequestForSignUP newUser) {
		
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
	        session.beginTransaction();
	        
	        //Add new User object
	        User user = new User();
	     
	        user.setUserName(newUser.getUsername()); 
	        user.setEmail(newUser.getEmail());
	        user.setName(newUser.getName());
	        user.setPassword(newUser.getPassword());
	        user.setSignUpDate(new Date());
	        
	        //Save the employee in database
	        session.save(user);
	        session.getTransaction().commit();
		}catch(Exception e ) {
			e.printStackTrace();
		}
		finally {
		//	session.close();
		}
 
	}
}
