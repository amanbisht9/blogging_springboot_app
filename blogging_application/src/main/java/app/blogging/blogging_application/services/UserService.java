package app.blogging.blogging_application.services;

import java.util.List;


import app.blogging.blogging_application.dto.UserDto;

public interface UserService{

    UserDto createUser(UserDto userDto);
    UserDto updateUser(UserDto userDto, Integer userId);
    UserDto getUserById(Integer userId);
    List<UserDto> getAllUser()  throws Exception;
    void deleteUserById(Integer userId);
}