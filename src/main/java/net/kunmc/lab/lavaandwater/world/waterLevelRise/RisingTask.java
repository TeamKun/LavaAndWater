package net.kunmc.lab.lavaandwater.world.waterLevelRise;

import net.kunmc.lab.lavaandwater.config.Config;
import net.kunmc.lab.lavaandwater.config.WaterLevel;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitRunnable;

public class RisingTask extends BukkitRunnable {

    /** 処理対象ワールド */
    public static World world;

    /** 実行中フラグ */
    private static boolean isRunning;

    @Override
    public void run() {

        if (!isRunning || world == null) {
            return;
        }

        Config.centralPlayer().effectiveBlocks(Config.waterLevel()).forEach(blockModel -> {
            blockModel.setWater();
        });

        Config.waterLevel().rise();

        // 限界まで上昇仕切ったらタスクを終了する
        if (Config.waterLevel().isReachedHeightLimit()) {
            cancel();
        }
    }

    /**
     * 水面上昇を開始する.
     * */
    public static void start(World w) {
        world = w;
        isRunning = true;
    }

    /**
     * 水面上昇を一時停止する.
     * */
    public static void pause() {
        isRunning = false;
    }

    /**
     * 実行中か
     * */
    public static boolean isRunning() {
        return isRunning;
    }
}
