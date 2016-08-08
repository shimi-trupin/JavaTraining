package javatraining.tools;

import java.util.Random;

/**
 * Created by shimi on 24/07/2016.
 */
public class KeyGen {
    public static byte randKey(){
        Random random = new Random();
        byte[] b = new byte[1];
        random.nextBytes(b);
        return b[0];
    }

    public static byte randOddKey(){
        byte key;
        do {
            key = randKey();
        } while (key %2 == 0);//make sure key is not divided by 2
        return key;
    }
}
