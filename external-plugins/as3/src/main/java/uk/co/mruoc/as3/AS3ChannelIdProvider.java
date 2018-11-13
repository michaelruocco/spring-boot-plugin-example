package uk.co.mruoc.as3;

import uk.co.mruoc.api.ChannelIdProvider;

public class AS3ChannelIdProvider implements ChannelIdProvider {

    public static String CHANNEL_ID = "AS3";

    public String getChannelId() {
        return CHANNEL_ID;
    }

}
