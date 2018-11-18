package uk.co.mruoc.plugin.as3;

import lombok.extern.slf4j.Slf4j;
import org.pf4j.PluginWrapper;
import uk.co.mruoc.plugin.api.AbstractBidvPlugin;

import java.util.List;

import static java.util.Collections.singletonList;

@Slf4j
public class AS3AliasLoaderPlugin extends AbstractBidvPlugin {

    public AS3AliasLoaderPlugin(PluginWrapper wrapper) {
        super(wrapper);
    }

    @Override
    public String getName() {
        return "AS3AliasLoaderPlugin";
    }

    @Override
    public List<String> getComponentScanPackages() {
        return singletonList("uk.co.mruoc.plugin.as3");
    }

}
