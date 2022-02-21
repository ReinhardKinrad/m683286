package com.rdlab.marketplace.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unchecked")
@Repository
public abstract class GenericDaoImpl<T> {

  private Class<T> daoType;

  @Autowired
  protected SessionFactory sessionFactory;


  public void setDaoType(Class<T> daoType) {
    if (daoType != null) {
      this.daoType = daoType;
    }
  }

  public T findById(int id) {
    return sessionFactory.getCurrentSession().get(daoType, id);
  }

  public List<T> findAll() {
    return sessionFactory.getCurrentSession().createQuery("from " + daoType.getName()).list();
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
