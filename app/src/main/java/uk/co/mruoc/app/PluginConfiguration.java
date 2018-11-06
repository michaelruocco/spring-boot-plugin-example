package uk.co.mruoc.app;

import org.pf4j.PluginManager;
import org.pf4j.spring.SpringPluginManager;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import uk.co.mruoc.api.AliasLoader;

import java.util.List;

@Configuration
public class PluginConfiguration implements BeanFactoryAware {

    private BeanFactory beanFactory;

    @Bean
    public PluginManager pluginManager() {
        return new SpringPluginManager();
    }

    @Bean
    @DependsOn("pluginManager")
    public List<AliasLoader> aliasLoaders(PluginManager pluginManager) {
        System.out.println("|||||||||||||||||||||||||||||||||||||||||||| loading plugins ||||||||||||||||||||||||||||||||||||||||||");
        List<AliasLoader> loaders = pluginManager.getExtensions(AliasLoader.class);
        return loaders;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

}