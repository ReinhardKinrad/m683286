package com.rdlab.marketplace.controller;

import com.rdlab.marketplace.service.LotService;
import com.rdlab.marketplace.util.SecurityUtil;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ShowItemsController {

  private final LotService lotService;


  @Autowired
  public ShowItemsController(LotService lotService) {
    this.lotService = lotService;
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

  @GetMapping("/show-items/search")
  public String searchLot(Model model, @RequestParam(name = "item") String item) {
    model.addAttribute("lotList", lotService.searchLot(item));
    return "show";
  }

  @GetMapping("/show-items/my-items")
  public String getLotByUser(Model model) {
    model.addAttribute("lotList",
        lotService.getLotByUserIDFromDAO(
            Objects.requireNonNull(model.getAttribute("currentUser")).toString()));
    return "show";
  }


}
