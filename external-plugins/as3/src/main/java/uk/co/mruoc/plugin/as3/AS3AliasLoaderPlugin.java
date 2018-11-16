package uk.co.mruoc.plugin.as3;

import lombok.extern.slf4j.Slf4j;
import org.pf4j.PluginWrapper;
import org.pf4j.spring.SpringPlugin;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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
        applicationContext.refresh();
        return applicationContext;
    }

}
