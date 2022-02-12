package com.rdlab.marketplace.dao;

import com.rdlab.marketplace.domain.User;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
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
    Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
    criteria.add(Restrictions.eq("username", username));
    return (User) criteria.uniqueResult();
  }

  public void saveUser() {

  }

  public List<User> getUsers() {
    List<User> list;
    Session session = sessionFactory.getCurrentSession();
    return null;

  }

}
