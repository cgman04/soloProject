package com.solo.project.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.solo.project.models.Movie;
import com.solo.project.models.User;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Long> {
	List<Movie> findAll();
	List<Movie> findAllByUserLikesNotContains(User user);
	List<Movie> findAllByUserLikesContains(User user);
}
