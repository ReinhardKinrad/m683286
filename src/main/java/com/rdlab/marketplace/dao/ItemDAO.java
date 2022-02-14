package com.rdlab.marketplace.dao;

import com.rdlab.marketplace.domain.Item;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class ItemDAO {

  private final SessionFactory sessionFactory;

  public ItemDAO(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  public Item findById(int id) {
    return sessionFactory.getCurrentSession().get(Item.class, id);
  }

  public void saveItem(Item item) {
    sessionFactory.getCurrentSession().save(item);
  }

}
