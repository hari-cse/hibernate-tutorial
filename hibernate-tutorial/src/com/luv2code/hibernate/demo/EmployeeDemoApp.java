package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Employee;

public class EmployeeDemoApp {

	public static void main(String[] args) {

		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Employee.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {
			//delete whole data
			/*
			 * 
			session = factory.getCurrentSession();
			session.beginTransaction();
			session.createQuery("delete from Employee").executeUpdate();
			session.getTransaction().commit();
			 * 
			 */
			
			//exercise.1 create the employee object
			session = factory.getCurrentSession();
			System.out.println("creating the new Employee object");
			Employee tempEmployee0 = new Employee("Paul","hehman","Amazon");
			Employee tempEmployee1 = new Employee("John","Cena","ADP");
			Employee tempEmployee2 = new Employee("Fin","Parlor","FaceBook");

			//start the employee object
			session.beginTransaction();

			//save the employee object
			System.out.println("saving the employee ...");
			session.save(tempEmployee0);
			session.save(tempEmployee1);
			session.save(tempEmployee2);
			
			//commit the transaction
			session.getTransaction().commit();

			System.out.println("creation Done !");
			
			//exercise.2: code to retrieve an object by primary key.
			session = factory.getCurrentSession();
			session.beginTransaction();
			System.out.println("Current Primary key generated " + tempEmployee0.getId());
			Employee myEmployee = session.get(Employee.class, tempEmployee0.getId());
			System.out.println("Get complete " + myEmployee);
			session.getTransaction().commit();
			System.out.println("retrieving Done !");
			 
			//exercise.3: code to query objects to find employees for a given company.
			session = factory.getCurrentSession();
			session.beginTransaction();
			List<Employee> theEmployees = session.createQuery("from Employee e where e.company = 'ADP'")
					.getResultList();
			System.out.println("\n employee selected .. ");
			displayStudents(theEmployees);
			session.getTransaction().commit();
			
			//exercise 4. code to delete an object by primary key
			session = factory.getCurrentSession();
			session.beginTransaction();
			System.out.println("\n delete Query started .. ");
			session.createQuery("delete from Employee where "+
					"id = "+ tempEmployee0.getId()).executeUpdate();
			System.out.println("\n employees availed .. ");
			List<Employee> theEmployeeList = session.createQuery("from Employee").getResultList();
			displayStudents(theEmployeeList);
			session.getTransaction().commit();
		}
		finally {
			factory.close();
		}
		
	}
	private static void displayStudents(List<Employee> theEmployees) {
		for (Employee tempEmployee : theEmployees) {
			System.out.println(tempEmployee);
		}
	}
}
