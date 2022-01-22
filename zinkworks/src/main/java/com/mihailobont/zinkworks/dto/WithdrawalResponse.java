package com.mihailobont.zinkworks.dto;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@ToString
public class WithdrawalResponse {

    private Long accountNumber;
    private Long amountWithdraw;
    private Long[] numberOfNotesWithdraw;
    private String message;

    public WithdrawalResponse(String message) {
        this.message = message;
    }
    public WithdrawalResponse(Long accountNumber, Long amountWithdraw, Long[] numberOfNotesWithdraw, String message) {
        this.accountNumber = accountNumber;
        this.amountWithdraw = amountWithdraw;
        this.numberOfNotesWithdraw = numberOfNotesWithdraw;
        this.message = message;
    }

}
