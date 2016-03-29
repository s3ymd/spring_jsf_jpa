package jp.co._3sss.web.items;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import jp.co._3sss.domain.Item;
import jp.co._3sss.service.ItemRepository;

@Component
@Scope("request")
public class ItemEdit {
  private long id;
  
  private Item item = new Item();
 
  public void preRender() {
    item = itemRepository.findOne(id);
  }
  
  @Autowired
  private ItemRepository itemRepository;
  
  public String update() {
    Item item = itemRepository.findOne(id);
    item.setName(this.item.getName());
    item.setPrice(this.item.getPrice());
    itemRepository.save(item);
    
    return "index?faces-redirect=true";
  }

  public Item getItem() {
    return item;
  }

  public void setItem(Item item) {
    this.item = item;
  }

  public void setId(long id) {
    this.id = id;
  }
  
  public long getId() {
    return id;
  }
  
}
