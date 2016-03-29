package jp.co._3sss.web.items;

import java.io.IOException;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import jp.co._3sss.service.ItemRepository;

@Component
@Scope("request")
public class ItemDelete {
  private long id;
  
  @Autowired
  private ItemRepository itemRepository;
  
  public void preRender() throws IOException {
    itemRepository.delete(id);
    
    ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
    ec.redirect(ec.getRequestContextPath() + "/items/index.xhtml");
  }
  
  public void setId(long id) {
    this.id = id;
  }
  public long getId() {
    return id;
  }
}
