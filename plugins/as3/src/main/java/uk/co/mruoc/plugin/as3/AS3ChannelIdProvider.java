package uk.co.mruoc.plugin.as3;

import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Collections.singletonList;

@Component
public class AS3ChannelIdProvider {

    public static String CHANNEL_ID = "AS3";

    public List<String> getChannelId() {
        return singletonList(CHANNEL_ID);
    }

}
