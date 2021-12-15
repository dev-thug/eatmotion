package com.one.eatmotion.service;

import com.one.eatmotion.advice.exception.NotFoundResourceException;
import com.one.eatmotion.dto.PostDTO;
import com.one.eatmotion.entity.Board;
import com.one.eatmotion.entity.Post;
import com.one.eatmotion.entity.User;
import com.one.eatmotion.repository.BoardRepository;
import com.one.eatmotion.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PostService {
  private final PostRepository postRepository;

  private final UserService userService;

  private final BoardRepository boardRepository;

  @Transactional
  public Post updatePost(PostDTO postDTO, Long postId) throws Exception {
    User user = userService.getAuthedUser();
    Post post = postRepository.findById(postId).orElseThrow(NotFoundResourceException::new);

    if (!Objects.equals(post.getUser().getId(), user.getId())) {
      throw new Exception();
    }

    // dirty checking JPA가 객체의 변화를 감지를 하고, DB 를 업데이트 한다
    post.updatePost(postDTO.getTitle(), postDTO.getContent());

    return post;
  }

  public Post writePost(PostDTO postDTO, Long boardId) {
    User user = userService.getAuthedUser(); // 현재 로그인이된 유저의 정보를 가져오는 부분
    Board board = boardRepository.findById(boardId).orElseThrow();
    Post post =
        Post.builder()
            .title(postDTO.getTitle())
            .content(postDTO.getContent())
            .writer(user.getName())
            .user(user)
            .board(board)
            .build();

    return postRepository.save(post);
  }

  public Page<Post> findAllByBoard(Long boardId, Pageable pageable) {
    Board board = boardRepository.findById(boardId).orElseThrow();

    //        Page<Post> pagedPosts = postRepository.findAllByBoard(board, pageable);

    return postRepository.findAllByBoard(board, pageable);
  }

  public void delete(Long postId) throws Exception {
    User user = userService.getAuthedUser();
    Post post = postRepository.findById(postId).orElseThrow(NotFoundResourceException::new);

    if (!Objects.equals(post.getUser().getId(), user.getId())) {
      throw new Exception();
    }

    postRepository.delete(post);
  }
}
