package net.kunmc.lab.lavaandwater.world;

import net.kunmc.lab.lavaandwater.LavaAndWater;
import net.kunmc.lab.lavaandwater.config.Config;
import net.kunmc.lab.lavaandwater.util.QueuedExecutor;
import net.kunmc.lab.lavaandwater.world.lavaRain.RainingTask;
import net.kunmc.lab.lavaandwater.world.waterLevelRise.RisingTask;
import org.bukkit.scheduler.BukkitTask;

public class TaskManager {

    /** 水面上昇タスク */
    private static BukkitTask risingTask;
    /** 溶岩雨タスク */
    private static BukkitTask rainingTask;

    /** 水面上昇タスクキュー */
    public static QueuedExecutor risingTaskQue;
    /** 溶岩雨タスクキュー */
    public static QueuedExecutor rainingTaskQue;


    /**
     * 水面上昇タスクを開始
     * */
    public static void runRisingTask() {

        // 実行中の処理をキャンセル
        if (risingTask != null) {
            risingTask.cancel();
        }

        risingTask = new RisingTask().runTaskTimerAsynchronously(LavaAndWater.plugin,0, Config.waterRisingSpan().tickValue());
    }

    /**
     * 水面上昇タスクを停止
     * */
    public static void pauseRisingTask() {
        risingTask.cancel();
        risingTaskQue.clear();
    }

    /**
     * 溶岩雨タスクを開始
     * */
    public static void runRainingTask() {

        // 実行中の処理をキャンセル
        if (rainingTask != null) {
            rainingTask.cancel();
        }
        rainingTask = new RainingTask().runTaskTimerAsynchronously(LavaAndWater.plugin, 0, Config.lavaRainySpan().value());
    }

    /**
     * 溶岩雨タスクを停止
     * */
    public static void pauseRainingTask() {
        rainingTask.cancel();
        rainingTaskQue.clear();
    }
}
