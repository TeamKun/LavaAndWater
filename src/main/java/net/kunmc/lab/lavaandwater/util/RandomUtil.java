package net.kunmc.lab.lavaandwater.util;

import java.util.Random;

public class RandomUtil {
    /**
     * 抽選をする
     * @param "当選確率"
     * */
    public static boolean lottery(double probability) {
        Random random = new Random();

        int randomInt = random.nextInt(10000) + 1;

        if (randomInt <= probability * 100) {
            return true;
        }

        return false;
    }
}
