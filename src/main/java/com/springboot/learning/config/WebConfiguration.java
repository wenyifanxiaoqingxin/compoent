package com.springboot.learning.config;

import org.apache.catalina.filters.RemoteIpFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by fx on 2018/4/11.
 */
@Configuration
public class WebConfiguration {
    @Bean
    public RemoteIpFilter remoteIpFilter(){
        return new RemoteIpFilter();
    }
    @Bean
    public FilterRegistrationBean filterRegistrationBean(){

        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new MyFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.addInitParameter("paramName","paramValue");
        registrationBean.setName("MyFilter");
        registrationBean.setOrder(1);
        return registrationBean;
    }

    public class MyFilter implements Filter{
        @Override
        public void init(FilterConfig filterConfig) throws ServletException {

        }

        @Override
        public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

            HttpServletRequest request = (HttpServletRequest) servletRequest;
            System.out.println("this is MyFilter,url:"+request.getRequestURI());
            filterChain.doFilter(servletRequest,servletResponse);
        }

        @Override
        public void destroy() {

        }
    }
}
