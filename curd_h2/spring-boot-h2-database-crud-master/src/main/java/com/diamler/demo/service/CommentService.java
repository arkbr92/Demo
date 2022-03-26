package com.diamler.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.diamler.demo.model.Comment;
import com.diamler.demo.repository.CommentRepository;
/**
 * Comment service
 * @author arunkbr
 *
 */
@Service
public class CommentService {
	
	@Autowired
    private CommentRepository commentRepository;

	
	public Page getAllComment(Pageable pageable) {
		Page page = commentRepository.findAll(pageable);
		return page;
	}


	public Comment saveComment(Comment comment) {
		Comment commentSaved = commentRepository.save(comment);
		return commentSaved;
	}


	public List<Comment> getTop3CommentsByPostId(long postId, int topComments) {
		List<Comment> top3RecordsByPostId = commentRepository.getTop3RecordsByPostId(postId, topComments);
		return top3RecordsByPostId;
	}


	public int deleteById(long id) {
		commentRepository.deleteById(id);
		return 1;
	}
	
	

}
