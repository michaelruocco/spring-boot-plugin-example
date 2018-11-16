package uk.co.mruoc.plugin.bidv;

import org.springframework.stereotype.Component;

@Component
public class BidvChannelIdProvider {

    public static String CHANNEL_ID = "BIDV";

    public String getChannelId() {
        return CHANNEL_ID;
    }

}
