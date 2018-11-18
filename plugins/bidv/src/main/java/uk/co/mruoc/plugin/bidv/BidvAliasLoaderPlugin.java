package uk.co.mruoc.plugin.bidv;

import lombok.extern.slf4j.Slf4j;
import org.pf4j.PluginWrapper;
import uk.co.mruoc.plugin.api.AbstractBidvPlugin;

import java.util.Collections;
import java.util.List;

@Slf4j
public class BidvAliasLoaderPlugin extends AbstractBidvPlugin {

    public BidvAliasLoaderPlugin(PluginWrapper wrapper) {
        super(wrapper);
    }

    @Override
    public String getName() {
        return "BidvAliasLoaderPlugin";
    }

    @Override
    public List<String> getComponentScanPackages() {
        return Collections.singletonList("uk.co.mruoc.plugin.bidv");
    }

}
