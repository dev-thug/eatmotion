package com.one.eatmotion.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Setter
public class Post {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  String title;

  String content;

  String writer;

  @ManyToOne Board board;

  @ManyToOne User user;

  public void updatePost(String title, String content) {
    this.title = title;
    this.content = content;
  }
}
