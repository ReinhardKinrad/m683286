package com.rdlab.marketplace.dao;

import com.rdlab.marketplace.domain.Lot;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

@Component
public class LotDAO {

  private final SessionFactory sessionFactory;

  public LotDAO(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  public Lot getLotById(int id) {
    return sessionFactory.getCurrentSession().get(Lot.class, id);
  }

  @SuppressWarnings("unchecked")
  public List<Lot> getLotByUserID(int id) {
    return (List<Lot>) sessionFactory.getCurrentSession()
        .createQuery("from Lot where User.id = :paramID").setParameter("paramID", id).list();
  }

  @SuppressWarnings("unchecked")
  public List<Lot> getLots() {
    return (List<Lot>) sessionFactory.getCurrentSession().createQuery("from Lot order by id")
        .list();
  }
}
