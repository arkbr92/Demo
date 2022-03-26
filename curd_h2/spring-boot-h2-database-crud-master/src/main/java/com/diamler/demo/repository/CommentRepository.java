package com.diamler.demo.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.diamler.demo.model.Comment;
import com.diamler.demo.model.Post;

import java.util.List;

/**
 * Comment repository
 * @author arunkbr
 *
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    <T> List<T> findByPost_Id(Long id, Class<T> type);
	/*
	 * Pageable pageableBottom = new PageRequest(0, 3, Direction.DESC,
	 * "yourFieldToSortBy");
	 * yourRepository.findTopOrderByYourEntity(pageableBottom); the repo:
	 */
    	
    //SELECT r FROM Reviews r WHERE r.book = :book ORDER BY r.stars DESC LIMIT 3;
    
    @Query(value = "SELECT * FROM COMMENT r WHERE r.post_id = :postId ORDER BY r.update DESC LIMIT :topComments", nativeQuery = true)
    List<Comment> getTop3RecordsByPostId(@Param("postId") Long postId, @Param("topComments") int topComments);
    
}
