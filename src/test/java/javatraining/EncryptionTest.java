package javatraining;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.instanceOf;

/**
 * Created by shimi on 30/06/2016.
 * Test class for Encryption
 */
public class EncryptionTest {
    @Test
    public void randKeyTest() throws Exception {
        //check that key is generated
        byte b = Encryption.randKey();
        assertThat(b, instanceOf(byte.class));

    }
}