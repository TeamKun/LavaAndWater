package net.kunmc.lab.lavaandwater.config;

import org.bukkit.entity.Player;

public class CentralPlayer {

    /** プレイヤー */
    private Player player;

    CentralPlayer(Player player) {
        this.player = player;
    }

    /**
     * プレイヤー名を取得する
     * */
    public String playerName() {
        return this.player.getName();
    }


}
