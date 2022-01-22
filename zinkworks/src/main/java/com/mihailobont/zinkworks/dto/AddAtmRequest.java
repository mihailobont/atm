package com.mihailobont.zinkworks.dto;

import lombok.*;

import javax.persistence.Column;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddAtmRequest {
    private Long fiftyEurosBills;
    private Long twentyEurosBills;
    private Long tenEurosBills;
    private Long fiveEurosBills;
}
