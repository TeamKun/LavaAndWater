package net.kunmc.lab.lavaandwater.world.waterLevelRise;


import net.kunmc.lab.lavaandwater.util.MessageUtil;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;

public class BlockModel {
    Block block;

    public BlockModel(Location location) {
        block = location.getBlock();
    }

    /**
     * ブロックを水に置き換える.
     * */
    public void setWater() {

        // ブロックが空気でない
        if (!(block.getType().equals(Material.AIR)) && !(block.getType().equals(Material.CAVE_AIR))) {
            return;
        }

        // 上空にブロックが存在する
        Location thisLocation = block.getLocation();
        Location highestLocation = RisingTask.world.getHighestBlockAt(thisLocation).getLocation();
        if (!(thisLocation.getBlockY() > highestLocation.getBlockY())) {
            return;
        }

        QueuedExecutor.instance().offer(new BukkitRunnable() {
            public void run() {
                block.setType(Material.WATER);
            }
        });
    }

}
