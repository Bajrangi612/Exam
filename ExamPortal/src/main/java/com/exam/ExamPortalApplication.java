package com.exam;

import com.exam.domain.Role;
import com.exam.domain.UserRole;
import com.exam.domain.Users;
import com.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ExamPortalApplication {


	public static void main(String[] args) {
		SpringApplication.run(ExamPortalApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//		Role role = new Role();
//		role.setRoleName("ADMIN");
//
//
//		Users users = new Users();
//		users.setUserName("123");
//		users.setEmail("abc@gmail.com");
//		users.setPassword("abc");
//		users.setFirstName("Ram");
//		users.setLastName("Kumar");
//
//		List<UserRole> userRoles = new ArrayList<>();
//
//		UserRole userRole = new UserRole();
//		userRole.setRole(role);
//		userRole.setUsers(users);
//		userRoles.add(userRole);
//		Users users1 = userService.createUser(users,userRoles);
//		System.out.println("UserName is :"+users1.getUserName());
//	}
}
