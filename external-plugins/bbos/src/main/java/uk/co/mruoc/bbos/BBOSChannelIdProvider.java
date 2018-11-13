package uk.co.mruoc.bbos;

import uk.co.mruoc.api.ChannelIdProvider;

public class BBOSChannelIdProvider implements ChannelIdProvider {

    public static String CHANNEL_ID = "BBOS";

    public String getChannelId() {
        return CHANNEL_ID;
    }

}
