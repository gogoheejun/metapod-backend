package com.metapod.api.rest.demo.controllers;

import com.metapod.api.rest.demo.application.PostService;
import com.metapod.api.rest.demo.dtos.PostDto;
import com.metapod.api.rest.demo.exceptions.PostNotFound;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

//    private final ObjectMapper objectMapper;
//
//    public PostController(ObjectMapper objectMapper) {
//        this.objectMapper = objectMapper;
//    }

    private final PostService postService;

    public PostController() {
        this.postService = new PostService();
    }

    @GetMapping
    public List<PostDto> list(HttpServletResponse response) {
//        response.addHeader("Access-Control-Allow-Origin", "https://seed2whale.github.io");
        return postService.getPostDtos();
    }

    /**
     * Jackson은 spring-boot-starter-web 디펜던시에 이미 포함되어있음.
     * 또한, 아래처럼 String으로 반환할 필요도 없고, objectMapper.으로 json으로 변환해서 String으로 쏴줄 필요가 없음
     * 걍 객체로 보내면 알아서 json 형태 String으로 뽑아줌
     */
//    @GetMapping("/{id}")
//    public String detail(@PathVariable String id) throws JacksonException {
//        PostDto postDto = new PostDto(id, "제목", "테스트입니다.");
//
//        return objectMapper.writeValueAsString(postDto);
//    }
    @GetMapping("/{id}")
    public PostDto detail(@PathVariable String id) {
//        PostDto postDto = new PostDto(id, "제목", "테스트입니다.");
        PostDto postDto = postService.getPostDto(id);
        return postDto;
    }

    @CrossOrigin("https://seed2whale.github.io")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostDto create(@RequestBody(required = false) PostDto postDto) {

//        String id = UlidCreator.getUlid().toString();
//        postDto.setId(id);
//
//        postDtos.add(postDto);

        return postService.createPost(postDto);

//        return "{\"action\": \"게시물 생성\" , \"body\": " + body + "}";
    }

//    @PatchMapping("/{id}")
//    public String update(@PathVariable("id") String id, @RequestBody String body) {
//        return "게시물 수정: " + id + " with " + body;
//    }

    @PatchMapping("/{id}")
    public PostDto update(@PathVariable("id") String id, @RequestBody PostDto postDto) {
        return postService.updatePost(id, postDto);
    }


    @DeleteMapping("/{id}")
    public PostDto delete(@PathVariable("id") String id) {
//        return "게시물 삭제: " + id;
        return postService.deletePost(id);
    }

    @ExceptionHandler(PostNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String postNotFound() {
        return "게시물을 찾을 수 없습니다.\n";
    }
}