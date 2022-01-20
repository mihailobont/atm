package com.mihailobont.zinkworks.dto;

import lombok.Getter;

@Getter
public class BalanceRequest {

    private Long id;
    private Long accountNumber;
    private int pin;
}
