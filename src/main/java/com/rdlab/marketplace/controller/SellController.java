package com.rdlab.marketplace.controller;

import com.rdlab.marketplace.domain.Item;
import com.rdlab.marketplace.domain.Lot;
import com.rdlab.marketplace.service.SellService;
import com.rdlab.marketplace.util.SecurityUtil;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sell")
public class SellController {

  private final SellService sellService;

  public SellController(SellService sellService) {
    this.sellService = sellService;
  }

  @ModelAttribute
  private void addUserToModelFromSecurityContext(Model model) {
    model.addAttribute("currentUser", SecurityUtil.getUserFromSecurityContext());
  }

  @GetMapping("/new")
  public String selling(Model model) {
    model.addAttribute("itemForm", new Item());
    model.addAttribute("lotForm", new Lot());
    return "new";
  }

  @PostMapping("/new")
  public String createLot(@ModelAttribute("lotForm") Lot lotForm,
      @ModelAttribute("itemForm") Item itemForm,
      Model model) {
    var auth = SecurityContextHolder.getContext().getAuthentication();
    var user = auth.getName();
    sellService.create(user, itemForm, lotForm);
    return "redirect:/show-items";
  }


}
