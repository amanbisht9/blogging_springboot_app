package app.blogging.blogging_application.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.blogging.blogging_application.config.ModelMapperConfig;
import app.blogging.blogging_application.dto.UserDto;
import app.blogging.blogging_application.entity.User;
import app.blogging.blogging_application.exceptions.CreationException;
import app.blogging.blogging_application.exceptions.FetchException;
import app.blogging.blogging_application.repositories.UserRepository;
import app.blogging.blogging_application.services.UserService;

@Service
public class IUserService implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapperConfig modelMapperConfig;


    @Override
    public UserDto createUser(UserDto userDto){
        // Add or create user in database
        try {

            User user = this.modelMapperConfig.modelMapper().map(userDto, User.class);
            User savedUser = this.userRepository.save(user);
            return this.modelMapperConfig.modelMapper().map(savedUser, UserDto.class);

        } catch (Exception e) {
            // Throw exception
            throw new CreationException("Error while creating user", e);
        }
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        // Update the user
        
        User user = this.userRepository.findById(userId).orElseThrow(() -> new FetchException("User id: "+String.valueOf(userId)+" not found", new Throwable()));

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());

        User savedUser = this.userRepository.save(user);

        return this.modelMapperConfig.modelMapper().map(savedUser, UserDto.class);

    }
    

    @Override
    public UserDto getUserById(Integer userId){
        // Get the user by id
     
        User user = this.userRepository.findById(userId).orElseThrow(() -> new FetchException("User id: "+String.valueOf(userId)+" not found", new Throwable().getCause()));
        // if(user == null){
        //     throw new FetchException("User id: "+String.valueOf(userId)+" not found");
        // }

        return this.modelMapperConfig.modelMapper().map(user, UserDto.class);

    }

    @Override
    public List<UserDto> getAllUser()  throws Exception{
        // Get the user
        try {
            List<User> user = this.userRepository.findAll();
            return user.stream()
                       .map(u -> this.modelMapperConfig.modelMapper().map(u, UserDto.class))
                       .collect(Collectors.toList());

        } catch (Exception e) {
            // Throw exception
            throw new FetchException("Cannot get all users.", e);
        }
    }

    @Override
    public void deleteUserById(Integer userId){
        // Delete user by id
        this.userRepository.findById(userId).orElseThrow(() -> new FetchException("User id: "+String.valueOf(userId)+" not found", new Throwable().getCause()));
        this.userRepository.deleteById(userId);

    }
    
}
