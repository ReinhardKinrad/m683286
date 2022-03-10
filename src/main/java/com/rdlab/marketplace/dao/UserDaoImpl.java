package com.rdlab.marketplace.dao;

import com.rdlab.marketplace.domain.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends AbstractDao<User> implements UserDao {


  @Override
  public User findByUsername(String username) {
    return (User) getSessionFactory().createQuery("from User where username=:username")
        .setParameter("username", username).uniqueResult();
  }
}
