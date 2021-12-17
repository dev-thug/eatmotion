package com.one.eatmotion.controller;

import com.one.eatmotion.dto.PostDTO;
import com.one.eatmotion.entity.Board;
import com.one.eatmotion.entity.Post;
import com.one.eatmotion.repository.BoardRepository;
import com.one.eatmotion.repository.PostRepository;
import com.one.eatmotion.service.PostService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
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

    private final BoardRepository boardRepository;
    private final PostService postService;

    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "authToken",
                    value = "허가된 유요한 토큰",
                    required = true,
                    dataType = "String",
                    paramType = "header")
    })
    @ApiOperation(value = "게시글 작성", notes = "회원이 게시판에 게시글을 작성한다")
    @PostMapping("post/{id}")
    public Post save(@RequestBody PostDTO post, @PathVariable Long id) {
        return postService.writePost(post, id);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "authToken",
                    value = "허가된 유요한 토큰",
                    required = true,
                    dataType = "String",
                    paramType = "header")
    })
    @ApiOperation(value = "게시글 수정", notes = "회원이 게시판에 게시글을 수정한다")
    @PutMapping("post/{id}")
    public Post update(@RequestBody PostDTO post, @PathVariable Long id) throws Exception {
        return postService.updatePost(post, id);
    }

    @PostMapping("board")
    public Board save(@RequestParam String name) {
        return boardRepository.save(Board.builder().name(name).build());
    }

    @GetMapping("posts/{boardId}")
    public Page<Post> findAll(
            @PathVariable Long boardId,
            @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {

        return postService.findAllByBoard(boardId, pageable);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "authToken",
                    value = "허가된 유요한 토큰",
                    required = true,
                    dataType = "String",
                    paramType = "header")
    })
    @ApiOperation(value = "게시글 삭제", notes = "회원이 게시판에 게시글을 삭제한다")
    @DeleteMapping("post/{id}")
    public ResponseEntity delete(@PathVariable Long id) throws Exception {
        postService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
