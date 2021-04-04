//package com.fun.club.web;
//
//import java.io.IOException;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.catalina.connector.RequestFacade;
//import org.springframework.core.Ordered;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import com.fun.club.enums.HeaderEnum;
//
//@Order(Ordered.HIGHEST_PRECEDENCE)
//@Component
//public class CorsFilter implements Filter {
//  @Override
//  public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
//        throws IOException, ServletException {
//    System.out.println("do filter called");
//    HttpServletResponse response = (HttpServletResponse) res;
//    response.setHeader("Access-Control-Allow-Origin", "*");
//    response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT, PATCH");
//    response.setHeader("Access-Control-Max-Age", "3600");
//    response.setHeader("Access-Control-Allow-Headers",
//          "Content-Type, Access-Control-Allow-Headers, Authorization, username, password, token");
//    response.setHeader("Access-Control-Expose-Headers",
//          HeaderEnum.TOTAL_PAGES.getValue() + ", " + HeaderEnum.TOTAL_ELEMENTS.getValue());
//
//    // handle pre-flight requests
//    if (req instanceof RequestFacade) {
//      System.out.println("1.handle pre-flight requests");
//      RequestFacade rf = (RequestFacade) req;
//      if ("OPTIONS".equalsIgnoreCase(rf.getMethod())) {
//        response.setStatus(HttpServletResponse.SC_OK);
//        return;
//      }
//    }
//
//    chain.doFilter(req, res);
//  }
//
//  @Override
//  public void init(FilterConfig filterConfig) {
//    // do nothing
//  }
//
//  @Override
//  public void destroy() {
//    // do nothing
//  }
//}
