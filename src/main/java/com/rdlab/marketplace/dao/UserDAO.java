package com.rdlab.marketplace.dao;

import com.rdlab.marketplace.domain.User;

public interface UserDAO extends GenericDao<User> {

  User findByUsername(String username);

}
