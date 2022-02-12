package com.rdlab.marketplace.service;

import com.rdlab.marketplace.dao.UserDAO;
import com.rdlab.marketplace.domain.User;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserDAO userDAO;

  @Autowired
  public UserService(UserDAO userDAO) {
    this.userDAO = userDAO;
  }

  @Transactional
  public User findByUsername(String username) {
    return userDAO.findByUsername(username);
  }

  @Transactional
  public User getUser(int id) {
    return userDAO.findByID(id);
  }
}
