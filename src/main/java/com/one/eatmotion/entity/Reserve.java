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
    Long id; //ReserveId

    Long userId;

    Long storeId;

    String reserveDate;

    Integer reserveNumberOfPeople;


//    @ManyToOne
//    User user;
}
