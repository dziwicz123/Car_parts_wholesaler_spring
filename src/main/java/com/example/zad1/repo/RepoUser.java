package com.example.zad1.repo;

import com.example.zad1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoUser extends JpaRepository<User,Long> {
    User findUserByEmail(String email);

}
