package com.rdlab.marketplace.service;

import com.rdlab.marketplace.dao.GenericDao;
import com.rdlab.marketplace.domain.Lot;
import com.rdlab.marketplace.domain.User;
import com.sun.istack.NotNull;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class LotService {

  private final GenericDao<Lot> genericDao;

  public LotService(GenericDao<Lot> genericDao) {
    this.genericDao = genericDao;
    genericDao.setDaoType(Lot.class);
  }

  @Transactional
  public Lot getLotFromDAO(int id) {
    return genericDao.findById(id);
  }

  @Transactional
  public List<Lot> getLotByUsernameFromDAO(String username) {
    return genericDao.findAll().stream()
        .filter((lot) -> lot.getUser().getUsername().equals(username))
        .collect(Collectors.toList());
  }

  @Transactional
  public List<Lot> getLotByBidUsernameFromDAO(String username) {
    return genericDao.findAll().stream()
        .filter(lot -> lot.getBid() != null && lot.getBid().getUsername().equals(username))
        .collect(
            Collectors.toList());
  }

  @Transactional
  public List<Lot> searchLot(String search) {
    return genericDao.findAll().stream()
        .filter(
            (lot ->
                lot.getItem().getTitle().toLowerCase(Locale.ROOT)
                    .contains(search.toLowerCase(
                        Locale.ROOT))))
        .collect(
            Collectors.toList());
  }

  @Transactional
  public List<Lot> getAllLotsFromDAO() {
    return genericDao.findAll().stream()
        .filter(Lot::getIsActive)
        .collect(Collectors.toList());
  }

  @Transactional
  public boolean addBidder(User user, int id, double price) {
    Lot lot = genericDao.findById(id);
    if (price <= lot.getStartPrice() || price <= lot.getBidInc()) {
      return false;
    }
    lot.setBid(user);
    lot.setBidInc(price);
    return true;
  }

  @Transactional
  public void saveNewLot(Lot lot) {
    genericDao.save(lot);
  }
}
