package jp.co._3sss.web.items;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co._3sss.domain.Item;
import jp.co._3sss.service.ItemRepository;

@Component
public class ItemIndex {
  
  private Iterable<Item> items;
  
  @Autowired
  private ItemRepository itemRepository;
  
  public void preRender() {
    items = itemRepository.findAll();
  }
  
  public Iterable<Item> getItems() {
    return items;
  }
  
  
}
