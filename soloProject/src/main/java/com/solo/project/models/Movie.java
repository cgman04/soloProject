package com.solo.project.models;


import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="movies")
public class Movie {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(updatable=false)
    @DateTimeFormat(pattern = "MM/dd/yyyy HH:mm:ss")
	private Date createdAt;
    @DateTimeFormat(pattern = "MM/dd/yyyy HH:mm:ss")
	private Date updatedAt;
    @PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}
	
	@NotEmpty(message = "Movie Title is required!")
	@Size(min = 3, max = 30, message = "Movie Title must be between 3 and 30 characters.")
	private String movieTitle;
	
	@NotEmpty(message = "Review Title is required!")
	@Size(min = 3, max = 30, message = "Review Title must be between 3 and 30 characters.")
	private String reviewTitle;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")/*MUST be year-month-day*/
    @NotNull(message="Due Date is required!")
    private Date date;  
	
	@NotEmpty(message = "Review is required!")
	@Size(min = 10, max = 500, message = "Review must be between 10 and 500 characters.")
	private String review;
	
	
	private Integer rating;
	

	
	// Table relationships
		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name="lead_id")
		private User lead;
		
		@ManyToMany(fetch = FetchType.LAZY)
		@JoinTable(
				name="likes",
				joinColumns = @JoinColumn(name="movie_id"),
				inverseJoinColumns = @JoinColumn(name="user_id")
				)
		private List<User> userLikes;
		
		public Movie() {
			
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public Date getCreatedAt() {
			return createdAt;
		}
		public void setCreatedAt(Date createdAt) {
			this.createdAt = createdAt;
		}
		public Date getUpdatedAt() {
			return updatedAt;
		}
		public void setUpdatedAt(Date updatedAt) {
			this.updatedAt = updatedAt;
		}
		public String getMovieTitle() {
			return movieTitle;
		}
		public void setMovieTitle(String movieTitle) {
			this.movieTitle = movieTitle;
		}
		public String getReviewTitle() {
			return reviewTitle;
		}
		public void setReviewTitle(String reviewTitle) {
			this.reviewTitle = reviewTitle;
		}
		public Date getDate() {
			return date;
		}
		public void setDate(Date date) {
			this.date = date;
		}
		public String getReview() {
			return review;
		}
		public void setReview(String review) {
			this.review = review;
		}
		public Integer getRating() {
			return rating;
		}
		public void setRating(Integer rating) {
			this.rating = rating;
		}
		public User getLead() {
			return lead;
		}
		public void setLead(User lead) {
			this.lead = lead;
		}
		public List<User> getUserLikes() {
			return userLikes;
		}
		public void setUserLikes(List<User> userLikes) {
			this.userLikes = userLikes;
		}
		
}
