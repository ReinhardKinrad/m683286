package com.rdlab.marketplace.dao;

import com.rdlab.marketplace.domain.Lot;
import java.util.List;

public interface LotDao extends GenericDao<Lot> {

  List<Lot> findLotByUsername(String username);
  List<Lot> findLotByBidUsername(String bidUsername);
  List<Lot> findLotThatContainsParameterInTheTitleOfItem(String searchLine);

}
