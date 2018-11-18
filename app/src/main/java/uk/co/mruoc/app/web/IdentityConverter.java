package uk.co.mruoc.app.web;

import org.springframework.stereotype.Component;
import uk.co.mruoc.api.IdentityAlias;
import uk.co.mruoc.api.IdentityData;
import uk.co.mruoc.app.Identity;
import uk.co.mruoc.plugin.api.Alias;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class IdentityConverter {

    private final AliasConverter aliasConverter;

    public IdentityConverter(AliasConverter aliasConverter) {
        this.aliasConverter = aliasConverter;
    }

    public Collection<Identity> toModelIdentity(Collection<IdentityData> data) {
        return data.stream().map(this::toModelIdentity).collect(Collectors.toList());
    }

    public Identity toModelIdentity(IdentityData data) {
        final Collection<Alias> aliases = aliasConverter.toModelAliases(data.getAliases());
        return Identity.builder()
                .id(data.getId())
                .aliases(aliases)
                .build();
    }

    public Collection<IdentityData> toIdentityDataList(Collection<Identity> identities) {
        return identities.stream().map(this::toIdentityData).collect(Collectors.toList());
    }

    public IdentityData toIdentityData(Identity identity) {
        final Collection<IdentityAlias> aliases = aliasConverter.toApiAlias(identity.getAliases());
        return new IdentityData(identity.getId(), aliases);
    }

}
