package uk.co.mruoc.plugin.as3;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class OdsClientConfig {

    private final String username;
    private final String password;
    private final String url;

}
