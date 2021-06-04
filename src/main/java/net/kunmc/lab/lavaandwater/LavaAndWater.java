package net.kunmc.lab.lavaandwater;

import net.kunmc.lab.lavaandwater.command.CommandData;
import net.kunmc.lab.lavaandwater.command.CommandHandler;
import net.kunmc.lab.lavaandwater.command.TabComplete;
import net.kunmc.lab.lavaandwater.config.Config;
import net.kunmc.lab.lavaandwater.world.lavaRain.RainingTask;
import net.kunmc.lab.lavaandwater.world.waterLevelRise.RisingTask;
import net.kunmc.lab.lavaandwater.world.waterLevelRise.QueuedExecutor;
import org.bukkit.plugin.java.JavaPlugin;

public final class LavaAndWater extends JavaPlugin {

    /** プラグインオブジェクト */
    public static LavaAndWater plugin;

    @Override
    public void onEnable() {
        plugin = this;

        // コンフィグ
        Config.loadConfig();

        // コマンド
        getCommand(CommandData.WATER_AND_LAVA.commandName()).setExecutor(new CommandHandler());
        getCommand(CommandData.WATER_AND_LAVA.commandName()).setTabCompleter(new TabComplete());

        // イベントリスナー
        getServer().getPluginManager().registerEvents(QueuedExecutor.instance(), this);

        // TODO タスク
        new RisingTask().runTaskTimerAsynchronously(this,0, 400);
        new RainingTask().runTaskTimerAsynchronously(this, 0, 5);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
