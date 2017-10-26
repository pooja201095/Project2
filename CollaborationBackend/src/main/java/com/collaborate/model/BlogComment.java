package com.collaborate.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class BlogComment {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int blogCommentId;
	
	private String commentTxt;
	
	@ManyToOne
	@JoinColumn(name="userid")
	private Users commentedBy;
	
	private Date commentedOn;
	
	@ManyToOne
	private Blog blog;

	public int getBlogCommentId() {
		return blogCommentId;
	}

	public void setBlogCommentId(int blogCommentId) {
		this.blogCommentId = blogCommentId;
	}

	public String getCommentTxt() {
		return commentTxt;
	}

	public void setCommentTxt(String commentTxt) {
		this.commentTxt = commentTxt;
	}

	public Users getCommentedBy() {
		return commentedBy;
	}

	public void setCommentedBy(Users commentedBy) {
		this.commentedBy = commentedBy;
	}

	public Date getCommentedOn() {
		return commentedOn;
	}

	public void setCommentedOn(Date commentedOn) {
		this.commentedOn = commentedOn;
	}

	public Blog getBlog() {
		return blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}
	
	
	

}
