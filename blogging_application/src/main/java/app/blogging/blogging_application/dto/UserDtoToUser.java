package app.blogging.blogging_application.dto;

import app.blogging.blogging_application.entity.User;
import lombok.Data;

@Data
public class UserDtoToUser {
    
    public User userDtoToUser(UserDto userDto){

        User user = new User();

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        
        return user;
        
    }
    
}