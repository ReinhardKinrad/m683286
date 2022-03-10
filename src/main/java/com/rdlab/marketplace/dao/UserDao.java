package com.rdlab.marketplace.dao;

import com.rdlab.marketplace.domain.User;

public interface UserDao extends GenericDao<User> {

  User findByUsername(String username);

}
