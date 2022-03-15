package com.rdlab.marketplace.controller;

import com.rdlab.marketplace.domain.Lot;
import com.rdlab.marketplace.service.SellService;
import com.rdlab.marketplace.util.SecurityUtil;
import com.rdlab.marketplace.validator.ItemValidator;
import com.rdlab.marketplace.validator.LotValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sell")
public class SellController {

  private final SellService sellService;
  private final LotValidator lotValidator;
  private final ItemValidator itemValidator;

  public SellController(SellService sellService, LotValidator lotValidator,
      ItemValidator itemValidator) {
    this.sellService = sellService;
    this.lotValidator = lotValidator;
    this.itemValidator = itemValidator;
  }

  @ModelAttribute
  private void addUserToModelFromSecurityContext(Model model) {
    model.addAttribute("currentUser", SecurityUtil.getUserFromSecurityContext());
  }

  @GetMapping("/new")
  public String selling(Model model) {
    model.addAttribute("lotForm", new Lot());
    return "new";
  }

  @PostMapping("/new")
  public String createLot(@ModelAttribute("lotForm") Lot lotForm, BindingResult lotBinding) {
    lotValidator.validate(lotForm, lotBinding);
    itemValidator.validate(lotForm.getItem(), lotBinding);
    if (lotBinding.hasErrors()) {
      return "new";
    }
    sellService.create(SecurityUtil.getUserFromSecurityContext(), lotForm);
    return "redirect:/show-items";
  }
}
