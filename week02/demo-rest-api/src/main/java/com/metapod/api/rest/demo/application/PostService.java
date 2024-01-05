package com.metapod.api.rest.demo.application;

import com.metapod.api.rest.demo.dtos.PostDto;
import com.metapod.api.rest.demo.models.MultilineText;
import com.metapod.api.rest.demo.models.Post;
import com.metapod.api.rest.demo.models.PostId;
import com.metapod.api.rest.demo.repositories.PostRepository;

import java.util.List;

public class PostService {

//    private final PostDAO postDAO;

    private final PostRepository postRepository;

    public PostService() {
//        this.postDAO = new PostDAO();
//        this.postDAO = new PostMapDAO();

        postRepository = new PostRepository();
    }


    public List<PostDto> getPostDtos() {

        List<Post> posts = postRepository.findAll();

        return posts.stream().map(post -> new PostDto(post)).toList();
//        return postDAO.findAll();
    }

    public PostDto getPostDto(String id) {
//        return postDAO.find(id);
        Post post = postRepository.find(PostId.of(id));
        return new PostDto(post);
    }

    public PostDto createPost(PostDto postDto) {

//        PostDto newPostDto = new PostDto(); //원본을 고치지 않기 위해
//
//        String id = UlidCreator.getUlid().toString();
//
//        newPostDto.setId(id);
//        newPostDto.setTitle(postDto.getTitle());
//        newPostDto.setContent(postDto.getContent());
//
//        postDAO.save(postDto);

        Post post = new Post(
                postDto.getTitle(),
                MultilineText.of(postDto.getContent()));

        postRepository.save(post);

        return new PostDto(post);
    }

    public PostDto updatePost(String id, PostDto postDto) {
//        PostDto found = postDAO.find(id);
//        found.setTitle(postDto.getTitle());
//        found.setContent(postDto.getContent());

        Post post = postRepository.find(PostId.of(id));
        post.update(
                postDto.getTitle(),
                MultilineText.of(postDto.getContent())
        );
        return new PostDto(post);
    }

    public PostDto deletePost(String id) {
//        PostDto postDto = postDAO.find(id);
//        postDAO.delete(id);
        Post post = postRepository.find(PostId.of(id));
        postRepository.delete(PostId.of(id));
        return new PostDto(post);
    }
}
