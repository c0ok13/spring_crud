package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.entity.User;
import web.repository.UserRepository;
import web.repository.UserRepositoryImp;
import web.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	@Qualifier("userRepositoryImp")
	private UserRepository userService;
	@GetMapping()
	public String listOfUsers(ModelMap model) {
		model.addAttribute("users", this.userService.listUsers());
		return "users";
	}

	@GetMapping(value = "/add")
	public String createNewUser(ModelMap model){
		User user = new User();
		model.addAttribute("user", user);
		return "newUser";
	}

	@PostMapping(value = "/add")
	public String addUser(@ModelAttribute("user") User user){
		this.userService.add(user);

		return "redirect:/users";
	}

	@GetMapping("/edit/{id}")
	public String editUser(@PathVariable("id") long id, ModelMap model) {
		User editUser = this.userService.userById(id);

		model.addAttribute("user", editUser);
		return "editUser";
	}

	@PostMapping(value = "/edit/{id}")
	public String UpdateUser(@PathVariable("id") long id, @ModelAttribute("user") User user){
		user.setId(id);
		this.userService.updateUser(user);

		return "redirect:/users";
	}

	@GetMapping("/delete/{id}")
	public String editUser(@PathVariable("id") long id) {
		System.out.println(id);
		this.userService.deleteUser(id);

		return "redirect:/users";
	}
}