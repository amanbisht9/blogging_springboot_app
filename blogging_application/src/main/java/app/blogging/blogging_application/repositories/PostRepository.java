package app.blogging.blogging_application.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.blogging.blogging_application.entity.Category;
import app.blogging.blogging_application.entity.Posts;
import app.blogging.blogging_application.entity.User;

@Repository
public interface PostRepository extends JpaRepository<Posts, Integer>{
    List<Posts> findByPostUser(User user);
    List<Posts> findByPostCategory(Category category);
    List<Posts> findByPostTitleContaining(String postTitle);
}
