package com.exam.controller;

import com.exam.domain.Role;
import com.exam.domain.UserRole;
import com.exam.domain.Users;
import com.exam.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
    @Autowired
    private UserService userService;
    private static final Logger log = LogManager.getLogger(UserController.class);

    @PostMapping("/")
    public ResponseEntity<?> createUser(@RequestBody Users users) {
        log.info("userService ::: createUser :::");
        List<UserRole> userRoleList = new ArrayList<>();
        Role role = new Role();
//        role.setRoleId(44l);
        role.setRoleName("NORMAL");

        UserRole userRole = new UserRole();
        userRole.setRole(role);
        userRole.setUsers(users);
        userRoleList.add(userRole);
        log.info("Exit from User Controller");
        return userService.createUser(users, userRoleList);

    }

    @GetMapping("/{userName}")
    public ResponseEntity<?> getUserByUserName(@PathVariable String userName) {
        return userService.getUserByUserName(userName);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long userId) {
        return userService.deleteUserByUserId(userId);
    }

}
