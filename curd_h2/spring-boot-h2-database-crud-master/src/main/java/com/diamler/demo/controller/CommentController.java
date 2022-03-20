package com.diamler.demo.controller;

import java.util.List;

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

import com.diamler.demo.model.Comment;
import com.diamler.demo.repository.CommentRepository;
import com.diamler.demo.service.CommentService;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/top3comments/{postId}")
    public ResponseEntity<List<Comment>> getTop3CommentsByPostId(@PathVariable("postId") long postId) {
    	List<Comment> top3CommentsByPostId = commentService.getTop3CommentsByPostId(postId);
    	if (top3CommentsByPostId.isEmpty()) {
    	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    	    }

    	    return new ResponseEntity<>(top3CommentsByPostId, HttpStatus.OK);
    }
    
    
    @GetMapping("/comments")
    public PagedModel<EntityModel<Comment>> getComments(Pageable pageable, PagedResourcesAssembler<Comment> assembler) {
        Page page = commentService.getAllComment(pageable);
        return assembler.toModel(page);
    }
    
    @PostMapping("/save")
	public ResponseEntity<Comment> createComment(@RequestBody Comment comment) {
		try {
			Comment commentSaved = commentService.saveComment(comment);
			return new ResponseEntity<>(commentSaved, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable("id") long id) {
      try {
        int result = commentService.deleteById(id);
        if (result == 0) {
          return new ResponseEntity<>("Cannot find Comment with id=" + id, HttpStatus.OK);
        }
        return new ResponseEntity<>("Comment was deleted successfully.", HttpStatus.OK);
      } catch (Exception e) {
        return new ResponseEntity<>("Cannot delete Comment.", HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
}
