package app.blogging.blogging_application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {

    private int id; 
    
    @NotEmpty
    @Size(min = 1, message = "Minimum 1 character for name field")
    private String name;
    @Email(message = "Given email is not valid")
    private String email;
    @NotEmpty
    @Size(min = 4, message = "Minimum 4 character for password field")
    private String password;
    @NotEmpty
    @Size(min = 4, message = "Minimum 4 character for about field")
    private String about;

}
