package dev.wacho.basic.utils;

import dev.wacho.basic.Basic;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Animation {

    public static String Header, tabfooter;

    public static void init() {
        FileConfig tabfile = Basic.getInst().getTabFile();

        // TabList Animation

        // Header
        List<String> Headers = tabfile.getStringList("HEADER-ANIMATED.HEADER");
        AtomicInteger c = new AtomicInteger();
        if (!Headers.isEmpty()) { // Comprueba si la lista no está vacía
            TaskUtil.runTimerAsync(() -> {
                if (c.get() == Headers.size())
                    c.set(0);
                Header = Headers.get(c.getAndIncrement());
            }, 0L, (long) (tabfile.getDouble("HEADER-ANIMATED.INTERVAL") * 20.0D));
        }

        // Footer
        List<String> TabFooters = tabfile.getStringList("FOOTER-ANIMATED.FOOTER");
        AtomicInteger d = new AtomicInteger();
        if (!TabFooters.isEmpty()) { // Comprueba si la lista no está vacía
            TaskUtil.runTimerAsync(() -> {
                if (d.get() == TabFooters.size())
                    d.set(0);
                tabfooter = TabFooters.get(d.getAndIncrement());
            }, 0L, (long) (tabfile.getDouble("FOOTER-ANIMATED.INTERVAL") * 20.0D));
        }
    }

    public static String getTabListHeader() {
        return Header;
    }

    public static String getTabListFooter() {
        return tabfooter;
    }
}

