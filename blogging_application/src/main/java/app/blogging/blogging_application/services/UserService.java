package app.blogging.blogging_application.services;

import java.util.List;


import app.blogging.blogging_application.dto.UserDto;
import app.blogging.blogging_application.entity.User;

public interface UserService{

    User createUser(UserDto userDto) throws Exception;
    User updateUser(UserDto userDto, Integer userId) throws Exception;
    User getUserById(Integer userId)  throws Exception;
    List<User> getAllUser()  throws Exception;
    void deleteUserById(Integer userId) throws Exception;
}