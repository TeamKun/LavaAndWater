package net.kunmc.lab.lavaandwater.world.lavaRain;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class RainingTask extends BukkitRunnable {

    /** 実行中フラグ */
    public static boolean isRunning;

    @Override
    public void run() {

        if (!isRunning) {
            return;
        }

        TargetPlayers targetPlayers = new TargetPlayers();

        // パーティクル処理
        targetPlayers.rainParticle();
        // プレイヤーを燃やす
        targetPlayers.burnPlayer();
        // 周囲のブロックを燃やす
        targetPlayers.burnNearBlocks();
    }
}
