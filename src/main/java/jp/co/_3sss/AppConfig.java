package jp.co._3sss;

import javax.faces.webapp.FacesServlet;

import org.springframework.boot.context.embedded.ServletListenerRegistrationBean;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sun.faces.config.ConfigureListener;

@Configuration
public class AppConfig {


  @Bean
  public ServletRegistrationBean servletRegistrationBean() {
    FacesServlet servlet = new FacesServlet();
    ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(servlet, "*.xhtml");
    return servletRegistrationBean;
  }
  
  @Bean
  public ServletListenerRegistrationBean<ConfigureListener> jsfConfigureListener() {
      return new ServletListenerRegistrationBean<ConfigureListener>(new ConfigureListener());
  }
}
