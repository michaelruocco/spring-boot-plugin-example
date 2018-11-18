package uk.co.mruoc.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static java.util.Arrays.asList;

@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class IdentityRequestAttributes {

    private final IdentityAlias alias;

}
