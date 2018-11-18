package uk.co.mruoc.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class IdentityAlias {

    private final String type;
    private final String value;

}
