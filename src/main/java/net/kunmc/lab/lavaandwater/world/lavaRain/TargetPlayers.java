package net.kunmc.lab.lavaandwater.world.lavaRain;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TargetPlayers {
    private List<TargetPlayer> targetPlayers;

    TargetPlayers() {
        List<TargetPlayer> result = new ArrayList<>();
        List<Player> players = (List<Player>) Bukkit.getOnlinePlayers();

        players.forEach(player -> {
            result.add(new TargetPlayer(player));
        });

        this.targetPlayers = result;
    }

    /**
     * パーティクルを降らせる
     * */
    void rainParticle() {
        targetPlayers.forEach(targetPlayer -> {
            targetPlayer.rainParticle();
        });
    }

    /**
     * プレイヤーを燃やす
     * */
    void burnPlayer() {
        targetPlayers.forEach(targetPlayer -> {
            targetPlayer.burnPlayer();
        });
    }

    /**
     * 周囲の可燃性ブロックを燃やす
     * */
    void burnNearBlocks() {
        targetPlayers.forEach(targetPlayer -> {
            targetPlayer.burnNearBlocks();
        });
    }
}
