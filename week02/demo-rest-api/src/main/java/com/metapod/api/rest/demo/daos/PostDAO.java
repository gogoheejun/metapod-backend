package com.metapod.api.rest.demo.daos;

import com.metapod.api.rest.demo.dtos.PostDto;

import java.util.List;

public interface PostDAO {

    public List<PostDto> findAll();

    public PostDto find(String id);

    public void save(PostDto postDto);

    public void delete(String id);
}
