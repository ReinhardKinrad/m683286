package com.rdlab.marketplace.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unchecked")
@Repository
public abstract class AbstractDao<T> {

  private Class<T> daoType;

  @Autowired
  protected SessionFactory sessionFactory;


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

  public void delete() {

  }

  public Session getSessionFactory() {
    return sessionFactory.getCurrentSession();
  }
}
