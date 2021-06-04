package net.kunmc.lab.lavaandwater.config;

public class EffectiveRange {

    private int effectiveRange;

    EffectiveRange(int effectiveRange) {
        if (effectiveRange < 16) {
            this.effectiveRange = 16;
        }

        if (effectiveRange > 512) {
            this.effectiveRange = 512;
        }

        this.effectiveRange = effectiveRange;
    }

    /**
     * 範囲を取得する(辺の長さの半分)
     * */
    public int halfRange() {
        return this.effectiveRange / 2;
    }
}
