package com.microservice.phonekart.apigateway.repository;

import org.springframework.stereotype.Repository;


import java.util.Arrays;
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
			sessionFactory =   new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@SuppressWarnings("deprecation")
	public void getOrders() {
		Session session = null;
		Transaction tx;
		Query query; 
		try {
			
			session  = sessionFactory.getCurrentSession();

			 tx = session.beginTransaction();

			 query = session
					.createQuery("select c.customerName , count(c.customerName) from Customers c join Orders "
							+ "o on c.customerNumber = o.customerNumber group by c.customerName");

			System.out.println(" ---------------------------------------");

			System.out.println(" ---------------------------------------");

			List<Object[]> list = query.getResultList();
			
			for (Object[] arr : list) {
				System.out.println(Arrays.toString(arr));
			}
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}finally {
			session.close();
		}

	}
}
