package com.hibernate.main;

import com.hibernate.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class Hibernate{
	public static void main(String args[]){
		EntityManagerFactory emf =  Persistence.createEntityManagerFactory("mypunit");
	 	EntityManager em = emf.createEntityManager();
	 	EntityTransaction tx = null;
	 	try {
		 	tx = em.getTransaction();
		 	tx.begin();
		 	
			User user = new User();
			user.setUsername("Andrew Anderson");
			user.setEmail("andrew@gmail.com");
			user.setPassword("andrew@123");
		 

		 	CriteriaBuilder criteriaBuilder =  em.getCriteriaBuilder();
		 	CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
		 	Root<User> root =  query.from(User.class);
		 	query.select(root);
		 	
		 	TypedQuery<User> q =  em.createQuery(query);
	 
		 	
		 	tx.commit();
	 	}catch(Exception e) {
	 		if(tx!=null)
	 			tx.rollback();
	 		System.out.println("Rollback takes place..!!");
	 		System.out.println("Exception : "+e);
	 	}
	 	em.close();
	 	emf.close();
	}
}