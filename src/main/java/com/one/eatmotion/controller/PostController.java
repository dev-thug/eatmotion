package com.one.eatmotion.controller;

import com.one.eatmotion.dto.PostDTO;
import com.one.eatmotion.entity.Board;
import com.one.eatmotion.entity.Post;
import com.one.eatmotion.entity.User;
import com.one.eatmotion.repository.BoardRepository;
import com.one.eatmotion.repository.PostRepository;
import com.one.eatmotion.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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
    private final UserService userService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "AUTH-TOKEN", value = "허가된 유요한 토큰", required = true, dataType = "String", paramType = "header")
    })
    @Transactional // dirty checking
    @PutMapping("post/{id}")
    public Post update(@RequestBody PostDTO postDTO, @PathVariable Long id) throws Exception {

        User user = userService.getAuthedUser();
        Post post = postRepository.findById(id).orElseThrow();

        if (!post.getUser().getId().equals(user.getId())){
            throw new Exception("사용자가 다름");
        }

        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        return post;

    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "AUTH-TOKEN", value = "허가된 유요한 토큰", required = true, dataType = "String", paramType = "header")
    })
    @PostMapping("post/{id}")
    public Post save(@RequestBody PostDTO post, @PathVariable Long id) {

        User user = userService.getAuthedUser();
        Board board = boardRepository.findById(id).orElseThrow();

        return postRepository.save(Post.builder().title(post.getTitle()).content(post.getContent()).writer(user.getName()).board(board).user(user).build());

    }

    @PostMapping("board")
    public Board save(@RequestParam String name){
        return boardRepository.save(Board.builder().name(name).build());

    }


    @GetMapping("post")
    public List<Post> findAll() {
        return postRepository.findAll();
    }



}
