package com.one.eatmotion.entity;

import com.one.eatmotion.entity.review.TextReview;
import lombok.*;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Proxy(lazy = false)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Setter
@ToString
@Table(name = "Shop")
public class Shop implements Serializable {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private Long id;

  private String name;

  private Double x;

  private Double y;

  private String address;

  private String jachi;

  private String foodClassific;

  private Double grade;



}
