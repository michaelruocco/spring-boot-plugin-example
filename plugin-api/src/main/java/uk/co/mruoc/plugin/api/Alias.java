package uk.co.mruoc.plugin.api;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Alias {

    private String type;
    private String value;

}
