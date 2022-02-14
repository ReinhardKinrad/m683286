package com.rdlab.marketplace.service;

import com.rdlab.marketplace.dao.ItemDAO;
import com.rdlab.marketplace.domain.Item;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

  private final ItemDAO itemDAO;

  public ItemService(ItemDAO itemDAO) {
    this.itemDAO = itemDAO;
  }

  @Transactional
  public void saveItem(Item item) {
    itemDAO.saveItem(item);
  }
}
