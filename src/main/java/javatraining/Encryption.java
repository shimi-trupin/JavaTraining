package javatraining;

import lombok.val;

import java.util.Random;

/**
 * Created by shimi on 29/06/2016.
 * Performs an encryption of file
 */
public class Encryption {

    /**
     * @return randomly generated number (byte)
     */
    private static byte randKey(){
        Random random = new Random();
        byte[] b = new byte[1];
        random.nextBytes(b);
        return b[0];
    }
}
