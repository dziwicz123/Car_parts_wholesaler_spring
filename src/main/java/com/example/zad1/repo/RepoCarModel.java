package com.example.zad1.repo;

import com.example.zad1.model.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoCarModel extends JpaRepository<CarModel,Long> {
}
