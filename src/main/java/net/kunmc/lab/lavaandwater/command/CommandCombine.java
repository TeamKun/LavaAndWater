package net.kunmc.lab.lavaandwater.command;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CommandCombine {
    private static CommandCombine INSTANCE = new CommandCombine();
    private Map<CommandData, Set<CommandData>> allowed;

    private CommandCombine() {
        // 許容されるコマンドの組み合わせをセット
        allowed = new HashMap<>();

        // 第一引数
        allowed.put(CommandData.WATER_AND_LAVA, EnumSet.of(CommandData.LAVA_RAIN, CommandData.LAVA_RAIN, CommandData.CONFIG));

        // 第二引数
        allowed.put(CommandData.WATER_LEVEL_RISE, EnumSet.of(CommandData.RUN, CommandData.PAUSE));
        allowed.put(CommandData.LAVA_RAIN, EnumSet.of(CommandData.RUN, CommandData.PAUSE));
        allowed.put(CommandData.CONFIG, EnumSet.of(CommandData.SHOW, CommandData.SET));

        // 第三引数
        allowed.put(CommandData.SET, EnumSet.of(CommandData.WATER_RISING_SPAN, CommandData.LAVA_RAINY_SPAN, CommandData.CENTRAL_PLAYER, CommandData.EFFECTIVE_RANGE));
    }

    /**
     * インスタンスを取得
     * */
    public static CommandCombine instance() {
        return INSTANCE;
    }

    /**
     * コマンドの組み合わせが許容されているか判定する.
     * */
    boolean canCombine(CommandData from, CommandData to) {
        Set<CommandData> allowedCommands = allowed.get(from);
        if (!allowedCommands.contains(to)) {
            return false;
        }
        return true;
    }
}
