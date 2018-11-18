package uk.co.mruoc.plugin.api;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class Alias {

    private String type;
    private String value;

}
