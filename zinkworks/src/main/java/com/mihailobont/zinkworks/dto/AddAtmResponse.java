package com.mihailobont.zinkworks.dto;

import com.mihailobont.zinkworks.modal.Atm;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddAtmResponse {

    private Atm atm;
    private String message;
}
