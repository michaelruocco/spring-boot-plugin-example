package uk.co.mruoc.app;

//import org.pf4j.PluginManager;
import lombok.extern.slf4j.Slf4j;
import org.pf4j.spring.SpringPluginManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import uk.co.mruoc.api.AliasLoader;

import java.util.List;

@SpringBootApplication
@Slf4j
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public ApplicationRunner run() {

        return new ApplicationRunner() {

            @Autowired
            private SpringPluginManager pluginManager;

            @Override
            public void run(ApplicationArguments args) {
                List<AliasLoader> plugins = pluginManager.getExtensions(AliasLoader.class);
                log.info(String.format("Number of plugins found: %d", plugins.size()));
                plugins.forEach(c -> log.info(c.getClass().getName()));
            }

        };

    }
    /*public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(PluginConfiguration.class);

        AliasLoaders loaders = applicationContext.getBean(AliasLoaders.class);
        loaders.print();

        SpringApplication.
        //PluginManager pluginManager = applicationContext.getBean(PluginManager.class);
        //pluginManager.stopPlugins();
    }*/

}