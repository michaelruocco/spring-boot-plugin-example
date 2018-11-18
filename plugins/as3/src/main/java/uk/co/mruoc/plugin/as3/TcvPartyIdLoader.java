package uk.co.mruoc.plugin.as3;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
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
public class TcvPartyIdLoader implements AliasLoader {

    @Autowired
    private AS3ChannelIdProvider channelIdProvider;

    @Autowired
    private TcvClientConfig tcvConfig;

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
        logTcvConfig();
        log.info("loading aliases using alias {}", ukcCardholderId);
        final Alias tcvPartyId = toTcvPartyId(ukcCardholderId);
        return singleton(tcvPartyId);
    }

    private static Alias toTcvPartyId(final Alias ukcCardholderId) {
        final String tcvPartyId = toTcvPartyId(ukcCardholderId.getValue());
        return Alias.builder()
                .type("TCV_PARTY_ID")
                .value(tcvPartyId)
                .build();
    }

    private static String toTcvPartyId(final String ukcCardholderId) {
        if (ukcCardholderId.length() > 6) {
            return ukcCardholderId.substring(ukcCardholderId.length() - 6, ukcCardholderId.length());
        }
        return StringUtils.rightPad(ukcCardholderId, 6, '9');
    }

    private void logTcvConfig() {
        log.info("tcv username {}", tcvConfig.getUsername());
        log.info("tcv password {}", tcvConfig.getPassword());
        log.info("tcv url {}", tcvConfig.getUrl());
    }

}
