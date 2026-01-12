package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.stat.Statistics;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;


import com.entity.Employee;

public class Launch {
	public static void main(String[] args) {
		createDB();
		SessionFactory sf=new Configuration().configure().buildSessionFactory();
		
//		Session session = sf.openSession();
//		session.beginTransaction();
//
//		Employee emp = new Employee();
//		emp.setId(-2);
//		emp.setName("Mohit Patel");
//		emp.setSalary(5000);
//
//		session.save(emp);
//
//		session.getTransaction().commit();
//		session.close();
		
		
		Statistics stats = sf.getStatistics();
		stats.setStatisticsEnabled(true);
		
		//Session 1
		Session s1=sf.openSession();
		Employee e1=s1.get(Employee.class, 1);
		System.out.println(e1.getName());
		s1.close();
		
		//Session 2
		Session s2=sf.openSession();
		Employee e2= s2.get(Employee.class, 1);
		System.out.println(e2.getName());
		s2.close();
		
		System.out.println("Second Level Cache Hit Count = " + stats.getSecondLevelCacheHitCount());
		System.out.println("Second Level Cache Miss Count = " + stats.getSecondLevelCacheMissCount());
		
		//third type
		CacheManager cacheManager = CacheManager.getInstance();
		Cache cache = cacheManager.getCache("com.entity.Employee");

		if (cache == null) {
		    System.out.println("Cache NOT FOUND");
		} else {
		    System.out.println("Cache size: " + cache.getSize());
		    System.out.println("Cache hits: " + cache.getStatistics().cacheHitCount());
		    System.out.println("Cache misses: " + cache.getStatistics().cacheMissCount());
		}

		/*
		 ##### Correct rule (memorize):-####
        Strategy	            Entity type
        READ_ONLY	            Truly immutable data
        READ_WRITE	            Updatable data
        NONSTRICT_READ_WRITE	Rare updates
        
        #######Hiebrnate Versions:-#######
        Hibernate Version	JPA Package
        Hibernate 5.x	    javax.persistence.*
        Hibernate 6.x	    jakarta.persistence.*
        
        @@What I recommend for YOU (based on your learning stage)@@

        You are:

          Learning cache for the first time

          Still building fundamentals

          Preparing for interviews

          ✅ Use Hibernate 5 + Ehcache 2

          Once concept is crystal clear:

          Move to Hibernate 6

          JCache will feel easy
          
          
          #######Final one-line rule (memorize this)

          Hibernate 5 → hibernate-ehcache → Ehcache 2
          Hibernate 6 → JCache → Ehcache 3
        
		 */
		
		
		sf.close();
	}
	public static void createDB() {
		String driver="com.mysql.cj.jdbc.Driver";
		String url="jdbc:mysql://localhost:3306/";
		String username="Mohit";
		String password="9174";
		String db="itep16";
		try {
			Class.forName(driver);
			Connection con =DriverManager.getConnection(url,username,password);
			if(con!=null) {
				String query="create database if not exists "+db;
				Statement stmt=con.createStatement();
				stmt.execute(query);
			}else {
				System.out.println("Something went wrong..!!");
			}
		}catch(Exception e) {
			System.out.println("Exception : "+e);
		}
	}
}
