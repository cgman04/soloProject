package com.solo.project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solo.project.models.Movie;
import com.solo.project.models.User;
import com.solo.project.repositories.MovieRepository;

@Service
public class MovieService {
	
	@Autowired
	private MovieRepository movieRepo;
	
//	List all movies
	public List<Movie> getAll() {
		return this.movieRepo.findAll();
	}
	
//	Create new movie
	public Movie createMovie(Movie newReview) {
		return this.movieRepo.save(newReview);
	}
	
//	Delete a movie
	public void deleteById(Long id) {
		this.movieRepo.deleteById(id);
	}
	
//	Get one movie
	public Movie getById(Long id) {
		return this.movieRepo.findById(id).orElse(null); 
	}
//	Update movie
	public Movie updateMovie(Movie updatedMovie) {
		return this.movieRepo.save(updatedMovie);
	}
//	Like a post
	public void likePost(User user, Movie movie) {
		List<User> userLikes = movie.getUserLikes();
		userLikes.add(user);
		this.updateMovie(movie);
	}
//	Dislike a post
	public void dislikePost(User user, Movie movie) {
		List<User> userDisLikes = movie.getUserLikes();
		userDisLikes.remove(user);
		this.updateMovie(movie);
	}
	public List<Movie> getLikeByUser(User user) {
		return movieRepo.findAllByUserLikesContains(user);
	}
	public List<Movie> getLikeByNotUser(User user) {
		return movieRepo.findAllByUserLikesNotContains(user);
	}
}
