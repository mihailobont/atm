package com.mihailobont.zinkworks.dto;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BalanceResponse{

    private Long accountNumber;
    private Long maximumWithdrawalAmount;
    private String message;

    public BalanceResponse(Long accountNumber, String message) {
        this.accountNumber = accountNumber;
        this.message = message;
    }
}
