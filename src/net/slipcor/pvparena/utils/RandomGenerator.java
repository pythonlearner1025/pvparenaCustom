package net.slipcor.pvparena.utils;
import java.util.concurrent.ThreadLocalRandom;

// nextInt is normally exclusive of the top value,
// so add 1 to make it inclusive

public class RandomGenerator {
    public static String generateRandomString(int length){
        // not completely random, but good enough so there are no
        // collisions among on-line servers managed by bargo
        // possibility of coincidence = 1 / (26 ^ length)
        String choices = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String result = "";
        for (int i=0; i<length;i++){
            int randInt = ThreadLocalRandom.current().nextInt(0, 26);
            result += choices.charAt(randInt);
        }
        return result;
    }
}
