package com.myblog.myblog11.service;

import com.myblog.myblog11.payload.PostDto;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postdto);



    PostDto getPostById(long id);

    List<PostDto> getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);
}
