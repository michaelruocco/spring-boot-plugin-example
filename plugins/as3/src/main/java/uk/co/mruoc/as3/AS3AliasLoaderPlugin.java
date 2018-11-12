package uk.co.mruoc.as3;

import lombok.extern.slf4j.Slf4j;
import org.pf4j.Extension;
import org.pf4j.Plugin;
import org.pf4j.PluginWrapper;
import uk.co.mruoc.api.Alias;
import uk.co.mruoc.api.AliasLoader;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.singletonList;

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
