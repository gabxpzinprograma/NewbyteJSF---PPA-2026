package com.example.ifbademo;

import javax.faces.webapp.FacesServlet;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.ServletContextAware;
import org.springframework.beans.factory.config.CustomScopeConfigurer;
import com.example.ifbademo.util.ViewScope;
import com.google.common.collect.ImmutableMap;

@SpringBootApplication
public class Ifbademo_vespApplication implements ServletContextAware {

    public static void main(String[] args) {
        SpringApplication.run(Ifbademo_vespApplication.class, args);
    }

    @Bean
    static CustomScopeConfigurer viewScope() {
        CustomScopeConfigurer configurer = new CustomScopeConfigurer();
        configurer.setScopes(new ImmutableMap.Builder<String, Object>().put("view", new ViewScope()).build());
        return configurer;
    }

    @Bean
    ServletRegistrationBean<FacesServlet> servletRegistrationBean() {
        ServletRegistrationBean<FacesServlet> bean = new ServletRegistrationBean<>(
                new FacesServlet(), "*.xhtml");
        bean.setLoadOnStartup(1);
        bean.setMultipartConfig(new MultipartConfigElement("", 10485760, 10485760, 0));
        return bean;
    }

    public void setServletContext(ServletContext servletContext) {
        servletContext.setInitParameter("com.sun.faces.forceLoadConfiguration", Boolean.TRUE.toString());
        servletContext.setInitParameter("javax.faces.FACELETS_SKIP_COMMENTS", "true");
    }
}