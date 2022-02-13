package com.rdlab.marketplace.service;

import com.rdlab.marketplace.dao.UserDAO;
import com.rdlab.marketplace.domain.User;
import com.rdlab.marketplace.domain.UserRole;
import java.util.Collections;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserDAO userDAO;
  private final PasswordEncoder passwordEncoder;

  public UserService(UserDAO userDAO,
      @Qualifier("passwordEncoder") PasswordEncoder passwordEncoder) {
    this.userDAO = userDAO;
    this.passwordEncoder = passwordEncoder;
  }

  @Transactional
  public User findByUsername(String username) {
    return userDAO.findByUsername(username);
  }

  @Transactional
  public List<User> getAllUsers() {
    return userDAO.getUsers();
  }

  @Transactional
  public User getUser(int id) {
    return userDAO.findByID(id);
  }

  @Transactional
  public boolean saveUser(User user) {
    User userFromDB = userDAO.findByUsername(user.getUsername());
    if (userFromDB != null) {
      return false;
    }
    user.setUserRoles(Collections.singleton(new UserRole(2, "ROLE_USER")));
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    userDAO.saveUser(user);
    return true;
  }
}
