package com.one.eatmotion.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Setter
public class Reserve {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id; // ReserveId

    private Long storeId;

    private String reserveDate;

    private Integer reserveNumberOfPeople;

    @ManyToOne
    private User user;

    @ManyToOne
    private Shop shop;
}
