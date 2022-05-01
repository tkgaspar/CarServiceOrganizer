package com.ownproject.ServiceOrganizer.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;


public class SOAppInitializer implements WebApplicationInitializer {

    public void onStartup(ServletContext container) throws ServletException {

        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(AppConfig.class);
        ctx.setServletContext(container);

        ServletRegistration.Dynamic servlet = (ServletRegistration.Dynamic) new DispatcherServlet(ctx);

        servlet.setLoadOnStartup(1);
        servlet.addMapping("/control");

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
