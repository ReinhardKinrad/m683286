package com.rdlab.marketplace.service;

import com.rdlab.marketplace.dao.UserDAO;
import com.rdlab.marketplace.domain.User;
import com.rdlab.marketplace.domain.UserRole;
import java.util.Collections;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserDAO userDao;
  private final PasswordEncoder passwordEncoder;

  public UserService(UserDAO userDao,
      @Qualifier("passwordEncoder") PasswordEncoder passwordEncoder
  ) {
    this.passwordEncoder = passwordEncoder;
    this.userDao = userDao;
    userDao.setDaoType(User.class);
  }

  @Transactional
  public User findByUsername(String username) {
    return userDao.findByUsername(username);
  }

  @Transactional
  public List<User> getAllUsers() {
    return userDao.findAll();
  }

  @Transactional
  public User getUser(int id) {
    return userDao.findById(id);
  }

  @Transactional
  public boolean saveUser(User user) {
    User userFromDB = userDao.findByUsername(user.getUsername());
    if (userFromDB != null) {
      return false;
    }
    user.setUserRoles(Collections.singleton(new UserRole(2, "ROLE_USER")));
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    userDao.save(user);
    return true;
  }
}
