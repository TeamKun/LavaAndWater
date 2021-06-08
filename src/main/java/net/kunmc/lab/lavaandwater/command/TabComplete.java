package net.kunmc.lab.lavaandwater.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TabComplete implements TabCompleter {

    private static String[] arguments;
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        arguments = args;

        switch (args.length) {
            case 1:
                return firstArgs();
            case 2:
                return secondArgs();
            case 3:
                return thirdArgs();
            case 4:
                return fourthArgs();
        }
        return new ArrayList<>();
    }

    /**
     * 第1引数の補完
     */
    private List<String> firstArgs() {
        return Stream.of(CommandData.WATER_LEVEL_RISE.commandName(),
                CommandData.LAVA_RAIN.commandName(),
                CommandData.CONFIG.commandName())
                .filter(e -> e.startsWith(arguments[0]))
                .collect(Collectors.toList());
    }

    /**
     * 第2引数の補完
     */
    private List<String> secondArgs() {
        CommandData firstSubCommand = CommandData.getCommand(arguments[0]);

        if (firstSubCommand == null) return new ArrayList<>();
        switch (firstSubCommand) {
            case LAVA_RAIN:
            case WATER_LEVEL_RISE:
                return Stream.of(CommandData.RUN.commandName(),
                        CommandData.PAUSE.commandName())
                        .filter(e -> e.startsWith(arguments[1]))
                        .collect(Collectors.toList());

            case CONFIG:
                return Stream.of(CommandData.SHOW.commandName(),
                        CommandData.SET.commandName())
                        .filter(e -> e.startsWith(arguments[1]))
                        .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    /**
     * 第3引数の補完
     */
    private List<String> thirdArgs() {
        if(!arguments[1].equalsIgnoreCase(CommandData.SET.commandName())) return new ArrayList<>();

        return Stream.of(CommandData.LAVA_RAINY_SPAN.commandName(),
                CommandData.WATER_RISING_SPAN.commandName(),
                CommandData.CENTRAL_PLAYER.commandName(),
                CommandData.EFFECTIVE_RANGE.commandName())
                .filter(e -> e.startsWith(arguments[2]))
                .collect(Collectors.toList());
    }

    /**
     * 第4引数の補完
     */
    private List<String> fourthArgs() {
        if(!arguments[2].equalsIgnoreCase(CommandData.CENTRAL_PLAYER.commandName())) return new ArrayList<>();

        return null;
    }
}
