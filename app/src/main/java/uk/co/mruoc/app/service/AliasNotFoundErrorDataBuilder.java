package uk.co.mruoc.app.service;

import org.springframework.stereotype.Component;
import uk.co.mruoc.api.DefaultErrorData;
import uk.co.mruoc.api.ErrorData;
import uk.co.mruoc.plugin.api.Alias;

import java.util.HashMap;
import java.util.Map;

import static java.util.Collections.unmodifiableMap;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Component
public class AliasNotFoundErrorDataBuilder {

    public ErrorData build(final String channelId, final Alias alias) {
        final String detail = buildDetail(channelId, alias);
        final Map<String, String> meta = buildMeta(channelId, alias);
        return DefaultErrorData.builder()
                .code("ALIAS_NOT_FOUND")
                .title("alias not found")
                .detail(detail)
                .meta(meta)
                .httpStatus(NOT_FOUND)
                .build();
    }

    private String buildDetail(final String channelId, final Alias alias) {
        return String.format("alias not found for channel %s using lookup alias %s %s",
                channelId,
                alias.getType(),
                alias.getValue());
    }

    private static Map<String, String> buildMeta(final String channelId, final Alias alias) {
        final Map<String, String> meta = new HashMap<>();
        meta.put("channelId", channelId);
        meta.put("aliasType", alias.getType());
        meta.put("aliasValue", alias.getValue());
        return unmodifiableMap(meta);
    }

}
