package com.rdlab.marketplace.service;

import com.rdlab.marketplace.dao.LotDAO;
import com.rdlab.marketplace.domain.Lot;
import com.rdlab.marketplace.domain.User;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class LotService {

  private final LotDAO lotDAO;

  public LotService(LotDAO lotDAO) {
    this.lotDAO = lotDAO;
  }

  @Transactional
  public Lot getLotFromDAO(int id) {
    return lotDAO.getLotById(id);
  }

  @Transactional
  public List<Lot> getLotByUserIDFromDAO(String username) {
    return lotDAO.getLots().stream().filter((lot) -> lot.getUser().getUsername().equals(username))
        .collect(Collectors.toList());
  }

  @Transactional
  public List<Lot> getAllLotsFromDAO() {
    return lotDAO.getLots();
  }

  @Transactional
  public void saveNewLot(Lot lot) {
    lotDAO.saveLot(lot);
  }
}
