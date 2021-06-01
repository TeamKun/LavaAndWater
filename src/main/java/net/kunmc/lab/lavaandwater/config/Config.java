package net.kunmc.lab.lavaandwater.config;

import net.kunmc.lab.lavaandwater.LavaAndWater;
import net.kunmc.lab.lavaandwater.util.DecorationConst;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Config {

    /** 中心プレイヤー */
    private static CentralPlayer centralPlayer;

    /** 影響範囲 */
    private static EffectiveRange effectiveRange;

    /** 溶岩雨の処理スパン */
    private static LavaRainySpan lavaRainySpan;

    /** 水面上昇スパン */
    private static WaterRisingSpan waterRisingSpan;

    /**
     * コンフィグをロードする
     * */
    public static void loadConfig() {
        LavaAndWater.plugin.saveDefaultConfig();

        //　コンフィグファイルを取得
        FileConfiguration config = LavaAndWater.plugin.getConfig();

        effectiveRange = new EffectiveRange(config.getInt("effectiveRange"));
        lavaRainySpan = new LavaRainySpan(config.getInt("lavaRainySpan"));
        waterRisingSpan = new WaterRisingSpan(config.getInt("waterRisingSpan"));
    }

    /**********
     * getter *
     **********/

    public static CentralPlayer centralPlayer() {
        return centralPlayer;
    }

    public static EffectiveRange effectiveRange() {
        return effectiveRange;
    }

    public static LavaRainySpan lavaRainySpan() {
        return lavaRainySpan;
    }

    public static WaterRisingSpan waterRisingSpan() {
        return waterRisingSpan;
    }

    /**********
     * setter *
     **********/

    /**
     * コマンドで中心プレイヤーを設定する
     **/
    public static void setCentralPlayer(CommandSender sender, String arg) {
        Player player = Bukkit.getPlayer(arg);
        if (player == null) {
            sender.sendMessage(DecorationConst.RED + "指定したプレイヤーは存在しません");
            return;
        }

        centralPlayer = new CentralPlayer(player);
        sender.sendMessage(DecorationConst.GREEN + "中心プレイヤーを" + centralPlayer.playerName() + "に設定しました");
    }

    /**
     * コマンドで影響範囲を設定する
     **/
    public static void setEffectiveRange(CommandSender sender, String arg) {
        try {
            effectiveRange = new EffectiveRange(Integer.parseInt(arg));
            sender.sendMessage(DecorationConst.GREEN + "影響範囲を" + arg + "に設定しました");
        } catch (NumberFormatException e) {
            sender.sendMessage("引数が不正です");
        }
    }

    /**
     * コマンドで溶岩雨の処理スパンを設定する
     **/
    public static void setLavaRainySpan(CommandSender sender, String arg) {
        try {
            lavaRainySpan = new LavaRainySpan(Integer.parseInt(arg));
            sender.sendMessage(DecorationConst.GREEN + "溶岩雨の処理スパン" + arg + "tickに設定しました");
        } catch (NumberFormatException e) {
            sender.sendMessage("引数が不正です");
        }
    }

    /**
     * コマンドで溶岩雨の処理スパンを設定する
     **/
    public static void setWaterRisingSpan(CommandSender sender, String arg) {
        try {
            waterRisingSpan = new WaterRisingSpan(Integer.parseInt(arg));
            sender.sendMessage(DecorationConst.GREEN + "水面上昇の処理スパン" + arg + "秒に設定しました");
        } catch (NumberFormatException e) {
            sender.sendMessage("引数が不正です");
        }
    }
}
