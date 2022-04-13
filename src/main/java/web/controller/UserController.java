package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.entity.User;
import web.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping()
	public String listOfUsers(ModelMap model) {
		model.addAttribute("users", this.userService.listUsers());
		return "users";
	}

	@GetMapping(value = "/add")
	public String createNewUser(ModelMap model){
		model.addAttribute("user", new User());
		return "newUser";
	}

	@PostMapping(value = "/add")
	public String addUser(@ModelAttribute("user") User user){
		this.userService.add(user);
		return "redirect:/users";
	}

	@GetMapping("/edit/{id}")
	public String editUser(@PathVariable("id") long id, ModelMap model) {
		model.addAttribute("user", this.userService.userById(id));
		return "editUser";
	}

	@PostMapping(value = "/edit/{id}")
	public String UpdateUser(@PathVariable("id") long id, @ModelAttribute("user") User user){
		this.userService.updateUser(id, user);
		return "redirect:/users";
	}

	@GetMapping("/delete/{id}")
	public String editUser(@PathVariable("id") long id) {
		this.userService.deleteUser(id);
		return "redirect:/users";
	}
}