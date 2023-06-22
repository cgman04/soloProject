package com.solo.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.solo.project.models.User;
import com.solo.project.services.MovieService;
import com.solo.project.services.UserService;
import com.solo.project.validators.LoginUser;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/")
public class UserController {
	
	@Autowired 
	private MovieService movieServ;
	
	@Autowired
	private UserService userServ;
	
	@GetMapping("")
	public String logReg(Model model) {
		model.addAttribute("newUser", new User());
		model.addAttribute("newLogin", new LoginUser());
		return "logreg.jsp";
	}
	
	@PostMapping("register")
	public String registerUser(@Valid @ModelAttribute("newUser") User newUser, BindingResult result, Model model, HttpSession session) {
			User thisUser = userServ.register(newUser, result);
			if(result.hasErrors()) {
				model.addAttribute("newLogin", new LoginUser());
				return "logreg.jsp";
			}
			session.setAttribute("userId", thisUser.getId());
			return "redirect:/dashboard";
	}
	@PostMapping("login")
	public String loginUser(@Valid @ModelAttribute("newLogin") LoginUser newLogin, BindingResult result, 
			Model model, HttpSession session) {
		User thisUser =userServ.login(newLogin, result);
			if(result.hasErrors()) {
				model.addAttribute("newUser", new User());
				return "logreg.jsp";
			}
		session.setAttribute("userId", thisUser.getId());
		return "redirect:/dashboard";
	}
	
	@GetMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	@GetMapping("dashboard")
	public String homePage(Model model, HttpSession session) {
		if(session.getAttribute("userId") != null) {
			User thisUser = userServ.getById((long) session.getAttribute("userId"));
			model.addAttribute("user", thisUser);
			model.addAttribute("movieReviews", this.movieServ.getAll());
			model.addAttribute("myLikes", this.movieServ.getLikeByUser(thisUser));
			return "dashboard.jsp";
		}
		return "redirect:/";
	}
	
}
