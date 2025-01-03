package app.blogging.blogging_application.entity;


import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="posts")
@NoArgsConstructor
@Getter
@Setter
public class Posts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postId;
    
    @Size(min=1, max=80)
    private String postTitle;
    
    @Column(length = 10000)
    private String postContent;

 
    private String postImageName = "default.png";

    private String postImageContentType;
    
    @Lob
    private byte[] postImageData;


    private Date postDate;
    
    @ManyToOne
    private Category postCategory;

    @ManyToOne
    private User postUser;

}
