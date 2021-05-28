package net.kunmc.lab.lavaandwater.command;

import net.kunmc.lab.lavaandwater.util.DecorationConst;

public class CommandError {

    /**
     * 引数が足りているか判定する.
     * */
    static boolean isEnoughArgument(int minLimit) {
        if (CommandHandler.arguments.length < minLimit) {
            CommandHandler.commandSender.sendMessage(DecorationConst.RED + "引数が不足しています");
            return false;
        }
        return true;
    }

    /**
     * サブコマンドが存在するか判定する.
     * */
    static boolean existSubCommand(int argumentIndex) {
        if (CommandData.getCommand(CommandHandler.arguments[argumentIndex]) == null) {
            CommandHandler.commandSender.sendMessage(DecorationConst.RED + "引数が不正です");
            return false;
        }
        return true;
    }

    /**
     * コマンドの組み合わせが正常か判定する.
     * */
    static boolean canCombine(CommandData from, CommandData to) {
        if (!CommandCombine.instance().canCombine(from, to)) {
            CommandHandler.commandSender.sendMessage(DecorationConst.RED + "引数が不正です");

            return false;
        }
        return true;
    }
}
