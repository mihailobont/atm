package com.mihailobont.zinkworks.dto;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddAccountRequest {

    private Long accountNumber;
    private int pin;
    private Long openingBalance;
    private Long overdraft;

}
