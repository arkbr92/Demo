package com.diamler.demo.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.diamler.demo.controller.PostController;
import com.diamler.demo.model.Account;
import com.diamler.demo.model.Comment;
import com.diamler.demo.model.Post;
import com.diamler.demo.repository.AccountsRepository;
import com.diamler.demo.repository.PostRepository;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {

	@Mock
	private PostRepository postRepository;
	@Mock
	private AccountsRepository accountsRepository;

	@InjectMocks
	private PostService postService;

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
		Account account = new Account();
		account.setId(12l);
		post.setCreateBy(account);
	}

	@Test
	void testSavePost() {
		Account account = new Account();
		account.setId(12l);
		account.setFirstName("FirstName");
		Optional<Account>  accountOptn= Optional.of(account);
		//when(accountsRepository.findById(12l)).thenReturn(accountOptn);
		when(postRepository.save(post)).thenReturn(post);
		Post savePostResult = postService.savePost(post);
		assertNotNull(savePostResult);
		verify(postRepository, times(1)).save(Mockito.any(Post.class));
		//verify(accountsRepository, times(1)).findById(12l);
		assertEquals("PostTitle", savePostResult.getTitle());
		assertEquals(12l, savePostResult.getId());
		assertEquals("Comment1", savePostResult.getComment().get(0).getComment());
		assertEquals(2, savePostResult.getComment().get(0).getDown());
		assertEquals(4, savePostResult.getComment().get(0).getUp());
	}
	
	@Test
	void testDeleteById() {
		//when(postRepository.deleteById(2l)).thenReturn(post);
		int savePostResult = postService.deleteById(2l);
		assertNotNull(savePostResult);
		verify(postRepository, times(1)).deleteById(2l);
		assertEquals(1, savePostResult);
	}
	
	@Test
	void testGetByPostId() {
		Optional<Post> postOp = Optional.of(post);
		when(postRepository.findById(2l)).thenReturn(postOp);
		Post savePostResult = postService.getByPostId(2l);
		assertNotNull(savePostResult);
		//verify(postRepository, times(1)).findById(12l);
		assertEquals("PostTitle", savePostResult.getTitle());
		assertEquals(12l, savePostResult.getId());
		assertEquals("Comment1", savePostResult.getComment().get(0).getComment());
		assertEquals(2, savePostResult.getComment().get(0).getDown());
		assertEquals(4, savePostResult.getComment().get(0).getUp());
	}

}
