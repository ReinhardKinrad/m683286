package com.rdlab.marketplace.rest.controller;

import com.rdlab.marketplace.domain.Lot;
import com.rdlab.marketplace.service.LotService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LotController {

  private final LotService lotService;

  public LotController(LotService lotService) {
    this.lotService = lotService;
  }

  @GetMapping(path = "/lots", produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody
  ResponseEntity<List<Lot>> getAllLots() {
    List<Lot> lots = lotService.getAllLotsFromDAO();
    return lots == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
        : new ResponseEntity<>(lots, HttpStatus.OK);
  }
}
