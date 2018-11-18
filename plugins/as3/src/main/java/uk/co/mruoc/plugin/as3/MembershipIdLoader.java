package uk.co.mruoc.plugin.as3;

import lombok.extern.slf4j.Slf4j;
import org.pf4j.Extension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.co.mruoc.plugin.api.Alias;
import uk.co.mruoc.plugin.api.AliasLoader;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Collections.singleton;
import static java.util.Collections.unmodifiableSet;

@Slf4j
@Extension
@Component
public class MembershipIdLoader implements AliasLoader {

    @Autowired
    private AS3ChannelIdProvider channelIdProvider;

    @Autowired
    private OdsClientConfig odsConfig;

    @Override
    public List<String> getChannelIds() {
        return channelIdProvider.getChannelId();
    }

    @Override
    public Set<String> getSupportedAliasTypes() {
        Set<String> types = new HashSet<>();
        types.add("BUK_CUSTOMER_ID");
        types.add("UKC_CARDHOLDER_ID");
        return unmodifiableSet(types);
    }

    @Override
    public Set<Alias> loadAliases(final Alias bukCustomerId) {
        logOdsConfig();
        log.info("loading aliases using alias {}", bukCustomerId);
        final Alias membershipId = toMembershipId(bukCustomerId);
        return singleton(membershipId);
    }

    private static Alias toMembershipId(final Alias bukCustomerId) {
        final String membershipId = toMembershipId(bukCustomerId.getValue());
        return Alias.builder()
                .type("MEMBERSHIP_ID")
                .value(membershipId)
                .build();
    }

    private static String toMembershipId(final String bukCustomerId) {
        return new StringBuilder(bukCustomerId).reverse().toString();
    }

    private void logOdsConfig() {
        log.info("ods username {}", odsConfig.getUsername());
        log.info("ods password {}", odsConfig.getPassword());
        log.info("ods url {}", odsConfig.getUrl());
    }

}
