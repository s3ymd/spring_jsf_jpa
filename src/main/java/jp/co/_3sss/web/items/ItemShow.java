package jp.co._3sss.web.items;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co._3sss.domain.Item;
import jp.co._3sss.service.ItemRepository;

@Component
public class ItemShow {

  private long id;

  private Item item;

  @Autowired
  private ItemRepository itemRepository;

  public void preRender() {
    item = itemRepository.findOne(id);
    System.out.println("show#preRender: " + item);
  }

  public Item getItem() {
    return item;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getId() {
    return id;
  }
}
