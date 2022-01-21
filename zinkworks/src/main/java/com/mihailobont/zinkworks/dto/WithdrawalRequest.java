package com.mihailobont.zinkworks.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class WithdrawalRequest {

    private Long id;
    private Long accountNumber;
    private Long amountToWithdraw;
    private int pin;
}
