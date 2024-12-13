package app.blogging.blogging_application.dto;

import app.blogging.blogging_application.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {

    private String name;
    private String email;
    private String password;
    private String about;

}