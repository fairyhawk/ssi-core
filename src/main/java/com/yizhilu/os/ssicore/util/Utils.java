package com.yizhilu.os.ssicore.util;

import java.util.UUID;

public class Utils {
    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
