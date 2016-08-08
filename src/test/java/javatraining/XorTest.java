package javatraining;

import algorithms.Xor;
import designPatterns.EncryptionBase;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import tools.EncryptionResult;
import tools.KeyGen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * Created by shimi on 18/07/2016.
 */
public class XorTest {
    @Rule
    public TemporaryFolder folder= new TemporaryFolder();

    @Test
    public void XORAlgorithmTest() throws Exception {

        byte[] src = new byte[20];
        new Random().nextBytes(src);
        Xor xor = new Xor(new EncryptionBase());

        List<Byte> keys = new ArrayList<>();
        keys.add(KeyGen.randKey());

        EncryptionResult encryptionResult = xor.encrypt(src, keys);
        byte[] plain = xor.decrypt(encryptionResult.getData(), encryptionResult.getKey());

        //compare the original file and the decrypted one
        for(int i=0; i<plain.length; i++)
            assertEquals("Does not encrypt\\decrypt correctly", plain[i], src[i]);
    }
}