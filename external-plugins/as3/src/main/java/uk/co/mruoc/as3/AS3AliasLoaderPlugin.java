package uk.co.mruoc.as3;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.pf4j.Extension;
import org.pf4j.Plugin;
import org.pf4j.PluginWrapper;
import uk.co.mruoc.api.Alias;
import uk.co.mruoc.api.AliasLoader;

import java.util.Set;

import static java.util.Collections.singleton;

@Slf4j
public class AS3AliasLoaderPlugin extends Plugin {

    private static final String CHANNEL_ID = "AS3";

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
    public static class TcvPartyIdLoader implements AliasLoader {

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
            final Alias tcvPartyId = toTcvPartyId(ukcCardholderId);
            return singleton(tcvPartyId);
        }

        private static Alias toTcvPartyId(final Alias ukcCardholderId) {
            final String tcvPartyId = toTcvPartyId(ukcCardholderId.getValue());
            return new Alias("TCV_PARTY_ID", tcvPartyId);
        }

        private static String toTcvPartyId(final String ukcCardholderId) {
            if (ukcCardholderId.length() > 6) {
                return ukcCardholderId.substring(0, 6);
            }
            return StringUtils.rightPad(ukcCardholderId, 6, '9');
        }

    }

    @Extension
    public static class MembershipIdLoader implements AliasLoader {

        @Override
        public String getChannelId() {
            return CHANNEL_ID;
        }

        @Override
        public Set<String> getSupportedAliasTypes() {
            return singleton("BUK_CUSTOMER_ID");
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

}
