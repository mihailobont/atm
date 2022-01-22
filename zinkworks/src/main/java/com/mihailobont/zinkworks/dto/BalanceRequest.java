package com.mihailobont.zinkworks.dto;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BalanceRequest {

    private Long id;
    private Long accountNumber;
    private int pin;
}
