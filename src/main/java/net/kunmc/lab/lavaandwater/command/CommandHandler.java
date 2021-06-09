package net.kunmc.lab.lavaandwater.command;

import net.kunmc.lab.lavaandwater.config.Config;
import net.kunmc.lab.lavaandwater.util.DecorationConst;
import net.kunmc.lab.lavaandwater.util.MessageUtil;
import net.kunmc.lab.lavaandwater.world.TaskManager;
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
            return false;
        }

        // 入力されたサブコマンドが存在しない
        if (!CommandError.existSubCommand(0) || !CommandError.existSubCommand(1)) {
            return false;
        }

        firstSubCommand = CommandData.getCommand(args[0]);
        secondSubCommand = CommandData.getCommand(args[1]);

        // コマンドの組み合わせが不正
        if (!CommandError.canCombine(firstSubCommand, secondSubCommand)) {
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

                if (RisingTask.isRunning()) {
                    commandSender.sendMessage(DecorationConst.RED + "すでに実行中です");
                    return;
                }
                // 中心プレイヤーが設定されていない
                if (Config.centralPlayer() == null) {
                    commandSender.sendMessage(DecorationConst.RED + "中心プレイヤーが設定されていません");
                    return;
                }

                MessageUtil.sendAll(DecorationConst.GREEN + "水面上昇が始まった!");
                Player p = (Player) commandSender;
                TaskManager.runRisingTask();
                RisingTask.start(p.getWorld());
                break;

            case PAUSE:

                if (!RisingTask.isRunning()) {
                    commandSender.sendMessage(DecorationConst.RED + "実行中ではありません");
                    return;
                }
                MessageUtil.sendAll(DecorationConst.GREEN + "水面上昇停止");
                TaskManager.pauseRisingTask();
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
                    commandSender.sendMessage(DecorationConst.RED + "すでに実行中です");
                    return;
                }
                MessageUtil.sendAll(DecorationConst.GREEN + "溶岩雨が降り始める!");
                TaskManager.runRainingTask();
                RainingTask.isRunning = true;
                break;
            case PAUSE:
                if (!RainingTask.isRunning) {
                    commandSender.sendMessage(DecorationConst.RED + "実行中ではありません");
                    return;
                }
                MessageUtil.sendAll(DecorationConst.GREEN + "溶岩雨停止");
                TaskManager.pauseRainingTask();
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
                Config.show(commandSender);
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
        if (!CommandError.isEnoughArgument(4)) {
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

                // 再起動
                if (RainingTask.isRunning) {
                    TaskManager.runRainingTask();
                }
                break;
            case WATER_RISING_SPAN:
                Config.setWaterRisingSpan(commandSender, arguments[3]);

                // 再起動
                if (RisingTask.isRunning()) {
                    TaskManager.runRisingTask();
                }
                break;
            case CENTRAL_PLAYER:
                Config.setCentralPlayer(commandSender, arguments[3]);

                // 再起動
                if (RisingTask.isRunning()) {
                    TaskManager.runRisingTask();
                }
                return;
            case EFFECTIVE_RANGE:
                Config.setEffectiveRange(commandSender, arguments[3]);

                // 再起動
                if (RisingTask.isRunning()) {
                    TaskManager.runRisingTask();
                }
                return;

            case WATER_LEVEL:
                Config.setWaterLevel(commandSender, arguments[3]);

                // 再起動
                if (RisingTask.isRunning()) {
                    TaskManager.runRisingTask();
                }
                return;
        }
    }
}
