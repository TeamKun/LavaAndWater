package net.kunmc.lab.lavaandwater.world.lavaRain;

import net.kunmc.lab.lavaandwater.util.MessageUtil;
import net.kunmc.lab.lavaandwater.util.RandomUtil;
import net.kunmc.lab.lavaandwater.world.waterLevelRise.QueuedExecutor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerStatisticIncrementEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.Vector;

import java.util.stream.IntStream;

public class TargetPlayer {

    private Player player;

    TargetPlayer(Player player) {
        this.player = player;
    }

    /**
     * パーティクルを降らせる
     * */
    void rainParticle() {
        World world = player.getWorld();
        Location center = player.getLocation();
        double y = center.getBlockY() + 15;
        BoundingBox range = BoundingBox.of(player.getLocation().getBlock()).expand(252);

        IntStream.range((int) range.getMinX(),(int) range.getMaxX()).
                forEach(i -> IntStream.range((int) range.getMinZ(), (int) range.getMaxZ()).forEach(j -> {
            if (RandomUtil.lottery(5)) {
                QueuedExecutor.instance().offer(new BukkitRunnable() {
                    public void run() {
                        world.spawnParticle(Particle.DRIP_LAVA, i, y, j, 1);
                    }
                });
            }
        }));
    }

    /**
     * プレイヤーを燃やす
     * */
    void burnPlayer() {
        // プレイヤーの上空にブロックがあるか判定する
        Location playerLocation = player.getLocation();
        Block block = player.getWorld().getHighestBlockAt(playerLocation);

        if (block.getLocation().getBlockY() < playerLocation.getBlockY()) {
            QueuedExecutor.instance().offer(new BukkitRunnable() {
                public void run() {
                    player.setFireTicks(20);
                }
            });
        }
    }

    /**
     * 周囲の可燃性ブロックを燃やす
     * */
    void burnNearBlocks() {
        World world = player.getWorld();
        BoundingBox range = BoundingBox.of(player.getLocation().getBlock()).expand(50);

        IntStream.range((int) range.getMinX(),(int) range.getMaxX()).
                forEach(i -> IntStream.range((int) range.getMinZ(), (int) range.getMaxZ()).forEach(j -> {

                    Block targetBlock = world.getHighestBlockAt(new Vector(i, 150, j).toLocation(world));

                    if (targetBlock.getType().equals(Material.WATER)) {
                        return;
                    }
                    // 燃える確率を抽選する
                    if (RandomUtil.lottery(0.01)) {
                        QueuedExecutor.instance().offer(new BukkitRunnable() {
                            public void run() {
                                targetBlock.getRelative(BlockFace.UP).setType(Material.FIRE);
                            }
                        });
                    }
                }));
    }
}
