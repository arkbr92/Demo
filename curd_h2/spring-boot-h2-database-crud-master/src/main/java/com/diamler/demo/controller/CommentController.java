 package com.diamler.demo.controller;

import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.diamler.demo.model.Comment;
import com.diamler.demo.repository.CommentRepository;
import com.diamler.demo.service.CommentService;

/**
 * Adding COMMENT Operation
 * @author arunkbr
 *
 */
@RestController
@RequestMapping("/comment")
public class CommentController {
	static Logger logger = Logger.getLogger(CommentController.class.getName());
    @Autowired
    private CommentService commentService;
/**
 * get top3Comments details by postId
 * @param postId
 * @return ResponseEntity comment details
 */
    @GetMapping("/top3comments/{postId}")
    public ResponseEntity<List<Comment>> getTop3CommentsByPostId(@PathVariable("postId") long postId, @RequestParam(required = false) int topComments) {
    	logger.info("CommentController getTop3CommentsByPostId started:: {} "+postId);
    	List<Comment> top3CommentsByPostId = commentService.getTop3CommentsByPostId(postId, topComments);
    	if (top3CommentsByPostId.isEmpty()) {
    	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    	    }
    	logger.info("CommentController getTop3CommentsByPostId ended:: ");
    	    return new ResponseEntity<>(top3CommentsByPostId, HttpStatus.OK);
    }
    
    /**
     * get all Comments details
     * @param pageable
     * @param assembler
     * @return  all Comment details with Pagination
     */
    
    @GetMapping("/comments")
    public PagedModel<EntityModel<Comment>> getComments(Pageable pageable, PagedResourcesAssembler<Comment> assembler) {
    	logger.info("CommentController getComments started:: ");
    	Page page = commentService.getAllComment(pageable);
    	logger.info("CommentController getComments ended:: ");
        return assembler.toModel(page);
    }
    
    /**
     * save Comment deatils
     * @param comment
     * @return 
     */
    
    @PostMapping("/save")
	public ResponseEntity<Comment> createComment(@RequestBody Comment comment) {
    	logger.info("CommentController createComment started:: ");
		try {
			Comment commentSaved = commentService.saveComment(comment);
			logger.info("CommentController createComment ended:: ");
			return new ResponseEntity<>(commentSaved, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
    
    /**
     * delete comment details
     * @param id
     * @return
     */
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable("id") long id) {
    	logger.info("CommentController deleteComment started:: ");
      try {
        int result = commentService.deleteById(id);
        if (result == 0) {
        	logger.info(" Cannot find Comment");
          return new ResponseEntity<>("Cannot find Comment with id=" + id, HttpStatus.OK);
        }
        logger.info("CommentController deleteComment ended:: ");
        return new ResponseEntity<>("Comment was deleted successfully.", HttpStatus.OK);
      } catch (Exception e) {
        return new ResponseEntity<>("Cannot delete Comment.", HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
}
