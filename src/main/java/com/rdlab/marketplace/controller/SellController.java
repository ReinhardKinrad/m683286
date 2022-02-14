package com.rdlab.marketplace.controller;

import com.rdlab.marketplace.dao.ItemDAO;
import com.rdlab.marketplace.domain.Item;
import com.rdlab.marketplace.domain.Lot;
import com.rdlab.marketplace.service.ItemService;
import com.rdlab.marketplace.service.LotService;
import com.rdlab.marketplace.service.SellService;
import com.rdlab.marketplace.service.UserService;
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

  @GetMapping("/new")
  public String selling(Model model) {
    model.addAttribute("itemForm", new Item());
    model.addAttribute("lotForm", new Lot());
    return "sell/new";
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
