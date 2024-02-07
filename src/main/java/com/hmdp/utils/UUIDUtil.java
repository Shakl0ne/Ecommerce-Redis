package com.hmdp.utils;

import java.util.UUID;

public class UUIDUtil {

    public static synchronized String getUUID() {
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        return str.replace("-", "");
    }

}
