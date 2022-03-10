package com.rdlab.marketplace.service;

import com.rdlab.marketplace.dao.ItemDao;
import com.rdlab.marketplace.domain.Item;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

  private final ItemDao itemDAO;

  public ItemService(ItemDao itemDAO)
  {
    this.itemDAO = itemDAO;
  }

  @Transactional
  public void saveItem(Item item) {
    itemDAO.save(item);
  }
}
