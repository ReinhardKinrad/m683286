package com.rdlab.marketplace.dao;

import java.util.List;

public interface GenericDao<T> {
  void setDaoType(Class< T > daoType);
  T findById(int id);
  List<T> findAll();
  void save(T entity);

  // TODO: 15.02.2022
//  T update(T entity);
//  void delete(T entity);
//  void deleteById(T entity);


}
