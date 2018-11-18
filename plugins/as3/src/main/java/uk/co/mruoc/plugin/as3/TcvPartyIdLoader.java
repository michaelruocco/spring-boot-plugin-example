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
