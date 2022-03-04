package com.rdlab.marketplace.service;

import com.rdlab.marketplace.dao.GenericDao;
import com.rdlab.marketplace.domain.Item;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

  private final GenericDao<Item> itemDAO;

  public ItemService(GenericDao<Item> itemDAO)
  {
    this.itemDAO = itemDAO;
    itemDAO.setDomainType(Item.class);
  }

  @Transactional
  public void saveItem(Item item) {
    itemDAO.save(item);
  }
}
