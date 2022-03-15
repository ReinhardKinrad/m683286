package com.rdlab.marketplace.dao;

import java.util.List;

public interface GenericDao<T> {
  void setDomainType(Class< T > domainType);
  T findById(int id);
  List<T> findAll();
  void save(T entity);
  void update(int id, T entity);

  // TODO: 15.02.2022
//  void delete(T entity);
//  void deleteById(T entity);


}
