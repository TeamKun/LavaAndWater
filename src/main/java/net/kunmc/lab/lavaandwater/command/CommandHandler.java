package net.kunmc.lab.lavaandwater.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

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
                commandSender.sendMessage("水面上昇開始");
                break;
            case PAUSE:
                commandSender.sendMessage("水面上昇停止");
                break;
        }
    }

    /**
     * 溶岩雨コマンド処理
     * */
    private static void executeLavaRain() {
        switch (secondSubCommand) {
            case RUN:
                commandSender.sendMessage("溶岩雨開始");
                break;
            case PAUSE:
                commandSender.sendMessage("溶岩雨停止");
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
                commandSender.sendMessage("溶岩雨処理スパン変更");
                break;
            case WATER_RISING_SPAN:
                commandSender.sendMessage("水面上昇処理スパン変更");
                break;
        }
    }
}
