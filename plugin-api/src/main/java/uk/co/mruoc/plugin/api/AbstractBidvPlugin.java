package uk.co.mruoc.plugin.api;

import org.pf4j.PluginWrapper;
import org.pf4j.spring.SpringPlugin;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

import java.util.List;

public abstract class AbstractBidvPlugin extends SpringPlugin {

    public AbstractBidvPlugin(PluginWrapper wrapper) {
        super(wrapper);
    }

    @Override
    public void start() {
        final String message = String.format("starting plugin %s", getName());
        log.info(message);
    }

    @Override
    public void stop() {
        final String message = String.format("stopping plugin %s", getName());
        log.info(message);
    }

    @Override
    protected ApplicationContext createApplicationContext() {
        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.setClassLoader(getClassLoader());
        configureComponentScanPackages(context);
        configureProperties(context);
        context.refresh();
        return context;
    }

    public abstract ClassLoader getClassLoader();

    public abstract String getName();

    public abstract List<String> getComponentScanPackages();

    public abstract List<String> getPropertyPaths();

    private void configureComponentScanPackages(final AnnotationConfigApplicationContext context) {
        final List<String> scanPackages = getComponentScanPackages();
        scanPackages.forEach(context::scan);
    }

    private void configureProperties(final GenericApplicationContext context) {
        final List<String> propertyPaths = getPropertyPaths();
        propertyPaths.forEach(propertyPath -> context.addBeanFactoryPostProcessor(buildPropertySourcesPlaceholderConfigurer(propertyPath)));
    }

    private BeanFactoryPostProcessor buildPropertySourcesPlaceholderConfigurer(final String propertyPath) {
        final PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        configurer.setLocation(new ClassPathResource(propertyPath, getClassLoader()));
        return configurer;
    }

}
