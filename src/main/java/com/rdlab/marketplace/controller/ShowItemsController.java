package com.rdlab.marketplace.controller;

import com.rdlab.marketplace.domain.Lot;
import com.rdlab.marketplace.service.LotService;
import com.rdlab.marketplace.service.UserService;
import com.rdlab.marketplace.util.SecurityUtil;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ShowItemsController {

  private final LotService lotService;
  private final UserService userService;


  @Autowired
  public ShowItemsController(LotService lotService, UserService userService) {
    this.lotService = lotService;
    this.userService = userService;
  }

  @ModelAttribute
  private void addCurrentUserToModel(Model model) {
    model.addAttribute("currentUser", SecurityUtil.getUserFromSecurityContext());
  }

  @GetMapping("/show-items")
  public String showItems(Model model) {
    model.addAttribute("lotList", lotService.getAllLotsFromDAO());
    return "show";
  }

  // TODO: 21.02.2022 need to update this method and set model attribute to true value
  @PostMapping("/show-items/{id}")
  public String addBid(@RequestParam(name = "price") double price, Model model,
      @PathVariable int id) {
    var user = userService.findByUsername((Objects.requireNonNull(
        model.getAttribute("currentUser"))).toString());
    if (lotService.addBidder(user, id, price)) {
      model.addAttribute("bid", "OK");
    } else {
      model.addAttribute("bid", "NOT OK");
    }
    return "redirect:/show-items";
  }

  @GetMapping("/show-items/search")
  public String searchLot(Model model, @RequestParam(name = "item") String item) {
    model.addAttribute("lotList", lotService.searchLot(item));
    return "show";
  }


  @GetMapping("/show-items/my-items")
  public String getLotByUser(Model model) {
    List<Lot> list = lotService.getLotByUsernameFromDAO("Orlando");
    for (Lot lot : list) {
      System.out.println(lot.getId());
    }
    model.addAttribute("lotList",
        lotService.getLotByUsernameFromDAO(
            Objects.requireNonNull(model.getAttribute("currentUser")).toString()));
    return "show";
  }

  @GetMapping("/show-items/my-bids")
  public String getLotByBid(Model model) {
    model.addAttribute("lotList",
        lotService.getLotByBidUsernameFromDAO((Objects.requireNonNull(
            model.getAttribute("currentUser"))).toString()));
    return "show";
  }
}
