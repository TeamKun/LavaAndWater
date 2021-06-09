package net.kunmc.lab.lavaandwater.config;

public class WaterLevel {
    /** インスタンス */
    private static final WaterLevel INSTANCE = new WaterLevel();

    /** 高さの上限 */
    private final int HEIGHT_LIMIT = 256;
    /** 現在の水面の高さ */
    private int currentLevel;

    private WaterLevel() {
        currentLevel = 1;
    }

    /**
     * インスタンスを取得する
     * */
    static WaterLevel instance() {
        return INSTANCE;
    }

    /**
     * 現在の水面の高さを取得する.
     * */
    public int currentLevel() {
        return this.currentLevel;
    }

    /**
     * 水位をセットする
     * */
    int setWaterLevel(int level) {
        if (level <= 1) {
            level = 1;
        }

        if (level > HEIGHT_LIMIT) {
            level = HEIGHT_LIMIT;
        }

        currentLevel = level;

        return currentLevel;
    }

    /**
     * 水面を上昇させる.
     * */
    public void rise() {
        if (currentLevel > HEIGHT_LIMIT) {
            return;
        }
        currentLevel ++;
    }

    /**
     * 高度の上限に達しているか判定.
     * */
    public boolean isReachedHeightLimit() {
        return this.currentLevel >= this.HEIGHT_LIMIT;
    }
}
