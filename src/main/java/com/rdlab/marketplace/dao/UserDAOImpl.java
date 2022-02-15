package com.rdlab.marketplace.dao;

import com.rdlab.marketplace.domain.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl extends GenericDaoImpl<User> implements UserDAO {


  @Override
  public User findByUsername(String username) {
    return (User) getSessionFactory().createQuery("from User where username=:username")
        .setParameter("username", username).uniqueResult();
  }
}
