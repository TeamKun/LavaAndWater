package net.kunmc.lab.lavaandwater.world.waterLevelRise;

import net.kunmc.lab.lavaandwater.util.MessageUtil;
import net.kunmc.lab.lavaandwater.world.waterLevelRise.chunk.ChunkManager;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.stream.IntStream;

public class RisingTask extends BukkitRunnable {

    /** 処理対象ワールド */
    public static World world;

    /** 現在の水面の高さ */
    public static WaterLevel currentWaterLevel = WaterLevel.instance();

    /** 実行中フラグ */
    private static boolean isRunning;

    /** 後で消す player*/
    public static Player player;

    @Override
    public void run() {

        if (!isRunning || world == null) {
            MessageUtil.sendAll("return1");
            return;
        }

        if (ChunkManager.targetChunkSet().size() < 1) {
            MessageUtil.sendAll("return2");
            return;
        }

          MessageUtil.sendAll("runTask");
//        ChunkModels chunks = new ChunkModels();
//        chunks.setWater();


        Location center = player.getLocation();
        double centerX = center.getX();
        double centerZ = center.getZ();
        IntStream.range(0, 320).forEach(i -> IntStream.range(0, 320).forEach(j -> {

            Location l = new Vector(centerX + i, currentWaterLevel.currentLevel() , centerZ + j).toLocation(world);
            MessageUtil.sendAll(l.toString());
            new BlockModel(l).setWater();

        }));

        currentWaterLevel.rise();


        // 限界まで上昇仕切ったらタスクを終了する
        if (currentWaterLevel.isReachedHeightLimit()) {
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
}
