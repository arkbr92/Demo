package com.diamler.demo.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.diamler.demo.model.Comment;
import com.diamler.demo.model.Post;
import com.diamler.demo.service.PostService;

/**
 * Post controller Test Operation
 * @author arunkbr
 *
 */

@ExtendWith(MockitoExtension.class)
class PostControllerTest {

	@Mock
	private PostService postService;

	@InjectMocks
	private PostController postController;

    private Post post;
	@BeforeEach
	public void setUp() {
		post = new Post();
		post.setTitle("PostTitle");
		List<Comment> comments = new ArrayList<Comment>();
		Comment comment = new Comment();
		comment.setBest(true);
		comment.setComment("Comment1");
		comment.setDown(2);
		comment.setUp(4);
		comment.setId(4l);
		comments.add(comment);
		post.setComment(comments);
		post.setCreated(new Date());
		post.setId(12l);
	}
/**
 * Save post testcase
 */
	@Test 
	void testSavePost() {
		when(postService.savePost(post)).thenReturn(post);
		ResponseEntity<Post> savePostResult = postController.createPost(post);
		assertNotNull(savePostResult);
		verify(postService, times(1)).savePost(Mockito.any(Post.class));
		assertEquals(HttpStatus.CREATED, savePostResult.getStatusCode());
		assertEquals("PostTitle", savePostResult.getBody().getTitle());
		assertEquals(12l, savePostResult.getBody().getId());
		assertEquals("Comment1", savePostResult.getBody().getComment().get(0).getComment());
		assertEquals(2, savePostResult.getBody().getComment().get(0).getDown());
		assertEquals(4, savePostResult.getBody().getComment().get(0).getUp());
	}
	
	/**
	 * Exception while save post
	 */

	@Test
	void exceptionWhileSavePost() {
		when(postService.savePost(post)).thenThrow(RuntimeException.class);
		ResponseEntity<Post> savePostResult = postController.createPost(post);
		assertNotNull(savePostResult);
		verify(postService, times(1)).savePost(Mockito.any(Post.class));
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, savePostResult.getStatusCode());
	}
	/**
	 * Test get post byId
	 */
	@Test
	void testGetPostById() {
		when(postService.getByPostId(12l)).thenReturn(post);
		ResponseEntity<Post> savePostResult = postController.getPostById(12l);
		assertNotNull(savePostResult);
		verify(postService, times(1)).getByPostId(Mockito.anyLong());
		assertEquals(HttpStatus.CREATED, savePostResult.getStatusCode());
		assertEquals("PostTitle", savePostResult.getBody().getTitle());
		assertEquals(12l, savePostResult.getBody().getId());
		assertEquals("Comment1", savePostResult.getBody().getComment().get(0).getComment());
		assertEquals(2, savePostResult.getBody().getComment().get(0).getDown());
		assertEquals(4, savePostResult.getBody().getComment().get(0).getUp());
	}

	
	//@Test
	//@Ignore
	//(expected = IllegalArgumentException.class)
	//@ExpectsException(type = IllegalArgumentException.class, message = "negatives not allowed: [-1]")
	void exceptionWhileGetPostById() throws Exception {
		when(postService.getByPostId(12l)).thenReturn(null);
		ResponseEntity<Post> savePostResult = postController.getPostById(12l);
		assertNotNull(savePostResult);
		verify(postService, times(1)).getByPostId(Mockito.anyLong());
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, savePostResult.getStatusCode());
	}
	
}
