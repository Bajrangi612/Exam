package com.exam.service;


import com.exam.domain.UserRole;
import com.exam.domain.Users;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    public ResponseEntity<?> createUser(Users users, List<UserRole> userRoleList);
    public ResponseEntity<?> getUserByUserName(String userName);
    public ResponseEntity<?> deleteUserByUserId(Long userId);

}
