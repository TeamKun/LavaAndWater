package net.kunmc.lab.lavaandwater.config;

public class LavaRainySpan {
    /** 処理スパン */
    private int lavaRainySpan;

    LavaRainySpan(int lavaRainySpan) {
        if (lavaRainySpan <= 5) {
            this.lavaRainySpan = 5;
        }

        if (lavaRainySpan > 200) {
            this.lavaRainySpan = 100;
        }

        this.lavaRainySpan = lavaRainySpan;
    }

    /**
     * 処理スパンを取得する（tick）
     **/
    public int value() {
        return this.lavaRainySpan;
    }
}