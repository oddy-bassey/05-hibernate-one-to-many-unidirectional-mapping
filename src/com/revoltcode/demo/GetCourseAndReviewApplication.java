package com.revoltcode.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.revoltcode.demo.entity.Course;
import com.revoltcode.demo.entity.Instructor;
import com.revoltcode.demo.entity.InstructorDetail;
import com.revoltcode.demo.entity.Review;

public class GetCourseAndReviewApplication {
	
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
			 
			//get course 
			Course course = session.get(Course.class, 10);
			
			if(course != null) {
				System.out.println(">> Course: "+course);
				System.out.println(">> Course Review: "+course.getReviews());
			}else {
				System.out.println("No such course Found"); 
			}
			 
			
			session.getTransaction().commit();
			
		}catch (Exception e) {
			// TODO: handle exception
		}finally {
			session.close();
			factory.close();
		}
	}
}
