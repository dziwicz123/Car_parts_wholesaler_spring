package com.example.zad1.repo;

import com.example.zad1.model.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface RepoPart extends JpaRepository<Part,Long> {
    List<Part> findAllByPriceBetween(BigDecimal price, BigDecimal price2);
}
