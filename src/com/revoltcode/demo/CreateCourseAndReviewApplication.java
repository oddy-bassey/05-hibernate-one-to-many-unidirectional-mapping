package com.revoltcode.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.revoltcode.demo.entity.Course;
import com.revoltcode.demo.entity.Instructor;
import com.revoltcode.demo.entity.InstructorDetail;
import com.revoltcode.demo.entity.Review;

public class CreateCourseAndReviewApplication {
	
	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try { 
			session.beginTransaction();
			 
			//create some course
			System.out.println("Creating the course and reviews");
			Course course = new Course("Java - becoming a renound programmer");
			
			//add some reviews
			course.addReview(new Review("Great course!"));
			course.addReview(new Review("Challenging sometimes but definitely worth it"));
			course.addReview(new Review("Really love the course! An absolute life saver."));
			
			System.out.println(">> Course: "+course);
			System.out.println(">> Course Review: "+course.getReviews());
			
			//save the course ... and leverage the cascade all :-)
			System.out.println("Saving the course");
			session.save(course);
			
			session.getTransaction().commit();
			
		}catch (Exception e) {
			// TODO: handle exception
		}finally {
			session.close();
			factory.close();
		}
	}
}
