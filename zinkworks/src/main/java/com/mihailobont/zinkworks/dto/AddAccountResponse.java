package com.mihailobont.zinkworks.dto;

import com.mihailobont.zinkworks.modal.Account;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddAccountResponse {

    private Account account;
    private String message;

}
