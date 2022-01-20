package com.mihailobont.zinkworks.modal;

import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "accounts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "account_number", nullable = false)
    private Long accountNumber;

    @Column(name = "pin", nullable = false)
    private int pin;

    @Column(name = "opening_balance")
    private Long openingBalance;

    @Column(name = "overdraft")
    private Long overdraft;

}
