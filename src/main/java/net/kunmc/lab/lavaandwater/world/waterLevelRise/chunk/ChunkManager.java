package net.kunmc.lab.lavaandwater.waterLevelRise.chunk;

import net.kunmc.lab.lavaandwater.util.MessageUtil;
import org.bukkit.ChunkSnapshot;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.event.world.ChunkUnloadEvent;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ChunkManager implements Listener {

    /** 処理対象チャンク */
    private static Set<ChunkSnapshot> targetChunkSet = new HashSet<>();

    @EventHandler(ignoreCancelled = true)
    public void onChunkLoad(ChunkLoadEvent event) {
        targetChunkSet.add(event.getChunk().getChunkSnapshot());
    }

    @EventHandler(ignoreCancelled = true)
    public void onChunkUnload(ChunkUnloadEvent event) {
        targetChunkSet.remove(event.getChunk().getChunkSnapshot());
    }

    /**
     * 対象処理チャンクセットのコピーを取得する
     * */
    public static List<ChunkSnapshot> targetChunkSet() {
        List<ChunkSnapshot> list = new ArrayList<>();

        targetChunkSet.forEach(chunkSnapshot -> {
            list.add(chunkSnapshot);
        });

        return list;
    }
}
