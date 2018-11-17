package uk.co.mruoc.plugin.bbos;

import lombok.extern.slf4j.Slf4j;
import org.pf4j.PluginWrapper;
import org.pf4j.spring.SpringPlugin;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
public class BBOSAliasLoaderPlugin extends SpringPlugin {

    public BBOSAliasLoaderPlugin(PluginWrapper wrapper) {
        super(wrapper);
    }

    @Override
    public void start() {
        log.info("BBOSAliasLoaderPlugin.start()");
    }

    @Override
    public void stop() {
        log.info("BBOSAliasLoaderPlugin.stop()");
    }

    @Override
    protected ApplicationContext createApplicationContext() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.setClassLoader(getWrapper().getPluginClassLoader());
        applicationContext.scan("uk.co.mruoc.plugin.bbos");
        applicationContext.refresh();
        return applicationContext;
    }

}
