package com.mihailobont.zinkworks.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BalanceRequest {

    private Long id;
    private Long accountNumber;
    private int pin;
}
