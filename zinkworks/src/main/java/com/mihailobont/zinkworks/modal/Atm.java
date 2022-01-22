package com.mihailobont.zinkworks.modal;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "atms")
@Getter
@Setter
@AllArgsConstructor
@ToString
public class Atm {

    /* Number of notes at initialization */
    @Transient
    private final Long NUMBER_OF_FIFTY_BILLS = 10L;
    @Transient
    private final Long NUMBER_OF_TWENTY_BILLS = 30L;
    @Transient
    private final Long NUMBER_OF_TEN_BILLS = 30L;
    @Transient
    private final Long NUMBER_OF_FIVE_BILLS = 20L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "fifty")
    private Long fiftyEurosBills;

    @Column(name = "twenty")
    private Long twentyEurosBills;

    @Column(name = "ten")
    private Long tenEurosBills;

    @Column(name = "five")
    private Long fiveEurosBills;

    public Atm() {
        this.fiftyEurosBills = NUMBER_OF_FIFTY_BILLS;
        this.twentyEurosBills = NUMBER_OF_TWENTY_BILLS;
        this.tenEurosBills = NUMBER_OF_TEN_BILLS;
        this.fiveEurosBills = NUMBER_OF_FIVE_BILLS;
    }

    public Atm(Long fiftyEurosBills, Long twentyEurosBills, Long tenEurosBills, Long fiveEurosBills) {
        this.fiftyEurosBills = fiftyEurosBills;
        this.twentyEurosBills = twentyEurosBills;
        this.tenEurosBills = tenEurosBills;
        this.fiveEurosBills = fiveEurosBills;
    }


}
