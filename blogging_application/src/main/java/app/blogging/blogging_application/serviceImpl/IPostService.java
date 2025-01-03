package app.blogging.blogging_application.serviceImpl;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import app.blogging.blogging_application.config.ModelMapperConfig;
import app.blogging.blogging_application.dto.PostsDto;
import app.blogging.blogging_application.entity.Category;
import app.blogging.blogging_application.entity.Posts;
import app.blogging.blogging_application.entity.User;
import app.blogging.blogging_application.exceptions.CreationException;
import app.blogging.blogging_application.exceptions.FetchException;
import app.blogging.blogging_application.repositories.CategoryRepository;
import app.blogging.blogging_application.repositories.PostRepository;
import app.blogging.blogging_application.repositories.UserRepository;
import app.blogging.blogging_application.services.PostService;

@Service
public class IPostService implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapperConfig modelMapperConfig;


    @Override
    public PostsDto createPost(PostsDto postsDto, Integer userId, Integer categoryId, MultipartFile postImage) throws IOException{
        try {
            Optional<User> user = this.userRepository.findById(userId);

            if(!user.isPresent()){
                throw new FetchException("User id: "+String.valueOf(userId)+" not found", new Throwable().getCause());
            }

            Optional<Category> category = this.categoryRepository.findById(categoryId);
            if(!category.isPresent()){
                throw new FetchException("Category id: "+String.valueOf(categoryId)+" not found", new Throwable().getCause());
            }


            Posts posts = this.modelMapperConfig.modelMapper().map(postsDto, Posts.class);

            if(!postImage.isEmpty()){
                posts.setPostImageName(postImage.getOriginalFilename());
                posts.setPostImageContentType(postImage.getContentType());
                posts.setPostImageData(postImage.getBytes());
            }
            posts.setPostDate(new Date());
            posts.setPostUser(user.get());
            posts.setPostCategory(category.get());

            
            Posts savedPosts = this.postRepository.save(posts);
            return this.modelMapperConfig.modelMapper().map(savedPosts, PostsDto.class);

        } catch (Exception e) {
            throw new CreationException("Cannot create the post.", e);
        }
    }

    @Override
    public void deletePostById(Integer postId) {
        this.postRepository.findById(postId).orElseThrow(()-> new FetchException("Post id: "+String.valueOf(postId)+" not found", new Throwable().getCause()));
        this.postRepository.deleteById(postId);
    }

    @Override
    public PostsDto updatePost(PostsDto postsDto, Integer postId, MultipartFile postImage) throws IOException {
        Posts posts = this.postRepository.findById(postId).orElseThrow(()-> new FetchException("Post id: "+String.valueOf(postId)+" not found", new Throwable().getCause()));
        
        //User id should be same
        // if(posts.getPostUser().equals(postsDto.getPostUser())){
        //     throw new IllegalArgumentException("Post's user cannot be changed", new Throwable().getCause());
        // }

        posts.setPostTitle(postsDto.getPostTitle());
        posts.setPostContent(postsDto.getPostContent());

        if(!postImage.isEmpty()){
            posts.setPostImageName(postImage.getOriginalFilename());
            posts.setPostImageContentType(postImage.getContentType());
            posts.setPostImageData(postImage.getBytes());
        }


        Posts savedPost = this.postRepository.save(posts);

        return this.modelMapperConfig.modelMapper().map(savedPost, PostsDto.class);
    }

    @Override
    public List<PostsDto> getAllPosts() {
        try {
            List<Posts> posts = this.postRepository.findAll();
            return posts.stream()
                       .map(u -> this.modelMapperConfig.modelMapper().map(u, PostsDto.class))
                       .collect(Collectors.toList());
        } catch (Exception e) {
            throw new FetchException("Posts not found", e);
        }
    }

    @Override
    public PostsDto getPostsById(Integer id) {
        try {
            Optional<Posts> posts = this.postRepository.findById(id);
            if(posts.isPresent() == false){
                throw new FetchException("Post id: "+String.valueOf(id)+" not found", new Throwable().getCause());
            }

            return this.modelMapperConfig.modelMapper().map(posts, PostsDto.class);

        } catch (Exception e) {
            throw new FetchException("Post id: "+String.valueOf(id)+" not found", e);
        }
    }

    @Override
    public List<PostsDto> getPostsByCategory(Integer categoryId) {
        try {
            Category category = this.categoryRepository.findById(categoryId).orElseThrow(()-> new FetchException("Category id: "+String.valueOf(categoryId)+" not found", new Throwable().getCause()));
            List<Posts> posts = this.postRepository.findByPostCategory(category);
            return posts.stream()
                .map(u -> this.modelMapperConfig.modelMapper().map(u, PostsDto.class))
                .collect(Collectors.toList());
        } catch (Exception e) {
            throw new FetchException("Posts cannot be fetched for category", new Throwable().getCause());
        }
    }

    @Override
    public List<PostsDto> getPostByUser(Integer userId) {
        try {
            User user = this.userRepository.findById(userId).orElseThrow(()-> new FetchException("User id: "+String.valueOf(userId)+" not found", new Throwable().getCause()));
            List<Posts> posts = this.postRepository.findByPostUser(user);
            return posts.stream()
                .map(u -> this.modelMapperConfig.modelMapper().map(u, PostsDto.class))
                .collect(Collectors.toList());
        } catch (Exception e) {
            throw new FetchException("Posts cannot be found for user", new Throwable().getCause());
        }
    }

    @Override
    public List<PostsDto> searchPosts(String keyword) {
        List<Posts> posts = this.postRepository.findByPostTitleContaining(keyword);
        List<PostsDto> postsDtos = posts.stream()
                                    .map(u -> this.modelMapperConfig.modelMapper().map(u, PostsDto.class))
                                    .collect(Collectors.toList());

        return postsDtos;
    }
    
}
