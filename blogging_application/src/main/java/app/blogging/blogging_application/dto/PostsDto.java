package app.blogging.blogging_application.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import app.blogging.blogging_application.entity.Category;
import app.blogging.blogging_application.entity.User;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostsDto {


    private int postId;
    
    @NotEmpty
    private String postTitle;
    
    @NotEmpty
    private String postContent;

    private String postImageName;

    private Date postDate;
    
    private CategoryDto postCategory;

    private UserDto postUser;
 

}
