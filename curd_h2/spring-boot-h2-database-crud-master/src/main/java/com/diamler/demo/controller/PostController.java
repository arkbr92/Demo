package com.diamler.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diamler.demo.exception.PostNotFoundException;
import com.diamler.demo.model.Post;
import com.diamler.demo.repository.PostRepository;
import com.diamler.demo.service.PostService;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/posts/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable("id") long id) {
    	Post post = postService.getByPostId(id);
    	if(post == null)
            throw new PostNotFoundException();
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }

    @GetMapping("/posts")
    public PagedModel<EntityModel<Post>> getPosts(Pageable pageable, PagedResourcesAssembler<Post> assembler) {
        Page page = postService.getAllPost(pageable);
        return assembler.toModel(page);
    }
    
    @PostMapping("/save")
	public ResponseEntity<Post> createPost(@RequestBody Post post) {
		try {
			Post postSaved = postService.savePost(post);
			return new ResponseEntity<>(postSaved, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePost(@PathVariable("id") long id) {
      try {
        int result = postService.deleteById(id);
        if (result == 0) {
          return new ResponseEntity<>("Cannot find Post with id=" + id, HttpStatus.OK);
        }
        return new ResponseEntity<>("Post was deleted successfully.", HttpStatus.OK);
      } catch (Exception e) {
        return new ResponseEntity<>("Cannot delete Post.", HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
}
