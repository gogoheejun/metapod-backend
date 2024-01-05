package com.metapod.api.rest.demo.daos;

import com.metapod.api.rest.demo.dtos.PostDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostMapDAO implements PostDAO {
    private Map<String, PostDto> postDtos;

    public PostMapDAO() {
        this.postDtos = new HashMap<>();
        this.postDtos.put("1", new PostDto("1", "제목", "테스트입니다"));
        this.postDtos.put("2", new PostDto("2", "제목22", "테스트입니다22"));
    }

    @Override
    public List<PostDto> findAll() {
        return new ArrayList<>(postDtos.values());
    }

    @Override
    public PostDto find(String id) {
        return postDtos.get(id);
    }

    @Override
    public void save(PostDto postDto) {
        postDtos.put(postDto.getId(), postDto);
    }

    @Override
    public void delete(String id) {
        postDtos.remove(id);
    }
}
