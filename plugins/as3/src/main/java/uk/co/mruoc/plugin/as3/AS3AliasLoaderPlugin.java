package uk.co.mruoc.plugin.as3;

import lombok.extern.slf4j.Slf4j;
import org.pf4j.PluginWrapper;
import org.pf4j.spring.SpringPlugin;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

import java.util.Collections;
import java.util.List;

@Slf4j
public class AS3AliasLoaderPlugin extends SpringPlugin {

    public AS3AliasLoaderPlugin(PluginWrapper wrapper) {
        super(wrapper);
    }

    @Override
    public void start() {
        log.info("AS3AliasLoaderPlugin.start()");
    }

    @Override
    public void stop() {
        log.info("AS3AliasLoaderPlugin.stop()");
    }

    @Override
    protected ApplicationContext createApplicationContext() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.setClassLoader(getWrapper().getPluginClassLoader());
        applicationContext.scan("uk.co.mruoc.plugin.as3");
        //log.info("********************************* classloader {}", getWrapper());
        //log.info("********************************* parent classloader {}", getWrapper().getPluginClassLoader().getParent());
        configureProperties(applicationContext);
        applicationContext.refresh();
        return applicationContext;
    }

    private void configureProperties(final GenericApplicationContext context) {
        final List<String> propertyPaths = Collections.singletonList("test.properties");
        propertyPaths.forEach(propertyPath -> context.addBeanFactoryPostProcessor(buildPropertySourcesPlaceholderConfigurer(propertyPath)));
    }

    private BeanFactoryPostProcessor buildPropertySourcesPlaceholderConfigurer(final String propertyPath) {
        final PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        configurer.setLocation(new ClassPathResource(propertyPath, getWrapper().getPluginClassLoader()));
        return configurer;
    }

}
