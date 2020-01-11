package com.microservice.phonekart.apigateway.repository;

import org.springframework.stereotype.Repository;

import com.microservice.phonekart.apigateway.microService_model.User;
import com.microservice.phonekart.apigateway.model.JWTRequestForSignUP;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

@Repository
public class MySQLRepository {

	private static SessionFactory sessionFactory;

	public MySQLRepository() {
		try {
			sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	public boolean getUser(String userName) {
		Session session = null;
		Transaction tx;
		Query query;
		try {
			session = sessionFactory.getCurrentSession();
			tx = session.beginTransaction();
			query = session.createQuery("select * from user where username  = :userName");
			query.setParameter("userName", userName);
			List<Object[]> list = query.getResultList();
			/*
			 * for (Object[] arr : list) { System.out.println(Arrays.toString(arr)); }
			 */

			return (list.size() > 0);

		} catch (Exception e) {

			e.printStackTrace();

		} finally {
			session.close();
		}
		return false;
	}
	
	public void createNewRecord(JWTRequestForSignUP newUser) {
		
		Session session = null;
		Transaction tx;
		Query query;
	
		try {
			session = sessionFactory.getCurrentSession();
	        session.beginTransaction();
	        
	        //Add new User object
	        User user = new User();
	     
	        user.setEmail(newUser.getEmail());
	        user.setName(newUser.getName());
	        user.setPassword(newUser.getPassword());
	        user.setSignUpDate(new Date());
	        user.setUserName(newUser.getUsername()); 
	        
	        //Save the employee in database
	        session.save(user);
	        session.getTransaction().commit();
		}catch(Exception e ) {
			e.printStackTrace();
		}
		finally {
			session.close();
		}
 
	}
}
