
package com.myblog.myblog11.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "Comment")
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String text;

    private String email;

    @ManyToOne
    @JoinColumn(name = "post_id") // Specify nullable based on your requirements
    private Post post;
}
