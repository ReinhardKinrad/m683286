package com.rdlab.marketplace.controller;

import com.rdlab.marketplace.domain.User;
import com.rdlab.marketplace.service.UserService;
import com.rdlab.marketplace.validator.UserValidator;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

  private final UserService userService;
  private final UserValidator userValidator;

  public RegistrationController(UserService userService, UserValidator userValidator) {
    this.userService = userService;
    this.userValidator = userValidator;
  }

  @InitBinder
  public void initBinder(WebDataBinder webDataBinder) {
    StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
    webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
  }

  @GetMapping("/registration")
  public String registration(Model model) {
    model.addAttribute("userForm", new User());
    return "registration";
  }

  @PostMapping("/registration")
  public String createUser(@ModelAttribute("userForm") User userForm,
      BindingResult bindingResult, Model model) {

    userValidator.validate(userForm, bindingResult);

    if (bindingResult.hasErrors()) {
      return "registration";
    }

    if (!userForm.getPassword().equals(userForm.getConfirmPassword())) {
      model.addAttribute("confirmPassNotEquals", "Confirm password not equals with password");
      return "registration";
    }

    if (!userService.saveUser(userForm)) {
      model.addAttribute("userError", "such a user is already registered");
      return "registration";
    }

    return "redirect:/login";
  }
}
