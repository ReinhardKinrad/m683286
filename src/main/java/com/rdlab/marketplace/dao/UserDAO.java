package com.rdlab.marketplace.dao;

import com.rdlab.marketplace.domain.User;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

@Component
public class UserDAO {


  private final SessionFactory sessionFactory;

  public UserDAO(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  public User findByID(int id) {
    Session currentSession = sessionFactory.getCurrentSession();
    return currentSession.get(User.class, id);
  }

  public User findByUsername(String username) {
    return sessionFactory.getCurrentSession().get(User.class, username);
  }

  public List<User> getUsers() {
    List<User> list;
    Session session = sessionFactory.getCurrentSession();
    return null;

  }

}
