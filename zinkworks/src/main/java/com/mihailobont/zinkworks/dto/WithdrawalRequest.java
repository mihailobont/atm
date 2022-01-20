package com.mihailobont.zinkworks.dto;

import lombok.Getter;

@Getter
public class WithdrawalRequest {

    private Long id;
    private Long accountNumber;
    private Long amountToWithdraw;
    private int pin;
}
