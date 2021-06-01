package net.kunmc.lab.lavaandwater.config;

public class LavaRainySpan {
    /** 処理スパン */
    private int lavaRainySpan;

    LavaRainySpan(int lavaRainySpan) {
        if (lavaRainySpan <= 0) {
            this.lavaRainySpan = 1;
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