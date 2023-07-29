package com.example.wallet.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "wallet")
@Builder
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Size(min = 3, max = 50)
    private String firstName;

    @Column(nullable = false)
    @Size(min = 3, max = 50)
    private String lastName;

    @Column(nullable = false)
    @Size(min = 3, max = 50)
    private String middleName;

    @Column(nullable = false, unique = true)
    @Size(max = 50)
    private String email;

    @Column(nullable = false)
    @Size(min = 10, max = 10)
    private String accountNumber;

    private Double balance;

    private String status;
}
