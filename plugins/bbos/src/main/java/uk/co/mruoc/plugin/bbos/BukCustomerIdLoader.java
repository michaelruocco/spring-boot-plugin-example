package uk.co.mruoc.plugin.bbos;

import lombok.extern.slf4j.Slf4j;
import org.pf4j.Extension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.co.mruoc.plugin.api.Alias;
import uk.co.mruoc.plugin.api.AliasLoader;

import java.util.List;
import java.util.Set;

import static java.util.Collections.singleton;

@Slf4j
@Extension
@Component
public class BukCustomerIdLoader implements AliasLoader {

    @Autowired
    private BBOSChannelIdProvider channelIdProvider;

    @Override
    public List<String> getChannelIds() {
        return channelIdProvider.getChannelId();
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
