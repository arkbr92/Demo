package com.diamler.demo.model;

import javax.persistence.*;

import org.springframework.data.annotation.CreatedBy;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

/**
 * Post entity
 * @author arunkbr
 *
 */

@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    @OneToMany(mappedBy = "post") //, cascade = CascadeType.ALL, cascade = CascadeType.PERSIST
    private List<Comment> comment;
    
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date created;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "Id")
    private Account createBy;
    
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

	public Account getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Account createBy) {
		this.createBy = createBy;
	}
    
}
