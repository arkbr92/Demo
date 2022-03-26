package com.diamler.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.diamler.demo.model.Account;
import com.diamler.demo.model.Post;
import com.diamler.demo.repository.AccountsRepository;
import com.diamler.demo.repository.PostRepository;

/**
 * POSTService Operation
 * @author arunkbr
 *
 */

@Service
public class PostService {
	
	@Autowired
    private PostRepository postRepository;
	@Autowired
	private AccountsRepository accountsRepository;
	
	public Page getAllPost(Pageable pageable) {
		Page page = postRepository.findAll(pageable);
		return page;
	}

	public Post savePost(Post post) {
		
		Optional<Account> createBy = accountsRepository.findByFirstName(post.getCreateBy().getFirstName());
		Account account = createBy.isPresent() ? createBy.get() : null;
		post.setCreateBy(account);
		Post postSaved = postRepository.save(post);
		return postSaved;
	}


	public int deleteById(long id) {
		postRepository.deleteById(id);
		return 1;
	}


	public Post getByPostId(long id) {
		Optional<Post> post = postRepository.findById(id);
		if(!post.isPresent())
			return null;
		return post.get();
	}
}
