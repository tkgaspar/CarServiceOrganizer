package com.ownproject.ServiceOrganizer.Config;

import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;


@Configuration
public class SOAppInitializer implements ServletContextInitializer {


            @Override
            public void onStartup(ServletContext servletContext) throws ServletException {
                System.err.println("------------------------------------");
            }





  /*  @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        Class [] arr={AppConfig.class};
        return arr;
    }

    @Override
    protected String[] getServletMappings() {
        String[]arr={"/carserviceorganizer.com/*"};

        return arr;
    }

    @Override
    protected String getServletName() {
        return "appservlet";
    }*/
}
