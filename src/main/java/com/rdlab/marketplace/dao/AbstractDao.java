package com.rdlab.marketplace.dao;

import java.util.List;
import java.util.Random;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unchecked")
@Repository
public abstract class AbstractDao<T> implements GenericDao<T> {

  private Class<T> daoType;

  protected SessionFactory sessionFactory;

  @Autowired
  public void setSessionFactory(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  public void setDomainType(Class<T> domainType) {
    if (domainType != null) {
      this.daoType = domainType;
    }
  }

  public T findById(int id) {
    return sessionFactory.getCurrentSession().get(daoType, id);
  }

  public List<T> findAll() {
    return sessionFactory.getCurrentSession()
        .createQuery("from "
            + daoType.getName()
            + " ORDER BY "
            + daoType.getName() + ".id").list();
  }

  public void save(T entity) {
    if (entity != null) {
      sessionFactory.getCurrentSession().saveOrUpdate(entity);
    }
  }

  public void update(int id, T entity) {
    getSessionFactory().update(entity);
  }

  public Session getSessionFactory() {
    return sessionFactory.getCurrentSession();
  }
}
