package javatraining;

import lombok.Cleanup;
import org.junit.Rule;
import org.junit.Test;

import java.io.File;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.instanceOf;
import org.junit.rules.TemporaryFolder;

/**
 * Created by shimi on 30/06/2016.
 * Test class for EncryptionClass
 */
public class EncryptionClassTest {
    /*@Test
    public void randKeyTest() throws Exception {
        //check that key is generated
        byte b = EncryptionClass.randKey();
        assertThat(b, instanceOf(byte.class));

    }

    @Rule
    public TemporaryFolder folder= new TemporaryFolder();

    @Test
    public void encryptionTest() throws Exception {
        File createdFile = folder.newFile("sampleText.txt");

        @Cleanup PipedOutputStream out = new PipedOutputStream();
        @Cleanup PipedInputStream in = new PipedInputStream();

        //add functionality test

    }*/
}