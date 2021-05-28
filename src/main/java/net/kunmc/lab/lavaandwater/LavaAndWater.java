package net.kunmc.lab.lavaandwater;

import net.kunmc.lab.lavaandwater.command.CommandData;
import net.kunmc.lab.lavaandwater.command.CommandHandler;
import net.kunmc.lab.lavaandwater.command.TabComplete;
import org.bukkit.plugin.java.JavaPlugin;

public final class LavaAndWater extends JavaPlugin {

    /** プラグインオブジェクト */
    public static LavaAndWater plugin;

    @Override
    public void onEnable() {
        plugin = this;

        // TODO コンフィグ

        // コマンド
        getCommand(CommandData.WATER_AND_LAVA.commandName()).setExecutor(new CommandHandler());
        getCommand(CommandData.WATER_AND_LAVA.commandName()).setTabCompleter(new TabComplete());
        // TODO イベントリスナー

        // TODO タスク
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
