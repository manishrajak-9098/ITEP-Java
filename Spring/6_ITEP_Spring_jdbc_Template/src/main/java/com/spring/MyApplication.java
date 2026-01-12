package com.spring;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.spring.Dao.UserDao;
import com.spring.config.AppConfig;
import com.spring.model.User;

public class MyApplication {
public static void main(String args[]) {
	
	ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
	UserDao userDao = context.getBean(UserDao.class);
	 
	userDao.addUser(new User (101, "andrew anderson",	    "andrew@gmail.com",    "indore"));
	userDao.addUser(new User (102, "peter parker", 	  		"peter@gmail.com",	   "bhopal"));
	userDao.addUser(new User (103, "Mathew math",     		"mathew@gmail.com",    "jabalpur"));
	userDao.addUser(new User (104, "jack jackson",    		"jack@gmail.com", 	   "ujjain"));
	userDao.addUser(new User (105, "simon sin",       		"simon@gmail.com",	   "narsinghpur"));
	userDao.updateUser(new User(102,"Peter Parker updated", "peter123@gmail.com",  "Dewas MP"));
	System.out.println("\nData updated successfully");
	
	userDao.deleteUser(103);
	System.out.println("\nData deleted successfully");
	
	User user = userDao.getUser(104);
	System.out.println("\nUser : "+user);
	
	List<User> users = userDao.getAllUser();
	System.out.println("\nAll users \n");
	for(User u : users) {
		System.out.println("User : "+u);		
	}

}
	
}
