package uk.co.mruoc.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.co.mruoc.app.Identity;
import uk.co.mruoc.plugin.api.Alias;

import java.util.Collection;
import java.util.UUID;

@Component
public class IdentityService {

    private AliasLoaderService aliasLoaderService;

    @Autowired
    public IdentityService(AliasLoaderService aliasLoaderService) {
        this.aliasLoaderService = aliasLoaderService;
    }

    public Identity create(String channelId, Identity identity) {
        final Collection<Alias> loadedAliases = aliasLoaderService.loadAliases(channelId, identity.getAliases());
        final UUID id = extractId(identity);
        return Identity.builder()
                .id(id)
                .aliases(loadedAliases)
                .build();
    }

    private static UUID extractId(Identity identity) {
        if (identity.hasId()) {
            return identity.getId();
        }
        return UUID.randomUUID();
    }

}
