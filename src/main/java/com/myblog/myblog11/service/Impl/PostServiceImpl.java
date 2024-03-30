package com.myblog.myblog11.service.Impl;

import com.myblog.myblog11.entity.Post;
import com.myblog.myblog11.exception.ResourceNotFoundException;
import com.myblog.myblog11.payload.PostDto;
import com.myblog.myblog11.repository.PostRepository;
import com.myblog.myblog11.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    //Reading data from database


    private PostRepository postRepository;

    private ModelMapper modelMapper;


    public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper) {

        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PostDto createPost(PostDto postDto) { // this dto to convert entity simply call the method mapToEntity&alt enter introduce a variable post
//method will call any no of time and code become reusable

        Post post = mapToEntity(postDto);

        Post savedPost = postRepository.save(post);

        PostDto dto = mapToDto(savedPost);// This method is automatically convert this entity to dto object,the method is make the things reusable
        return dto;


    }

    @Override
    public PostDto getPostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Post not found with id: " + id)
        );

        PostDto dto = new PostDto();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setDescription(post.getDescription());
        dto.setContent(post.getContent());
        return dto;

    }

    @Override
    public List<PostDto> getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {//modify that code
        Sort sort = (sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Post> Pagepost = postRepository.findAll(pageable);

        List<Post> posts = Pagepost.getContent();
        List<PostDto> dtos = posts.stream().map(p -> mapToDto(p)).collect(Collectors.toList());
        return dtos;
    }

    PostDto mapToDto(Post post) {
    PostDto dto  = modelMapper.map(post,PostDto.class);
        return dto;

    }

    Post mapToEntity(PostDto postDto) {
      Post post =  modelMapper.map(postDto, Post.class);
        return post;
    }

}
