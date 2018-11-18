package uk.co.mruoc.plugin.bidv;

import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Collections.singletonList;

@Component
public class BidvChannelIdProvider {

    public static String CHANNEL_ID = "BIDV";

    public List<String> getChannelId() {
        return singletonList(CHANNEL_ID);
    }

}
