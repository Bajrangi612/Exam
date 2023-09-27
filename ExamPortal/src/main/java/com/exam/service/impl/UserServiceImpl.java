package com.exam.service.impl;

import com.exam.controller.UserController;
import com.exam.domain.Role;
import com.exam.domain.UserRole;
import com.exam.domain.Users;
import com.exam.repository.RoleRepository;
import com.exam.repository.UsersRepository;
import com.exam.response.ResponseDomain;
import com.exam.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private RoleRepository roleRepository;
    private static final Logger log = LogManager.getLogger(UserServiceImpl.class);



    @Override
    public ResponseEntity<?> createUser(Users users, List<UserRole> userRoleList) {
        log.info("UserServiceImpl ::: create user ::: creating user ");
        Users users1 = null;
        try {
            users1 = usersRepository.findByUsername(users.getUsername());
            if (users1 != null) {
                log.info("User Already Exist !!");
                return ResponseDomain.badRequest("User Already Exist !!");
            } else {
                if (!userRoleList.isEmpty()) {
                for (UserRole role : userRoleList) {
                   Role role1 = roleRepository.findByRoleName(role.getRole().getRoleName());
                   if(role1 != null)
                       userRoleList.get(0).setRole(role1);
                   else
                    roleRepository.save(role.getRole());
                }
                }
//                users.setUserRoles(userRoleList);
                users.getUserRoles().addAll(userRoleList);
                users1 = usersRepository.save(users);
            }
            log.info("Exit ::: UserServiceImpl ::: create user :::  user created ");
            return  ResponseDomain.successResponse("User Created Successfully..");
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseDomain.badRequest("Something went wrong !!");
        }


    }

    @Override
    public ResponseEntity<?> getUserByUserName(String userName) {
        log.info("finding user details by UserName");
        Users users1 = usersRepository.findByUsername(userName);
        if (users1 != null)
            return new ResponseEntity<>(users1, HttpStatus.OK);
        else return new ResponseEntity<>("User not found !!", HttpStatus.BAD_GATEWAY);
    }

    @Override
    public ResponseEntity<?> deleteUserByUserId(Long userId) {
       try {
           usersRepository.deleteById(userId);
           return new ResponseEntity<>("User Deleted Successfully..", HttpStatus.OK);

       }catch (Exception e) {
           return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
       }
    }
}
