package com.exam.repository;

import com.exam.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository  extends JpaRepository<Users,Long> {
    Users findByUsername(String username);
}
