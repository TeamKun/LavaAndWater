package net.kunmc.lab.lavaandwater.command;

import net.kunmc.lab.lavaandwater.config.Config;
import net.kunmc.lab.lavaandwater.util.DecorationConst;
import net.kunmc.lab.lavaandwater.util.MessageUtil;
import net.kunmc.lab.lavaandwater.world.lavaRain.RainingTask;
import net.kunmc.lab.lavaandwater.world.waterLevelRise.RisingTask;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandHandler implements CommandExecutor {

    /** コマンド送信者 */
    static CommandSender commandSender;
    /** 引数群 */
    static String[] arguments;
    /** メインコマンド */
    static CommandData mainCommand;
    /** 第一サブコマンド */
    static CommandData firstSubCommand;
    /** 第二サブコマンド */
    static CommandData secondSubCommand;
    /** 第三サブコマンド */
    static CommandData thirdSubCommand;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        commandSender = sender;
        arguments = args;
        mainCommand = CommandData.getCommand(command.getName());

        // 引数が不足している
        if (!CommandError.isEnoughArgument(2)) {
            commandSender.sendMessage("1");
            return false;
        }

        // 入力されたサブコマンドが存在しない
        if (!CommandError.existSubCommand(0) || !CommandError.existSubCommand(1)) {
            commandSender.sendMessage("2");
            return false;
        }

        firstSubCommand = CommandData.getCommand(args[0]);
        secondSubCommand = CommandData.getCommand(args[1]);

        // コマンドの組み合わせが不正
        if (!CommandError.canCombine(firstSubCommand, secondSubCommand)) {
            commandSender.sendMessage("3");
            return false;
        }

        switch (firstSubCommand) {
            case WATER_LEVEL_RISE:
                executeWaterLevelRise();
                break;
            case LAVA_RAIN:
                executeLavaRain();
                break;
            case CONFIG:
                executeConfig();
                break;
        }

        return true;
    }

    /**
     * 水面上昇コマンド処理
     * */
    private static void executeWaterLevelRise() {
        switch (secondSubCommand) {
            case RUN:
                MessageUtil.sendAll(DecorationConst.GREEN + "水面上昇開始");
                Player p = (Player) commandSender;
                RisingTask.start(p.getWorld());
                break;
            case PAUSE:
                MessageUtil.sendAll(DecorationConst.GREEN + "水面上昇停止");
                RisingTask.pause();
                break;
        }
    }

    /**
     * 溶岩雨コマンド処理
     * */
    private static void executeLavaRain() {
        switch (secondSubCommand) {
            case RUN:
                if (RainingTask.isRunning) {
                    MessageUtil.sendAll(DecorationConst.RED + "すでに実行中です");
                    return;
                }
                MessageUtil.sendAll(DecorationConst.GREEN + "溶岩雨開始");
                RainingTask.isRunning = true;
                break;
            case PAUSE:
                if (!RainingTask.isRunning) {
                    MessageUtil.sendAll(DecorationConst.RED + "実行中ではありません");
                    return;
                }
                MessageUtil.sendAll(DecorationConst.GREEN + "溶岩雨停止");
                RainingTask.isRunning = false;
                break;
        }
    }

    /**
     * コンフィグコマンド処理
     * */
    private static void executeConfig() {
        switch (secondSubCommand) {
            case SHOW:
                commandSender.sendMessage("コンフィグ表示");
                break;
            case SET:
                executeConfigSet();
                break;
        }
    }

    /**
     * コンフィグセットコマンド処理
     * */
    private static void executeConfigSet() {
        // 引数が不足している
        if (!CommandError.isEnoughArgument(3)) {
            return;
        }

        // 入力されたサブコマンドが存在しない
        if (!CommandError.existSubCommand(2)) {
            return;
        }

        thirdSubCommand = CommandData.getCommand(arguments[2]);

        switch (thirdSubCommand) {
            case LAVA_RAINY_SPAN:
                Config.setLavaRainySpan(commandSender, arguments[3]);
                break;
            case WATER_RISING_SPAN:
                Config.setWaterRisingSpan(commandSender, arguments[3]);
                break;
            case CENTRAL_PLAYER:
                Config.setCentralPlayer(commandSender, arguments[3]);
                return;
            case EFFECTIVE_RANGE:
                Config.setEffectiveRange(commandSender, arguments[3]);
                return;
        }
    }
}
