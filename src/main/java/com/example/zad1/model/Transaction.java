package com.example.zad1.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;


@Entity
@Table(name = "transactions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Transaction {
    @Id
    @Column(name = "transaction_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Date date;

    @OneToMany(orphanRemoval = true,
            cascade = CascadeType.ALL)
    List<PartsInTransaction> partsInTransactions;
}
