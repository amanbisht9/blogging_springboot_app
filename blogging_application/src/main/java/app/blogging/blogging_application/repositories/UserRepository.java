package app.blogging.blogging_application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.blogging.blogging_application.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
    
}
