package com.example.zad1;

import com.example.zad1.model.*;
import com.example.zad1.repo.*;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class Zad1ApplicationTests {
    @Autowired
    private RepoUser repoUser;

    @Autowired
    private RepoTransaction repoTransaction;

    @Autowired
    private RepoPart repoPart;

    @Autowired
    private RepoCategory repoCategory;

    @Autowired
    private RepoCarModel repoCarModel;

    private User ania;
    private User mariusz;
    private Category engines;
    private Category liquids;
    private Part engine1;
    private Part liquid1;
    private Part engine2;
    private CarModel carModel1;
    private CarModel carModel2;
    private CarModel carModel3;
    private Transaction transaction1;
    private Transaction transaction2;
    private Transaction transaction3;

    @BeforeEach
    void setupDatabase(){
        ania = repoUser.save(User.builder().fullName("Ania Mazurek").email("aniakowalski@mail.com").build());
        mariusz = repoUser.save(User.builder().fullName("Mariusz Nowak").email("mariusznowak@mail.com").build());
        engines = repoCategory.save(Category.builder().name("Engines").build());
        liquids = repoCategory.save(Category.builder().name("Liquids").build());

        engine1 = repoPart.save(Part.builder().name("engine 1").category(engines).price(new BigDecimal("10000")).build());
        liquid1 = repoPart.save(Part.builder().name("liquid 1").category(liquids).price(new BigDecimal("90")).build());
        engine2 = repoPart.save(Part.builder().name("engine 2").category(engines).price(new BigDecimal("15000")).build());

        List<Part> parts = new ArrayList<>();
        parts.add(engine1);
        parts.add(liquid1);
        parts.add(engine2);
        carModel1 = repoCarModel.save(CarModel.builder().manufacturer("Marka 1").name("Model 1").yearOfManufacture(2015).parts(parts).build());
        carModel2 = repoCarModel.save(CarModel.builder().manufacturer("Marka 1").name("Model 2").yearOfManufacture(2009).parts(parts).build());
        carModel3 = repoCarModel.save(CarModel.builder().manufacturer("Marka 2").name("Model 1").yearOfManufacture(2022).parts(parts).build());

        List<PartsInTransaction> transactionItems = new ArrayList<>();
        transactionItems.add(PartsInTransaction.builder().part(engine1).quantity(1).build());
        transactionItems.add(PartsInTransaction.builder().part(engine2).quantity(4).build());

        transaction1 = repoTransaction.save(Transaction.builder().date(new Date()).user(ania).partsInTransactions(transactionItems).build());

        List<PartsInTransaction> transactionItems1 = new ArrayList<>();
        transactionItems1.add(PartsInTransaction.builder().part(engine1).quantity(2).build());
        transactionItems1.add(PartsInTransaction.builder().part(liquid1).quantity(3).build());

        transaction2 = repoTransaction.save(Transaction.builder().date(new Date()).user(ania).partsInTransactions(transactionItems1).build());

        List<PartsInTransaction> transactionItems2 = new ArrayList<>();
        transactionItems2.add(PartsInTransaction.builder().part(liquid1).quantity(2).build());
        transactionItems2.add(PartsInTransaction.builder().part(engine2).quantity(1).build());

        transaction3 = repoTransaction.save(Transaction.builder().date(new Date()).user(mariusz).partsInTransactions(transactionItems2).build());
    }

    @Test
    void findAmountOfTransactions() {
        long orderCount = repoTransaction.count();
        assertEquals(3, orderCount);
    }

    @Test
    void findAmountOfTransactionsByProduct() {
        List<Transaction> orders = repoTransaction.findByPartsInTransactions_Part(liquid1);
        assertEquals(2, orders.size());
    }

    @Test
    void findAmountOfTransactionsByUser() {
        List<Transaction> orders = repoTransaction.findByUser(ania);
        assertEquals(2, orders.size());
    }

    @Test
    void findUserByEmail() {
        User user = repoUser.findUserByEmail(ania.getEmail());
        assertEquals(ania, user);
    }

    @Test
    void findPartsInPriceRange() {
        List<Part> parts = repoPart.findAllByPriceBetween(new BigDecimal("10.00"), new BigDecimal("11000.00"));
        assertEquals(2, parts.size());
    }

}
