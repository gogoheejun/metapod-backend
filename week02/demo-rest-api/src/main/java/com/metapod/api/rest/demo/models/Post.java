package com.metapod.api.rest.demo.models;

public class Post {

    private PostId id;

    private String title;

    private MultilineText content;

    public Post(PostId id, String title, MultilineText content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public Post(String title, MultilineText content) {
        this.id = PostId.generate();
        this.title = title;
        this.content = content;
    }

    public PostId id() {
        return id;
    }

    public String title() {
        return title;
    }

    public MultilineText content() {
        return content;
    }

    public void update(String title, MultilineText content) {
        //권한검사, 유효성 검사 등등 추가가능..service에서 하는게 아니라 여기로 위임.

        this.title = title;
        this.content = content;
    }
}
