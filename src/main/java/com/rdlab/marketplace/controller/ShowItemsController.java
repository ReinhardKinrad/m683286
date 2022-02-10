package com.rdlab.marketplace.controller;

import com.rdlab.marketplace.domain.Lot;
import com.rdlab.marketplace.service.LotService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
  public String sayHello(Model model) {

    var lots = lotService.getLotByUserIDFromDAO(6);
    //List<Lot> lots = lotService.getLotByUserIDFromDAO(5);

    model.addAttribute("lotList", lots);

    return "show";
  }


}
