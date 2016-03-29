package jp.co._3sss.web.session;

import java.io.IOException;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class SessionBean {
  
  private String userId;
  private String password;
  private String role;
  
  public String login() {
    if ("user".equals(userId) && "user".equals(password)) {
      
      FacesContext facesContext = FacesContext.getCurrentInstance();
      HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
      session.setAttribute("role", "normal");
      
      return "/index?faces-redirect=true";
    } else if ("admin".equals(userId) && "admin".equals(password)) {
      
      FacesContext facesContext = FacesContext.getCurrentInstance();
      HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
      session.setAttribute("role", "admin");
      
      return "/index?faces-redirect=true";
    } else {
      return null;
    }
  }
  
  public void logout() throws IOException {
    userId = password = role = null;
    
    FacesContext facesContext = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
    session.removeAttribute("role");
    
    ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
    ec.redirect(ec.getRequestContextPath() + "/index.xhtml");
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }
  
  
}
