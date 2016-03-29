package jp.co._3sss.web.items;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import jp.co._3sss.service.ItemRepository;

@Component
@Scope("request")
public class ItemDelete {
  
  @Autowired
  private ItemRepository itemRepository;
  
  public String delete(long id) {
    itemRepository.delete(id);
    return "index?faces-redirect=true";
  }
  
}
