package net.kunmc.lab.lavaandwater;

import net.kunmc.lab.lavaandwater.command.CommandData;
import net.kunmc.lab.lavaandwater.command.CommandHandler;
import net.kunmc.lab.lavaandwater.command.TabComplete;
import net.kunmc.lab.lavaandwater.config.Config;
import net.kunmc.lab.lavaandwater.world.TaskManager;
import net.kunmc.lab.lavaandwater.util.QueuedExecutor;
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
        TaskManager.rainingTaskQue = new QueuedExecutor();
        TaskManager.risingTaskQue = new QueuedExecutor();
        getServer().getPluginManager().registerEvents(TaskManager.rainingTaskQue, this);
        getServer().getPluginManager().registerEvents(TaskManager.risingTaskQue, this);

        // タスク
        TaskManager.runRisingTask();
        TaskManager.runRainingTask();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
