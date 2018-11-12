package uk.co.mruoc.bbos;

import lombok.extern.slf4j.Slf4j;
import org.pf4j.Extension;
import org.pf4j.Plugin;
import org.pf4j.PluginWrapper;
import uk.co.mruoc.api.Alias;
import uk.co.mruoc.api.AliasLoader;

import java.util.Set;

import static java.util.Collections.singleton;

@Slf4j
public class BBOSAliasLoaderPlugin extends Plugin {

    private static final String CHANNEL_ID = "BBOS";

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

    @Extension
    public static class BukCustomerIdLoader implements AliasLoader {

        @Override
        public String getChannelId() {
            return CHANNEL_ID;
        }

        @Override
        public Set<String> getSupportedAliasTypes() {
            return singleton("UKC_CARDHOLDER_ID");
        }

        @Override
        public Set<Alias> loadAliases(final Alias ukcCardholderId) {
            log.info("loading aliases using alias {}", ukcCardholderId);
            final Alias bukCustomerId = toBukCustomerId(ukcCardholderId);
            return singleton(bukCustomerId);
        }

        private static Alias toBukCustomerId(final Alias ukcCardholderId) {
            final String bukCustomerId = toBukCustomerId(ukcCardholderId.getValue());
            return new Alias("BUK_CUSTOMER_ID", bukCustomerId);
        }

        private static String toBukCustomerId(final String ukcCardholderId) {
            return new StringBuilder(ukcCardholderId).reverse().toString();
        }

    }

}
