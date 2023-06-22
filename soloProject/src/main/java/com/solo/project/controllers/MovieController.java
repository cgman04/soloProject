package com.solo.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.solo.project.models.Movie;
import com.solo.project.models.User;
import com.solo.project.services.MovieService;
import com.solo.project.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class MovieController {
	@Autowired
	private MovieService movieServ;
	@Autowired
	private UserService userServ;
	
	@GetMapping("/new/review")
	public String newReview(@ModelAttribute("newReview") Movie newReview) {
		return "newreview.jsp";
	}
	@PostMapping("/create")
	public String createReview(@Valid @ModelAttribute("newReview") Movie newReview, BindingResult result, HttpSession session) {
		if(session.getAttribute("userId") !=null) {
			if(result.hasErrors()) {
				System.out.println(newReview);
				return "newreview.jsp";
			}
			newReview.setLead(userServ.getById((long)session.getAttribute("userId")));
			movieServ.createMovie(newReview);
				return "redirect:/dashboard";

		}
		return "redirect:/";
	}
	@GetMapping("/edit/review/{id}")
	public String editReview(@PathVariable("id") Long reviewId, Model model, HttpSession session) {
		Movie thisMovieReview = movieServ.getById(reviewId);
		if(session.getAttribute("userId").equals(thisMovieReview.getLead().getId())) {
			model.addAttribute("reviewToEdit", thisMovieReview);
			return "editreview.jsp";
		}
		return "redirect:/dashoard";
	}
	@PutMapping("/update/review/{id}")
	public String updateReview(@Valid @ModelAttribute("reviewToEdit") Movie movieReview,
			BindingResult result, @PathVariable("id")Long reviewtId, HttpSession session) {
		if(session.getAttribute("userId") != null) {
			if(result.hasErrors()) {
				return "editreview.jsp";
			}
			movieReview.setLead(userServ.getById((long)session.getAttribute("userId")));
			movieServ.updateMovie(movieReview);
			return "redirect:/dashboard";
		}
		return "redirect:/";
	}
	@GetMapping("/review/{id}")
	public String showReview(@PathVariable("id") Long reviewId, Model model, HttpSession session) {
		if(session.getAttribute("userId") != null) {
			model.addAttribute("movie", movieServ.getById(reviewId));
			System.out.println(model);
			return "showreview.jsp";
		}
		return "redirect:/";
	}
	@GetMapping("/delete/review/{id}")
	public String deleteReview(@PathVariable("id") Long reviewId) {
		movieServ.deleteById(reviewId);
		return "redirect:/dashboard";
	}
	@GetMapping("/like/{reviewId}")
	public String likePost(@PathVariable("reviewId")Long reviewId, HttpSession session) {
		if (session.getAttribute("userId") != null) {
			Movie thisMovie = movieServ.getById(reviewId);
			User thisUser = userServ.getById((long)session.getAttribute("userId"));
			this.movieServ.likePost(thisUser, thisMovie);
			return "redirect:/dashboard";
		}
		return "redirect:/";
	}
	@GetMapping("/dislike/{reviewId}")
	public String dislikePost(@PathVariable("reviewId")Long reviewId, HttpSession session) {
		if (session.getAttribute("userId") != null) {
			Movie thisMovie = movieServ.getById(reviewId);
			User thisUser = userServ.getById((long)session.getAttribute("userId"));
			this.movieServ.dislikePost(thisUser, thisMovie);
			return "redirect:/dashboard";
		}
		return "redirect:/";
	}
}

