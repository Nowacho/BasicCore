package dev.wacho.basic.misc.timer;

import dev.wacho.basic.Basic;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Timer {

    private String name;
    private String prefix;
    private long startMillis;
    private long endMillis;
    private boolean running;

    public Timer(Basic plugin, String name, long startMillis, long endMillis, String prefix) {
        this.name = name;
        this.startMillis = startMillis;
        this.endMillis = endMillis;
        this.prefix = prefix;
        this.running = true;

        TimerTask timerTask = new TimerTask(plugin, this);
        timerTask.run();
    }

    public long getRemaining() {
        return endMillis - System.currentTimeMillis();
    }
}