package app.blogging.blogging_application.serviceImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.blogging.blogging_application.dto.UserDto;
import app.blogging.blogging_application.dto.UserDtoToUser;
import app.blogging.blogging_application.entity.User;
import app.blogging.blogging_application.exceptions.FetchException;
import app.blogging.blogging_application.repositories.UserRepository;
import app.blogging.blogging_application.services.UserService;

@Service
public class IUserService implements UserService{

    @Autowired
    private UserRepository userRepository;


    @Override
    public User createUser(UserDto userDto) throws Exception{
        // Add or create user in database
        try {
            UserDtoToUser userDtoToUser = new UserDtoToUser();
            User user = userDtoToUser.userDtoToUser(userDto);
            User savedUser = this.userRepository.save(user);
            return savedUser;

        } catch (Exception e) {
            // Throw exception
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public User updateUser(UserDto userDto, Integer userId) {
        // Update the user
        
        User user = this.userRepository.findById(userId).orElse(null);

        if(user == null){
            throw new FetchException("User id: "+String.valueOf(userId)+" not found");
        }

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());

        User savedUser = this.userRepository.save(user);

        return savedUser;

    }
    

    @Override
    public User getUserById(Integer userId){
        // Get the user by id
     
        User user = this.userRepository.findById(userId).orElse(null);

        if(user == null){
            throw new FetchException("User id: "+String.valueOf(userId)+" not found");
        }

        return user;

    }

    @Override
    public List<User> getAllUser()  throws Exception{
        // Get the user
        try {
            List<User> user = this.userRepository.findAll();
            return user;

        } catch (Exception e) {
            // Throw exception
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void deleteUserById(Integer userId){
        // Delete user by id
       
        User user = this.userRepository.findById(userId).orElse(null);
        if(user == null){
            throw new FetchException("User id: "+String.valueOf(userId)+" not found");
        }
        this.userRepository.deleteById(userId);

    }
    
}
