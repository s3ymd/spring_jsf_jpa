package jp.co._3sss.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthenticationFilter implements Filter {

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
      throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest) servletRequest;
    HttpServletResponse response = (HttpServletResponse) servletResponse;
    String url = request.getRequestURI();
    String role = (String) request.getSession(true).getAttribute("role");
    System.out.printf("role=[%s], url=[%s]\n", role, url);
    
    if (accessible(role, url)) {
      chain.doFilter(request, response);
    } else {
      response.getWriter().write("You can not access this page.");
    }
  }

  private boolean accessible(String role, String url) {
    if ("admin".equals(role)) {
      return true;
    } else if ("normal".equals(role)) {
      if (url.startsWith("/index")) {
        return true;
      } else if (url.startsWith("/items/index")) {
        return true;
      } else if (url.startsWith("/items/show")) {
        return true;
      } else if (url.startsWith("/session/login")) {
        return true;
      } else if (url.startsWith("/session/logout")) {
        return true;
      } else {
        return false;
      }
    } else {
      if (url.startsWith("/index")) {
        return true;
      } else if (url.startsWith("/items/index")) {
        return true;
      } else if (url.startsWith("/items/show")) {
        return true;
      } else if (url.startsWith("/session/login")) {
        return true;
      } else if (url.startsWith("/session/logout")) {
        return true;
      } else {
        return false;
      }
    }
  }

  @Override
  public void init(FilterConfig arg0) throws ServletException {
  }

  @Override
  public void destroy() {
  }

}
