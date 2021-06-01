package net.kunmc.lab.lavaandwater.world.waterLevelRise.chunk;

import net.kunmc.lab.lavaandwater.world.waterLevelRise.BlockModel;
import net.kunmc.lab.lavaandwater.world.waterLevelRise.RisingTask;
import org.bukkit.Chunk;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ChunkModel {
    List<BlockModel> blockModelList = new ArrayList<>();

    ChunkModel(Chunk chunk) {
        IntStream.range(0, 16).forEach(i -> {
            IntStream.range(0, 16).forEach(j -> {
                blockModelList.add(new BlockModel(
                        chunk.getBlock(i,
                                RisingTask.currentWaterLevel.currentLevel(),
                                j).getLocation()));
            });
        });
    }

    /**
     * ブロックを水に置き換える.
     * */
    void setWater() {
        blockModelList.forEach(blockModel -> {
            blockModel.setWater();
        });
    }
}
