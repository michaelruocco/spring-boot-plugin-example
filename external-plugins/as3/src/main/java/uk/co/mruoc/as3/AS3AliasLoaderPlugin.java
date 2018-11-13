package uk.co.mruoc.as3;

import lombok.extern.slf4j.Slf4j;
import org.pf4j.Plugin;
import org.pf4j.PluginWrapper;

@Slf4j
public class AS3AliasLoaderPlugin extends Plugin {

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

}
