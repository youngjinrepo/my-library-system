package me.my_library_system;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;

public final class TestClocks {
    public static final ZoneId zoneId = ZoneId.of("Asia/Seoul");

    public static Clock at(String localDateTime) {
        return Clock.fixed(
                LocalDateTime.parse(localDateTime).atZone(zoneId).toInstant(),
                zoneId
        );
    }

}
