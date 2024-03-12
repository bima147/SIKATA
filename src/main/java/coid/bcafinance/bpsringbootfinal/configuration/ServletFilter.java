//package coid.bcafinance.bpsringbootfinal.configuration;
//
///*
//IntelliJ IDEA 2023.3.4 (Ultimate Edition)
//Build #IU-233.14475.28, built on February 13, 2024
//@Author Acer-01 a.k.a. Bima Prakoso
//Java Developer
//Created on 05/03/2024 14:35
//@Last Modified 05/03/2024 14:35
//Version 1.0
//*/
//
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//import java.util.Objects;
//import java.util.stream.Collectors;
//
//@Component
//@Order(1)
//public class ServletFilter implements Filter {
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        ServletRequest requestWrapper = null;
//        if (servletRequest instanceof HttpServletRequest) {
//            requestWrapper = new MyHttpServletRequestWrapper((HttpServletRequest) servletRequest);
//        }
//        if (Objects.isNull(requestWrapper)){
//            filterChain.doFilter(servletRequest, servletResponse);
//        } else {
//            filterChain.doFilter(requestWrapper, servletResponse);
//        }
//    }
//    private String getRequestBody(HttpServletRequest request) {
//        try {
//            return request.getReader().lines().collect(Collectors.joining());
//        }catch (Exception e){
//            e.printStackTrace();
//            return "{}";
//        }
//    }
//}