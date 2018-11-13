package uk.co.mruoc.app;

import org.pf4j.PluginManager;
import org.pf4j.spring.SpringPluginManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import uk.co.mruoc.api.AliasLoader;

import java.util.List;

@Configuration
public class PluginConfiguration {

    @Bean
    public PluginManager pluginManager() {
        return new SpringPluginManager();
    }

    @Bean
    @DependsOn("pluginManager")
    public List<AliasLoader> aliasLoaders(PluginManager pluginManager) {
        return pluginManager.getExtensions(AliasLoader.class);
    }

}