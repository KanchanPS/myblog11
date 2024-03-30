package com.myblog.myblog11.payload;

import lombok.Data;

@Data
public class CommentDto {
    private Long id;  // Use Long instead of long
    private String text;
    private String email;


}

