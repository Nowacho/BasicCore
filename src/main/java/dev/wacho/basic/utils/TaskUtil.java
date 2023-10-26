package dev.wacho.basic.utils;

import dev.wacho.basic.Basic;
import org.bukkit.scheduler.BukkitRunnable;

public class TaskUtil {

    public static void runTaskAsync(Runnable runnable) {
        Basic.getInst().getServer().getScheduler().runTaskAsynchronously(Basic.getInst(), runnable);
    }

    public static void runTaskLater(Runnable runnable, long delay) {
        Basic.getInst().getServer().getScheduler().runTaskLater(Basic.getInst(), runnable, delay);
    }

    public static void runTaskTimer(BukkitRunnable runnable, long delay, long timer) {
        runnable.runTaskTimer(Basic.getInst(), delay, timer);
    }

    public static void runTaskTimer(Runnable runnable, long delay, long timer) {
        Basic.getInst().getServer().getScheduler().runTaskTimer(Basic.getInst(), runnable, delay, timer);
    }

    public static void runTask(Runnable runnable) {
        Basic.getInst().getServer().getScheduler().runTask(Basic.getInst(), runnable);
    }

    public static void runTimerAsync(Runnable runnable, long delay, long timer) {
        try {
            Basic.getInst().getServer().getScheduler().runTaskTimerAsynchronously(Basic.getInst(), runnable, delay, timer);
        } catch (IllegalStateException e) {
            Basic.getInst().getServer().getScheduler().runTaskTimer(Basic.getInst(), runnable, delay, timer);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public static void runTimerAsync(BukkitRunnable runnable, long delay, long timer) {
        Basic.getInst().getServer().getScheduler().runTaskTimerAsynchronously(Basic.getInst(), runnable, delay, timer);
    }

    public interface Callable {

        void call();
    }
}