package net.kunmc.lab.lavaandwater.config;

import net.kunmc.lab.lavaandwater.util.MessageUtil;
import net.kunmc.lab.lavaandwater.world.waterLevelRise.BlockModel;
import net.kunmc.lab.lavaandwater.world.waterLevelRise.RisingTask;
import net.kunmc.lab.lavaandwater.world.waterLevelRise.WaterLevel;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class CentralPlayer {

    /** プレイヤー */
    private Player player;

    /** ワールド */
    private World world;

    CentralPlayer(Player player) {
        this.player = player;
        this.world = player.getWorld();
    }

    /**
     * プレイヤー名を取得する
     * */
    public String playerName() {
        return this.player.getName();
    }

    /**
     * 水面上昇の処理範囲を取得する
     * */
    public List<BlockModel> effectiveBlocks(WaterLevel waterLevel) {

        Location center = player.getLocation();
        BoundingBox boundingBox = BoundingBox.of(center.getBlock()).expand(Config.effectiveRange().halfRange());

        MessageUtil.sendAll("長さ" + Config.effectiveRange().halfRange());
        List<BlockModel> result = new ArrayList<>();

        IntStream.range((int) boundingBox.getMinX(),(int) boundingBox.getMaxX()).forEach(i -> IntStream.range((int) boundingBox.getMinZ(), (int) boundingBox.getMaxZ()).forEach(j -> {
            result.add(new BlockModel(
                    new Vector(i, waterLevel.currentLevel(), j)
                            .toLocation(this.world)));
        }));

        return result;
    }
}
