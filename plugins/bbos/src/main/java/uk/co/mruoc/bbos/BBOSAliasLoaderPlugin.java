package uk.co.mruoc.plugin2;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.pf4j.Extension;
import org.pf4j.Plugin;
import org.pf4j.PluginWrapper;
import org.pf4j.RuntimeMode;
import uk.co.mruoc.api.Alias;
import uk.co.mruoc.api.AliasLoader;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class Plugin2 extends Plugin {

    public Plugin2(PluginWrapper wrapper) {
        super(wrapper);
    }

    @Override
    public void start() {
        log.info("Plugin2.start()");
        // for testing the development mode
        if (RuntimeMode.DEVELOPMENT.equals(wrapper.getRuntimeMode())) {
            log.info("development mode {}", StringUtils.upperCase("Plugin2"));
        }
    }

    @Override
    public void stop() {
        log.info("Plugin2.stop()");
    }

    @Extension
    public static class Plugin2AliasLoader implements AliasLoader {

        @Override
        public boolean supportsLookupAlias(Alias alias) {
            return true;
        }

        @Override
        public List<Alias> loadAliases(Alias lookupAlias) {
            return Arrays.asList(lookupAlias, new Alias("MEMBERSHIP_ID", "XYZ999"));
        }

    }

}
