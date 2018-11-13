package uk.co.mruoc;

import uk.co.mruoc.api.ChannelIdProvider;

public class BidvChannelIdProvider implements ChannelIdProvider {

    public static String CHANNEL_ID = "BIDV_DEMO";

    public String getChannelId() {
        return CHANNEL_ID;
    }

}
