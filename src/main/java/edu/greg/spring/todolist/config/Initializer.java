package edu.greg.spring.todolist.config;

import lombok.extern.slf4j.Slf4j;
import org.mortbay.servlet.GzipFilter;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.*;
import java.util.EnumSet;


/**
 * Created by greg on 26.06.15.
 */
@Slf4j
public class Initializer implements WebApplicationInitializer {

    private static final String DISPATCHER_SERVLET_NAME = "dispatcher";
    private static final String DISPATCHER_SERVLET_MAPPING = "/";

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        log.info("Web application configuration");
        log.debug("Configuring Spring root application context");
        // Create the root appcontext
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(WebAppConfig.class);
        //Register profiles
        ctx.register(DevDBProfileConfig.class);
        ctx.register(ProdDBProfileConfig.class);
        ctx.register(DbManagerProfileConfig.class);

        ctx.register(RestHandlerExceptionResolverConfig.class);
        ctx.register(WebSecurityConfig.class);
//        Set Profile
        ctx.getEnvironment().setActiveProfiles("dbManager");

        // Manage the lifecycle of the root appcontext
        servletContext.addListener(new ContextLoaderListener(ctx));
        servletContext.setInitParameter("defaultHtmlEscape", "true");

        ctx.setServletContext(servletContext);

        ServletRegistration.Dynamic servlet = servletContext.addServlet(DISPATCHER_SERVLET_NAME, new DispatcherServlet(ctx));

        servlet.addMapping(DISPATCHER_SERVLET_MAPPING);
        servlet.setLoadOnStartup(1);

        FilterRegistration.Dynamic filter = servletContext.addFilter("openEntityManagerInViewFilter", OpenEntityManagerInViewFilter.class);
        filter.setInitParameter("singleSession", "true");
        filter.addMappingForServletNames(null, true, "dispatcher");

        log.debug("Registering GZip Filter");
        EnumSet<DispatcherType> dispatcherTypes = EnumSet
                .of(DispatcherType.REQUEST);
        FilterRegistration.Dynamic characterEncodingFilter = servletContext
                .addFilter("GzipFilter", new GzipFilter());
        characterEncodingFilter
                .setInitParameter(
                        "mimeTypes",
                        "text/html,text/plain,text/xml,application/xhtml+xml,text/css,application/javascript,application/json,application/x-javascript,image/svg+xml");
        characterEncodingFilter.addMappingForUrlPatterns(dispatcherTypes, true,
                "/*");

        log.debug("Web application fully configured");
    }

}
