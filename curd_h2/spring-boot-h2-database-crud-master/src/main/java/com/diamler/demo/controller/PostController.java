package com.diamler.demo.controller;

import java.util.logging.Logger;

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

/**
 * Adding POST Operation
 * @author arunkbr
 *
 */
@RestController
@RequestMapping("/post")
public class PostController {
	static Logger logger = Logger.getLogger(CommentController.class.getName());
    @Autowired
    private PostService postService;

    /**
     * get Post details by Id
     * @param post id
     * @return ResponseEntity Post details
     */
    @GetMapping("/posts/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable("id") long id) {
    	logger.info("PostController getPostById started:: ");

    	Post post = postService.getByPostId(id);
    	if(post == null)
            throw new PostNotFoundException("Post is not found from Database");
    	
    	logger.info("PostController getPostById ended:: ");
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }

    /**
     * get all Post details
     * @param pageable
     * @param assembler
     * @return all Post details with Pagination
     */
    @GetMapping("/posts")
    public PagedModel<EntityModel<Post>> getPosts(Pageable pageable, PagedResourcesAssembler<Post> assembler) {
        Page page = postService.getAllPost(pageable);
        return assembler.toModel(page);
    }
    
    /**
     * save post details
     * @param post
     * @return
     */
    @PostMapping("/save")
	public ResponseEntity<Post> createPost(@RequestBody Post post) {
		try {
			Post postSaved = postService.savePost(post);
			return new ResponseEntity<>(postSaved, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
    
    /**
     * delete post details
     * @param id
     * @return
     */
    
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
