package net.kunmc.lab.lavaandwater.config;

public class WaterRisingSpan {

    /** 処理スパン */
    private int waterRisingSpan;

    WaterRisingSpan(int waterRisingSpan) {


        if (waterRisingSpan <= 20) {
            waterRisingSpan = 20;
        }

        if (waterRisingSpan > 600) {
            waterRisingSpan = 600;
        }

        this.waterRisingSpan = waterRisingSpan;
    }

    /**
     * 処理スパンを取得する(tick)
     **/
    public int tickValue() {
        return this.waterRisingSpan * 20;
    }

    /**
     * 処理スパンを取得する(秒)
     **/
    public int secondValue() {
        return this.waterRisingSpan;
    }
}
