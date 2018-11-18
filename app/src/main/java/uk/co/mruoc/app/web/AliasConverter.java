package uk.co.mruoc.app.web;

import org.springframework.stereotype.Component;
import uk.co.mruoc.api.IdentityAlias;
import uk.co.mruoc.plugin.api.Alias;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class AliasConverter {

    public Alias toModelAlias(IdentityAlias alias) {
        return Alias.builder()
                .type(alias.getType())
                .value(alias.getValue())
                .build();
    }

    public Set<IdentityAlias> toApiAlias(Set<Alias> aliases) {
        return aliases.stream().map(this::toApiAlias).collect(Collectors.toSet());
    }

    public IdentityAlias toApiAlias(Alias alias) {
        return new IdentityAlias(alias.getType(), alias.getValue());
    }

}
