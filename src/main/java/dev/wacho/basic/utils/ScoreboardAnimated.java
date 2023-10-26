package dev.wacho.basic.utils;

import dev.wacho.basic.Basic;
import lombok.Getter;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ScoreboardAnimated {

    @Getter
    private static String title;
    @Getter
    private static String footer;

    public static void init() {
        List<String> titles = Basic.getInst().getScoreboardFile().getStringList("TITLE-ANIMATED.TITLE");
        List<String> footers = Basic.getInst().getScoreboardFile().getStringList("FOOTER-ANIMATED.FOOTER");

        title = titles.get(0);
        footer = footers.get(0);

        if (Basic.getInst().getScoreboardFile().getBoolean("TITLE-ANIMATED.ENABLED")) {
            AtomicInteger atomicInteger = new AtomicInteger();

            TaskUtil.runTimerAsync(() -> {
                if (atomicInteger.get() == titles.size()) atomicInteger.set(0);

                title = titles.get(atomicInteger.getAndIncrement());

            }, 0L, (long) (Basic.getInst().getScoreboardFile().getDouble("TITLE-ANIMATED.INTERVAL") * 20L));
        }

        if (Basic.getInst().getScoreboardFile().getBoolean("FOOTER-ANIMATED.ENABLED")) {
            AtomicInteger atomicInteger = new AtomicInteger();

            TaskUtil.runTimerAsync(() -> {
                if (atomicInteger.get() == footers.size()) atomicInteger.set(0);

                footer = footers.get(atomicInteger.getAndIncrement());

            }, 0L, (long) (Basic.getInst().getScoreboardFile().getDouble("FOOTER-ANIMATED.INTERVAL") * 20L));
        }
    }
}