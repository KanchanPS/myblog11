package com.myblog.myblog11.service.Impl;

import com.myblog.myblog11.entity.Comment;
import com.myblog.myblog11.entity.Post;
import com.myblog.myblog11.exception.ResourceNotFoundException;
import com.myblog.myblog11.payload.CommentDto;
import com.myblog.myblog11.repository.CommentRepository;
import com.myblog.myblog11.repository.PostRepository;
import com.myblog.myblog11.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Override
    public void deleteComment(long id) {


        commentRepository.deleteById(id);


    }

    @Override
    public CommentDto updateComment(long id, CommentDto commentDto, long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("post  Not Found For Id:" + id)

        );
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("comment Not Found For Id:" + id)
        );
        Comment c = mapToEntity(commentDto);
        c.setId(comment.getId());
        c.setPost(post);
        Comment savedComment = commentRepository.save(c);

        return  mapToDto(savedComment);

    }

CommentDto mapToDto(Comment comment){
    CommentDto dtoss = modelMapper.map(comment, CommentDto.class);
    return dtoss;
}
    Comment mapToEntity(CommentDto commentDto) {
        Comment dtos = modelMapper.map(commentDto, Comment.class);
        return dtos;
    }

    private PostRepository postRepository;

    private CommentRepository commentRepository;

    private ModelMapper modelMapper;


 @Autowired
    public CommentServiceImpl(PostRepository postRepository, CommentRepository commentRepository , ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public CommentDto createComment(CommentDto commentDto, long PostId) {


        Post post = postRepository.findById(PostId).orElseThrow(
                () -> new ResourceNotFoundException("post Not found with id:" + PostId)
        );
        Comment comment = new Comment();
        comment.setEmail(commentDto.getEmail());
        comment.setText(commentDto.getText());
        comment.setPost(post);

        Comment SavedComment = commentRepository.save(comment);

        CommentDto dto = new CommentDto();
        dto.setId(SavedComment.getId());
        dto.setText(SavedComment.getText());
        dto.setEmail(SavedComment.getEmail());
        return dto;
    }
}


















