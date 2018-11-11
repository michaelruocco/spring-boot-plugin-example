package uk.co.mruoc.app;

import lombok.extern.slf4j.Slf4j;
import org.pf4j.spring.SpringPluginManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import uk.co.mruoc.api.AliasLoader;

import java.util.List;

@Slf4j
public class PluginApplicationRunner implements ApplicationRunner {

    @Autowired
    private SpringPluginManager pluginManager;

    @Override
    public void run(ApplicationArguments args) {
        log.info("attempting to find plugins implementing {}", AliasLoader.class);
        List<AliasLoader> plugins = pluginManager.getExtensions(AliasLoader.class);
        if (plugins.isEmpty()) {
            String message = String.format("no plugins found implementing %s at least one plugin must be configured", AliasLoader.class);
            throw new IllegalStateException(message);
        }
        log.info("found {} plugins implementing {}", plugins.size(), AliasLoader.class);
        plugins.forEach(c -> log.info(c.getClass().getName()));
    }

}
