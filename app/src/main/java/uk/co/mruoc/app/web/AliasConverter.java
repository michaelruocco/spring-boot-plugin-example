package uk.co.mruoc.app.web;

import org.springframework.stereotype.Component;
import uk.co.mruoc.api.IdentityAlias;
import uk.co.mruoc.plugin.api.Alias;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class AliasConverter {

    public Collection<Alias> toModelAliases(Collection<IdentityAlias> aliases) {
        return aliases.stream().map(this::toModelAlias).collect(Collectors.toList());
    }

    public Alias toModelAlias(IdentityAlias alias) {
        return Alias.builder()
                .type(alias.getType())
                .value(alias.getValue())
                .build();
    }

    public Collection<IdentityAlias> toApiAlias(Collection<Alias> aliases) {
        return aliases.stream().map(this::toApiAlias).collect(Collectors.toList());
    }

    public IdentityAlias toApiAlias(Alias alias) {
        return new IdentityAlias(alias.getType(), alias.getValue());
    }

}
