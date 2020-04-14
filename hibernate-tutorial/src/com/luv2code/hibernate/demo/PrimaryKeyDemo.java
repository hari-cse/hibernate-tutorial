package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class PrimaryKeyDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			//create 3 student objects
			System.out.println("creating the new student object");
			Student tempStudent1 = new Student("John","Doe","john@abc.com");
			Student tempStudent2 = new Student("Mary","Public","mary@abc.com");
			Student tempStudent3 = new Student("Bonita","Wall","bonita@abc.com");
			
			//start the student object
			session.beginTransaction();
			
			//save the student object
			System.out.println("saving the students ...");
			session.save(tempStudent1);
			session.save(tempStudent2);
			session.save(tempStudent3);
			
			//commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done !");
		}
		finally {
			factory.close();
		}
	}

}
