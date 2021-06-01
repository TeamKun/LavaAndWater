package net.kunmc.lab.lavaandwater.world.waterLevelRise.chunk;

import net.kunmc.lab.lavaandwater.world.waterLevelRise.RisingTask;

import java.util.ArrayList;
import java.util.List;

public class ChunkModels {

    /** チャンクリスト */
    List<ChunkModel> chunkList = new ArrayList<>();

    public ChunkModels() {
        ChunkManager.targetChunkSet().forEach(chunkSnapshot -> {
            chunkList.add(new ChunkModel(
                    RisingTask.world.getChunkAt(
                            chunkSnapshot.getX(),
                            chunkSnapshot.getZ())));
        });
    }

    /**
     * ブロックを水に置き換える.
     * */
    public void setWater() {
        chunkList.forEach(chunkModel -> {
            chunkModel.setWater();
        });
    }

}
