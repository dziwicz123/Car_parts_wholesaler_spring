package com.example.zad1.repo;

import com.example.zad1.model.Part;
import com.example.zad1.model.Transaction;
import com.example.zad1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepoTransaction extends JpaRepository<Transaction,Long> {

    List<Transaction> findByPartsInTransactions_Part(Part part1);

    List<Transaction> findByUser(User user);
}
