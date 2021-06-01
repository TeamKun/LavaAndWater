package net.kunmc.lab.lavaandwater.config;

public class WaterRisingSpan {

    /** 処理スパン */
    private int waterRisingSpan;

    WaterRisingSpan(int waterRisingSpan) {
        if (waterRisingSpan <= 0) {
            this.waterRisingSpan = 1;
        }

        if (waterRisingSpan > 600) {
            this.waterRisingSpan = 600;
        }

        this.waterRisingSpan = waterRisingSpan;
    }

    /**
     * 処理スパンを取得する(秒)
     **/
    public int value() {
        return this.waterRisingSpan * 20;
    }
}
