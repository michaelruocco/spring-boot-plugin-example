package uk.co.mruoc.api;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

import static java.util.Arrays.asList;

@Data
@NoArgsConstructor(force = true)
public class IdentityResponseAttributes {

    private final Collection<IdentityAlias> aliases;

    public IdentityResponseAttributes(IdentityAlias... aliases) {
        this(asList(aliases));
    }

    public IdentityResponseAttributes(Collection<IdentityAlias> aliases) {
        this.aliases = aliases;
    }

}
