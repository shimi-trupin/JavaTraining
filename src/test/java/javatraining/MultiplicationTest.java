package javatraining;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * Created by shimi on 18/07/2016.
 */
public class MultiplicationTest {

    @Test
    public void MultiplicationAlgorithmTest() throws Exception {
        byte[] src = new byte[20];
        new Random().nextBytes(src);

        Multiplication multiplication = new Multiplication(new EncryptionBase());

        List<Byte> keys = new ArrayList<>();
        keys.add(KeyGen.randKey());

        EncryptionResult encryptionResult = multiplication.encrypt(src, keys);
        byte[] plain = multiplication.decrypt(encryptionResult.getData(), encryptionResult.getKey());

        //compare the original file and the decrypted one
        for(int i=0; i<plain.length; i++)
            assertEquals("Does not encrypt\\decrypt correctly", plain[i], src[i]);
    }
}