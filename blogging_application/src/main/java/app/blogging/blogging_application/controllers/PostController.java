package app.blogging.blogging_application.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import app.blogging.blogging_application.dto.PostsDto;
import app.blogging.blogging_application.services.PostService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;




@RestController
@RequestMapping("/api")
public class PostController {
    
    @Autowired
    private PostService postService;

    @PostMapping("/user/{userId}/category/{categoryId}/post")
    public ResponseEntity<PostsDto> postMethodName(@Valid @ModelAttribute PostsDto postsDto, @PathVariable Integer userId, @PathVariable Integer categoryId, @RequestParam("postImage") MultipartFile postImage) throws IOException {
        //Create post api
        PostsDto savedPost = this.postService.createPost(postsDto, userId, categoryId, postImage);
        return new ResponseEntity<PostsDto>(savedPost, HttpStatus.CREATED);
    }

    @DeleteMapping("/remove/post/{id}")
    public void deleteMethodName(@PathVariable("id") Integer id){
        //delete post by id
        this.postService.deletePostById(id);
    }


    @PutMapping("/update/post/{id}")
    public ResponseEntity<PostsDto> putMethodName(@PathVariable Integer id, @Valid @ModelAttribute PostsDto postsDto, @RequestParam("postImage") MultipartFile postImage) throws IOException {
        //TODO: process PUT request
        PostsDto updatedPosts = this.postService.updatePost(postsDto, id, postImage);
        return new ResponseEntity<PostsDto>(updatedPosts, HttpStatus.OK);
        
    }

    @GetMapping("/post")
    public ResponseEntity<List<PostsDto>> getMethodName() {
        return new ResponseEntity<List<PostsDto>>(this.postService.getAllPosts(), HttpStatus.OK);
    }
    
    @GetMapping("/post/{id}")
    public ResponseEntity<PostsDto> getMethodName(@PathVariable Integer id) {
        return new ResponseEntity<PostsDto>(this.postService.getPostsById(id), HttpStatus.OK);
    }


    @GetMapping("/category/{id}/post")
    public ResponseEntity<List<PostsDto>> getCategoryPost(@PathVariable Integer id) {
        return new ResponseEntity<List<PostsDto>>(this.postService.getPostsByCategory(id), HttpStatus.OK);
    }

    @GetMapping("/user/{id}/post")
    public ResponseEntity<List<PostsDto>> getUserPost(@PathVariable Integer id) {
        return new ResponseEntity<List<PostsDto>>(this.postService.getPostByUser(id), HttpStatus.OK);
    }

    @GetMapping("/search/post/{keyword}")
    public ResponseEntity<List<PostsDto>> getMethodName(@PathVariable String keyword) {
        return new ResponseEntity<List<PostsDto>>(this.postService.searchPosts(keyword), HttpStatus.OK);
    }
    
    
    
}
