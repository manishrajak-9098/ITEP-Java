package com.jpa.main;

import java.util.List;

import com.jpa.entity.Aspirant;
import com.jpa.entity.Course;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Hibernate{
	public static void main(String args[]) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mypunit");
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
			
			Aspirant a1 = new Aspirant();
			a1.setStudname("Andrew Anderson");
			
			Aspirant a2 = new Aspirant();
			a2.setStudname("Peter Parker");
			
			Course c1 = new Course();
			c1.setCoursename("C Programming Language");
			
			Course c2 = new Course();
			c2.setCoursename("Java Programming Language");
			
			a1.addCourse(c1);
			a1.addCourse(c2);
			
			a2.addCourse(c1);
			try {	
				em.persist(a1);
				em.persist(a2);
				em.persist(c1);
				em.persist(c2);
				
				tx.commit();
				
				Course course = em.find(Course.class, 1);
				List<Aspirant> aspirant = course.getAspitant();
				for(Aspirant aspi: aspirant)
					System.out.println("Student Name : "+aspi.getStudname());
				
			}catch(Exception e) {
				if(tx!=null)
					tx.rollback();
				System.out.println("Rollback takes place..!!");
			}
			em.close();
		emf.close();
	}
}