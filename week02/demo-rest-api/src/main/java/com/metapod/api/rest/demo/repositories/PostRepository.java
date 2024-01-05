package com.metapod.api.rest.demo.repositories;

import com.metapod.api.rest.demo.exceptions.PostNotFound;
import com.metapod.api.rest.demo.models.MultilineText;
import com.metapod.api.rest.demo.models.Post;
import com.metapod.api.rest.demo.models.PostId;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostRepository {
    private final Map<PostId, Post> posts;

    public PostRepository() {
        this.posts = new HashMap<PostId, Post>();

        this.posts.put(PostId.of("1"), new Post(PostId.of("1"), "제목", MultilineText.of("테스트입니다.")));
        this.posts.put(PostId.of("2"), new Post(PostId.of("2"), "제목22", MultilineText.of("테스트입니다.")));
    }

    public List<Post> findAll() {
        return new ArrayList<>(posts.values());
    }

    public Post find(PostId id) {
        Post post = posts.get(id);

        if (post == null) {
            throw new PostNotFound();
        }
        return post;
    }

    public void save(Post post) {
        posts.put(post.id(), post);
    }

    public void delete(PostId id) {
        posts.remove(id);
    }
}
