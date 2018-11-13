package uk.co.mruoc;

import lombok.extern.slf4j.Slf4j;
import org.pf4j.Plugin;
import org.pf4j.PluginWrapper;

@Slf4j
public class BidvAliasLoaderPlugin extends Plugin {

    public BidvAliasLoaderPlugin(PluginWrapper wrapper) {
        super(wrapper);
    }

    @Override
    public void start() {
        log.info("BidvAliasLoaderPlugin.start()");
    }

    @Override
    public void stop() {
        log.info("BidvAliasLoaderPlugin.stop()");
    }

}
