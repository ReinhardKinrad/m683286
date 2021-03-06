package com.rdlab.marketplace.config;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebConfigInitializer extends
    AbstractAnnotationConfigDispatcherServletInitializer {

  @Override
  public void onStartup(ServletContext servletContext) throws ServletException {
    AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
    rootContext.register(AppContext.class, WebSecurityConfig.class);
    servletContext.addListener(new ContextLoaderListener(rootContext));

    AnnotationConfigWebApplicationContext servletAppContext = new AnnotationConfigWebApplicationContext();
    servletAppContext.register(WebMVCAppConfig.class);

    DispatcherServlet dispatcherServlet = new DispatcherServlet(servletAppContext);
    dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);

    ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher",
        dispatcherServlet);
    dispatcher.setLoadOnStartup(1);
    dispatcher.addMapping("/");

    FilterRegistration.Dynamic filterRegistration = servletContext.addFilter("encodingFilter",
        new CharacterEncodingFilter());
    filterRegistration.setInitParameter("encoding", "UTF-8");
    filterRegistration.setInitParameter("forceEncoding", "true");
    filterRegistration.addMappingForUrlPatterns(null, false, "/*");
  }

  @Override
  protected Class<?>[] getRootConfigClasses() {
    return new Class[]{WebSecurityConfig.class};
  }

  @Override
  protected Class<?>[] getServletConfigClasses() {
    return new Class[0];
  }

  @Override
  protected String[] getServletMappings() {
    return new String[0];
  }
}
