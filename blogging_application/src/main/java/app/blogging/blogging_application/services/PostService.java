package app.blogging.blogging_application.services;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import app.blogging.blogging_application.dto.PostsDto;
import app.blogging.blogging_application.entity.Category;
import app.blogging.blogging_application.entity.Posts;
import app.blogging.blogging_application.entity.User;

public interface PostService {
    PostsDto createPost(PostsDto postsDto, Integer userId, Integer categoryId, MultipartFile postImage) throws IOException;
    void deletePostById(Integer postId);
    PostsDto updatePost(PostsDto postsDto, Integer postId, MultipartFile postImage) throws IOException;
    List<PostsDto> getAllPosts();
    PostsDto getPostsById(Integer postId);
    List<PostsDto> getPostsByCategory(Integer categoryId);
    List<PostsDto> getPostByUser(Integer userId);
    List<PostsDto> searchPosts(String keyword);
}
