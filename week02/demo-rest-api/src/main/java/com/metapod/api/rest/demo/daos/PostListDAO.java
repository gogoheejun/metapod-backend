package com.metapod.api.rest.demo.daos;

import com.metapod.api.rest.demo.dtos.PostDto;
import com.metapod.api.rest.demo.exceptions.PostNotFound;

import java.util.ArrayList;
import java.util.List;

public class PostListDAO implements PostDAO {

    private final List<PostDto> postDtos;// 컬렉션을 바깥으로 바로 주는건 좋지 않으므로 final

    public PostListDAO() {
        this.postDtos = new ArrayList<>(List.of(
                new PostDto("1", "제목", "테스트입니다"),
                new PostDto("2", "2등", "222")
        ));

    }

    @Override
    public List<PostDto> findAll() {
        return new ArrayList<>(postDtos);//컬렉션 바로 주면 캡슐화가 깨지므로 새로 리스트 생성해서 반환
    }

    @Override
    public PostDto find(String id) {
        return postDtos.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new PostNotFound());
    }

    @Override
    public void save(PostDto postDto) {
        postDtos.add(postDto);
    }

    @Override
    public void delete(String id) {
    }
}
