package com.one.eatmotion.controller;

import com.one.eatmotion.dto.PostDTO;
import com.one.eatmotion.entity.Board;
import com.one.eatmotion.entity.Post;
import com.one.eatmotion.repository.BoardRepository;
import com.one.eatmotion.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.RequestPath;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

  private final PostRepository postRepository;
  private final BoardRepository boardRepository;

  @ResponseBody
  @PostMapping("post/{id}")
  public Post save(@RequestBody PostDTO post, @PathVariable Long id) {
    Board board = boardRepository.findById(id).orElseThrow();

    return postRepository.save(
        Post.builder()
            .title(post.getTitle())
            .content(post.getContent())
            .writer(post.getWriter())
            .board(board)
            .build());
  }

  @PostMapping("board")
  public Board save(@RequestParam String name) {
    return boardRepository.save(Board.builder().name(name).build());
  }

  @GetMapping("post")
  public List<Post> findAll() {
    return postRepository.findAll();
  }
}
