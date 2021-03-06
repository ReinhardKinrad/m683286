package com.rdlab.marketplace.service;

import com.rdlab.marketplace.domain.Lot;
import org.springframework.stereotype.Service;

@Service
public class SellService {

  private final UserService userService;
  private final LotService lotService;
  private final ItemService itemService;

  public SellService(UserService userService, LotService lotService,
      ItemService itemService) {
    this.userService = userService;
    this.lotService = lotService;
    this.itemService = itemService;
  }

  public void create(String user, Lot lotForm) {
    var userFromUserServiceByUsername = userService.findByUsername(user);
    itemService.saveItem(lotForm.getItem());
    lotForm.setUser(userFromUserServiceByUsername);
    lotForm.setIsActive(true);
    lotService.saveNewLot(lotForm);
  }
}
