package com.rdlab.marketplace.controller;

import com.rdlab.marketplace.domain.Lot;
import com.rdlab.marketplace.service.LotService;
import com.rdlab.marketplace.util.SecurityUtil;
import com.rdlab.marketplace.validator.ItemValidator;
import com.rdlab.marketplace.validator.LotValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/edit")
public class EditItemsController {

  private final LotService lotService;
  private final LotValidator lotValidator;
  private final ItemValidator itemValidator;

  @Autowired
  public EditItemsController(LotService lotService, LotValidator lotValidator,
      ItemValidator itemValidator) {
    this.lotService = lotService;
    this.lotValidator = lotValidator;
    this.itemValidator = itemValidator;
  }

  @ModelAttribute
  private void addCurrentUserToModel(Model model) {
    model.addAttribute("currentUser", SecurityUtil.getUserFromSecurityContext());
  }

  @GetMapping("/{id}")
  public String showItem(@PathVariable("id") Integer id, Model model) {
    var lot = lotService.getLotFromDAO(id);
    if (lot == null
        || !(lot.getUser().getUsername().equals(model.getAttribute("currentUser")))) {
      return "redirect:/show-items";
    }
    model.addAttribute("lotForm", lotService.getLotFromDAO(id));
    return "edit";
  }

  @PostMapping("/{id}")
  public String editItem(@PathVariable("id") int id, @ModelAttribute("lotForm") Lot lotForm,
      BindingResult bindingResult, Model model) {
    lotValidator.validateEditLotForm(lotForm, bindingResult);
    itemValidator.validate(lotForm.getItem(), bindingResult);
    if (bindingResult.hasErrors()) {
      model.addAttribute("lotForm", lotForm);
      return "edit";
    }
    lotService.updateLot(id, lotForm);
    return "redirect:/show-items";
  }
}
