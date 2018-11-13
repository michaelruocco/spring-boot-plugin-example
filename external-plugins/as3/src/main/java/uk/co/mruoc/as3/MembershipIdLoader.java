package uk.co.mruoc.as3;

import lombok.extern.slf4j.Slf4j;
import org.pf4j.Extension;
import uk.co.mruoc.api.Alias;
import uk.co.mruoc.api.AliasLoader;
import uk.co.mruoc.api.ChannelIdProvider;

import java.util.HashSet;
import java.util.Set;

import static java.util.Collections.singleton;
import static java.util.Collections.unmodifiableSet;

@Slf4j
@Extension
public class MembershipIdLoader implements AliasLoader {

    private final ChannelIdProvider channelIdProvider = new AS3ChannelIdProvider();

    @Override
    public String getChannelId() {
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
        log.info("loading aliases using alias {}", bukCustomerId);
        final Alias membershipId = toMembershipId(bukCustomerId);
        return singleton(membershipId);
    }

    private static Alias toMembershipId(final Alias bukCustomerId) {
        final String membershipId = toMembershipId(bukCustomerId.getValue());
        return new Alias("MEMBERSHIP_ID", membershipId);
    }

    private static String toMembershipId(final String bukCustomerId) {
        return new StringBuilder(bukCustomerId).reverse().toString();
    }

}
