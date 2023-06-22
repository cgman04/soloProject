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
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="users")
public class User {
	
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
	
	@NotEmpty(message = "Username is required!")
	@Size(min = 3, max = 30, message = "Username must be between 3 and 30 characters.")
	private String userName;
	
	@NotEmpty(message = "Email is required!")
	@Email(message = "Please enter a valid email.")
	private String email;
	
	@NotEmpty(message = "Password is required!")
	@Size(min = 8, max = 130, message = "Password must be between 8 and 130 characters.")
	private String password;
	
	@Transient
	@NotEmpty(message = "Confirm Password is required!")
	@Size(min = 8, max = 130, message = "Confirm password must be between 8 and 130 characters.")
	private String confirm;
	
//	Define table relationship
	@OneToMany(mappedBy="lead", fetch = FetchType.LAZY)
	private List<Movie> movies;  
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name="likes",
			joinColumns = @JoinColumn(name="user_id"),
			inverseJoinColumns = @JoinColumn(name="movie_id")
			)
	private List<Movie> joinedMovies;
	
	public User() {
		
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirm() {
		return confirm;
	}
	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}
	public List<Movie> getMovies() {
		return movies;
	}
	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}
	public List<Movie> getJoinedMovies() {
		return joinedMovies;
	}
	public void setJoinedMovies(List<Movie> joinedMovies) {
		this.joinedMovies = joinedMovies;
	}
	
}
