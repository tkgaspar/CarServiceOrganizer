package com.ownproject.ServiceOrganizer.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

@Configuration
public class SOAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {


    @Override
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
}
