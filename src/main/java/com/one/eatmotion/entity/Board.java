package com.one.eatmotion.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@Getter
@NoArgsConstructor
@Builder
public class Board {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id; // board_id

  String name;
}
