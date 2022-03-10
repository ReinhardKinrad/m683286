package com.rdlab.marketplace.dao;

import com.rdlab.marketplace.domain.Item;
import org.springframework.stereotype.Repository;

@Repository
public class ItemDaoImpl extends AbstractDao<Item> implements ItemDao{

  public ItemDaoImpl() {
    setDomainType(Item.class);
  }
}
