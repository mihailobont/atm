package com.mihailobont.zinkworks.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BalanceResponse{

    private Long accountNumber;
    private Long maximumWithdrawalAmount;
    private String message;

    public BalanceResponse(Long accountNumber, String message) {
        this.accountNumber = accountNumber;
        this.message = message;
    }
}
