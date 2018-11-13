package uk.co.mruoc;

import lombok.extern.slf4j.Slf4j;
import org.pf4j.Extension;
import uk.co.mruoc.api.Alias;
import uk.co.mruoc.api.AliasLoader;
import uk.co.mruoc.api.AliasLoaderException;
import uk.co.mruoc.api.ChannelIdProvider;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static java.util.Collections.singleton;

@Slf4j
@Extension
public class BukCustomerIdLoader implements AliasLoader {

    private final ChannelIdProvider channelIdProvider = new BidvChannelIdProvider();
    private final Map<String, String> mappings = buildMappings();

    @Override
    public String getChannelId() {
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

    private Alias toBukCustomerId(final Alias ukcCardholderId) {
        final String bukCustomerId = toBukCustomerId(ukcCardholderId.getValue());
        return new Alias("BUK_CUSTOMER_ID", bukCustomerId);
    }

    private String toBukCustomerId(final String ukcCardholderId) {
        if (mappings.containsKey(ukcCardholderId)) {
            return mappings.get(ukcCardholderId);
        }
        final String message = String.format("no BUK_CUSTOMER_ID found for UKC_CARDHOLDER_ID %s", ukcCardholderId);
        log.info(message);
        throw new AliasLoaderException(message);
    }

    private static Map<String, String> buildMappings() {
        Map<String, String> mappings = new HashMap<>();
        mappings.put("12345678", "1111111111");
        mappings.put("87654321", "2222222222");
        mappings.put("33333333", "8888888888");
        return Collections.unmodifiableMap(mappings);
    }

}
