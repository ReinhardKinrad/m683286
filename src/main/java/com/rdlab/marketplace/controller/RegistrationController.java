package com.rdlab.marketplace.controller;

import com.rdlab.marketplace.domain.User;
import com.rdlab.marketplace.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

  private final UserService userService;

  public RegistrationController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/registration")
  public String registration(Model model) {
    model.addAttribute("userForm", new User());
    return "registration";
  }

  @PostMapping("/registration")
  public String createUser(@ModelAttribute("userForm") User userForm, Model model) {

    if (!userService.saveUser(userForm)) {
      model.addAttribute("userError", "not valid");
      return "registration";
    }

    return "redirect:/show-items";
  }
}
