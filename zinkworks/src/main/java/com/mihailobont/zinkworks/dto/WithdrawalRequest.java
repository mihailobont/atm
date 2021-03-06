package com.mihailobont.zinkworks.dto;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class WithdrawalRequest {

    private Long id;
    private Long accountNumber;
    private Long amountToWithdraw;
    private int pin;
}
