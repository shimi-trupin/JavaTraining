package javatraining;

import algorithms.Caesar;
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
 * Created by shimi on 17/07/2016.
 */
public class CaesarTest {

    @Rule
    public TemporaryFolder folder= new TemporaryFolder();

    @Test
    public void CaesarAlgorithmTest() throws Exception {
        /*File createdFile = folder.newFile("sampleText.txt");

        //write sample text in the created file
        List<String> lines = Arrays.asList("The first line", "The second line");
        Files.write(createdFile.toPath(), lines, Charset.forName("UTF-8"));

        Caesar caesar = new Caesar(new EncryptionBase());
        caesar.encrypt(Files.readAllBytes(createdFile.toPath()), KeyGen.randKey());//encrypt the file

        byte key = caesar.getKey();
        File cypher = new File(createdFile.getAbsolutePath() + ".encrypted");
        caesar.decrypt(Files.readAllBytes(cypher.toPath()), key);//decrypt the file // TODO: 25/07/2016 change this, assert byte[]
        byte[] plain = Files.readAllBytes(Paths.get(createdFile.getAbsolutePath().replace(".txt", "_decrypted.txt")));
        byte[] original = Files.readAllBytes(createdFile.toPath());
        //compare the original file and the decrypted one
        for(int i=0; i<plain.length; i++)
        assertEquals("Does not encrypt\\decrypt correctly", plain[i], original[i]);*/


        byte[] src = new byte[20];
        new Random().nextBytes(src);
        Caesar caesar = new Caesar(new EncryptionBase());

        List<Byte> keys = new ArrayList<>();
        keys.add(KeyGen.randKey());
        EncryptionResult encryptionResult = caesar.encrypt(src, keys);
        byte[] plain = caesar.decrypt(encryptionResult.getData(),encryptionResult.getKey());

        for(int i=0; i<plain.length; i++)
            assertEquals("Does not encrypt\\decrypt correctly", plain[i], src[i]);
    }
}