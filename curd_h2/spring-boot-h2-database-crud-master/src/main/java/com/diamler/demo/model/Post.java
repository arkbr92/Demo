package com.diamler.demo.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

@Entity
@NamedQuery(name = "Post.findByTitle", query = "SELECT p FROM Post AS p WHERE p.title = ?1")
public class Post {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    @OneToMany(mappedBy = "post") //, cascade = CascadeType.ALL
    private List<Comment> comment;
    
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date created;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

	public List<Comment> getComment() {
		return comment;
	}

	public void setComment(List<Comment> comment) {
		this.comment = comment;
	}
    
    
}
