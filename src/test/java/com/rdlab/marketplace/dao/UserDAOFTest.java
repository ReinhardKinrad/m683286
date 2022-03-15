package com.rdlab.marketplace.dao;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



class UserDAOFTest {


  private final SessionFactory sessionFactory;

  UserDAOFTest(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  @BeforeEach
  void setUp() {
  }

  @AfterEach
  void tearDown() {
  }

  @Test
  void findByID() {
  }
}