package com.rdlab.marketplace.service;

import com.rdlab.marketplace.dao.LotDao;
import com.rdlab.marketplace.domain.Lot;
import com.rdlab.marketplace.domain.User;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class LotService {

  private final LotDao lotDAO;

  public LotService(LotDao lotDAO) {
    this.lotDAO = lotDAO;
  }

  @Transactional
  public Lot getLotFromDAO(int id) {
    return lotDAO.findById(id);
  }

  @Transactional
  public List<Lot> getLotByUsernameFromDAO(String username) {
    return lotDAO.findLotByUsername(username);
  }

  @Transactional
  public List<Lot> getLotByBidUsernameFromDAO(String bidUsername) {
    return lotDAO.findLotByBidUsername(bidUsername);
  }

  @Transactional
  public List<Lot> searchLot(String search) {
    return lotDAO.findLotThatContainsParameterInTheTitleOfItem(search);

  }

  @Transactional
  public List<Lot> getAllLotsFromDAO() {
    return lotDAO.findAll().stream()
        .filter(Lot::getIsActive)
        .collect(Collectors.toList());
  }

  @Transactional
  public boolean addBidder(User user, int id, double price) {
    Lot lot = lotDAO.findById(id);
    if (user.getUsername().equals(lot.getUser().getUsername())) {
      return false;
    }
    if (price <= lot.getStartPrice() || price <= lot.getBidInc()) {
      return false;
    }
    lot.setBid(user);
    lot.setBidInc(price);
    return true;
  }

  @Transactional
  public void saveNewLot(Lot lot) {
    lotDAO.save(lot);
  }
}
