package uk.co.mruoc.plugin.as3;

import org.springframework.stereotype.Component;

@Component
public class AS3ChannelIdProvider {

    public static String CHANNEL_ID = "AS3";

    public String getChannelId() {
        return CHANNEL_ID;
    }

}
