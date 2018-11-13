package uk.co.mruoc.bbos;

import lombok.extern.slf4j.Slf4j;
import org.pf4j.Plugin;
import org.pf4j.PluginWrapper;

@Slf4j
public class BBOSAliasLoaderPlugin extends Plugin {

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

}
