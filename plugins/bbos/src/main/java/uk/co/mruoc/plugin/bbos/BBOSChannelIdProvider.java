package uk.co.mruoc.plugin.bbos;

import org.springframework.stereotype.Component;

@Component
public class BBOSChannelIdProvider {

    public static String CHANNEL_ID = "BBOS";

    public String getChannelId() {
        return CHANNEL_ID;
    }

}
