package uk.co.mruoc.plugin1;

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

import static java.util.Collections.singletonList;

@Slf4j
public class Plugin1 extends Plugin {

    public Plugin1(PluginWrapper wrapper) {
        super(wrapper);
    }

    @Override
    public void start() {
        log.info("Plugin1.start()");
        // for testing the development mode
        if (RuntimeMode.DEVELOPMENT.equals(wrapper.getRuntimeMode())) {
            log.info("development mode {}", StringUtils.upperCase("Plugin1"));
        }
    }

    @Override
    public void stop() {
        log.info("Plugin1.stop()");
    }

    @Extension
    public static class Plugin1AliasLoader implements AliasLoader {

        @Override
        public boolean supportsLookupAlias(Alias alias) {
            return true;
        }

        @Override
        public List<Alias> loadAliases(Alias lookupAlias) {
            return Arrays.asList(lookupAlias, new Alias("TCV_PARTY_ID", "ABC123"));
        }

    }

    @Extension
    public static class Plugin3AliasLoader implements AliasLoader {

        @Override
        public boolean supportsLookupAlias(Alias alias) {
            return true;
        }

        @Override
        public List<Alias> loadAliases(Alias lookupAlias) {
            return singletonList(new Alias("TCV_PARTY_ID", reverse("ABC123")));
        }

        private static String reverse(String value) {
            return new StringBuilder(value).reverse().toString();
        }

    }

}
