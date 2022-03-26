package com.diamler.demo.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.diamler.demo.model.Post;

import java.util.List;

/**
 * Post repository
 * @author arunkbr
 *
 */
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT p FROM Post AS p WHERE p.title = ?1")
    List<Post> findByTitle(String title, Sort sort);
    
}
