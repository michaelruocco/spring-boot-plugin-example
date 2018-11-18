package uk.co.mruoc.plugin.api;

import org.pf4j.PluginWrapper;
import org.pf4j.spring.SpringPlugin;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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
        context.refresh();
        return context;
    }

    protected abstract String getName();

    protected abstract List<String> getComponentScanPackages();

    private ClassLoader getClassLoader() {
        return getWrapper().getPluginClassLoader();
    }

    private void configureComponentScanPackages(final AnnotationConfigApplicationContext context) {
        final List<String> scanPackages = getComponentScanPackages();
        scanPackages.forEach(context::scan);
    }

}
