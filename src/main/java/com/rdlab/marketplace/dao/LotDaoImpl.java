package com.rdlab.marketplace.dao;

import com.rdlab.marketplace.domain.Lot;
import com.rdlab.marketplace.util.StringUtil;
import java.util.List;
import javax.management.Query;
import org.springframework.stereotype.Repository;

@Repository
public class LotDaoImpl extends AbstractDao<Lot> implements LotDao {

  public LotDaoImpl() {
    setDomainType(Lot.class);
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Lot> findAll() {
    return (List<Lot>) getSessionFactory()
        .createQuery(
            "FROM Lot WHERE isActive = true ORDER BY id"
        )
        .list();
  }

  @SuppressWarnings("unchecked")
  @Override
  public final List<Lot> findLotByUsername(String username) {
    return (List<Lot>) getSessionFactory()
        .createQuery
            (
                "FROM Lot WHERE user.username =:userName ORDER BY id"
            )
        .setParameter("userName", username)
        .list();
  }

  @SuppressWarnings("unchecked")
  @Override
  public final List<Lot> findLotByBidUsername(String bidUsername) {
    return (List<Lot>) getSessionFactory()
        .createQuery
            (
                "FROM Lot WHERE bid.username =:bidUsername ORDER BY id"
            )
        .setParameter("bidUsername", bidUsername)
        .list();
  }

  @SuppressWarnings("unchecked")
  @Override
  public final List<Lot> findLotThatContainsParameterInTheTitleOfItem(String searchLine) {
    return (List<Lot>) getSessionFactory()
        .createQuery
            (
                "FROM Lot WHERE LOWER(item.title) LIKE :searchLine"
            )
        .setParameter("searchLine",
            StringUtil.putPercentCharAtTheBeginningAndEndOfTheLine(searchLine))
        .list();
  }
}
