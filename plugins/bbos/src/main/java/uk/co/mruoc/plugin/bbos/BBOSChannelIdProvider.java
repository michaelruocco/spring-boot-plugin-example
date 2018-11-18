package uk.co.mruoc.plugin.bbos;

import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Collections.singletonList;

@Component
public class BBOSChannelIdProvider {

    public static String CHANNEL_ID = "BBOS";

    public List<String> getChannelId() {
        return singletonList(CHANNEL_ID);
    }

}
