package com.rdlab.marketplace.controller;

import com.rdlab.marketplace.service.LotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShowItemsController {

  private final LotService lotService;


  @Autowired
  public ShowItemsController(LotService lotService) {
    this.lotService = lotService;
  }

  @GetMapping("/show-items")
  public String showItems(Model model) {

    var lots = lotService.getAllLotsFromDAO();

    model.addAttribute("lotList", lots);

    return "show";
  }

  @GetMapping("/show-items/my-items")
  public String getLotByUser(Model model) {
    var authentication = SecurityContextHolder.getContext().getAuthentication();
    var user = authentication.getName();
    model.addAttribute("lotList", lotService.getLotByUserIDFromDAO(user));
    model.addAttribute("currentUser", user);
    return "show";
  }


}
