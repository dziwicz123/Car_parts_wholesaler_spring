package com.example.zad1.repo;

import com.example.zad1.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoCategory extends JpaRepository<Category,Long> {
}
