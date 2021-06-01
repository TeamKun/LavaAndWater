package net.kunmc.lab.lavaandwater.command;

import java.util.Arrays;

public enum CommandData {
    WATER_AND_LAVA("lavaAndWater"),
    WATER_LEVEL_RISE("waterLevelRise"),
    LAVA_RAIN("lavaRain"),
    RUN("run"),
    PAUSE("pause"),
    CONFIG("config"),
    SHOW("show"),
    SET("set"),
    WATER_RISING_SPAN("waterRisingSpan"),
    LAVA_RAINY_SPAN("lavaRainySpan"),
    CENTRAL_PLAYER("centralPlayer"),
    EFFECTIVE_RANGE("effectiveRange");


    private String commandName;

    CommandData(String commandName) {
        this.commandName = commandName;
    }

    /**
     * コマンド名を取得する.
     * */
    public String commandName() {
        return commandName;
    }

    /**
     * 入力された文字列からコマンドを引き当てる.
     * */
    public static CommandData getCommand(String commandName) {
        return Arrays.stream(CommandData.values())
                .filter(data -> data.commandName().equalsIgnoreCase(commandName))
                .findFirst()
                .orElse(null);
    }
}
