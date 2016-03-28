package jp.co._3sss.web.items;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import jp.co._3sss.domain.Item;
import jp.co._3sss.service.ItemRepository;

@Component
@Scope("request")
public class ItemCreate {
  private Item item = new Item();
  
  @Autowired
  private ItemRepository itemRepository;
  
  public String create() {
    itemRepository.save(item);
    
    System.out.println(item.getName());
    System.out.println(item.getPrice());
    System.out.println("create!");
    
    return "index?faces-redirect=true";
    
  }

  public Item getItem() {
    return item;
  }

  public void setItem(Item item) {
    this.item = item;
  }

  
}
