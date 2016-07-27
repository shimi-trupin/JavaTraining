package javatraining;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by shimi on 18/07/2016.
 */
public class MultiplicationTest {
    @Rule
    public TemporaryFolder folder= new TemporaryFolder();

    @Test
    public void MultiplicationAlgorithmTest() throws Exception {
        File createdFile = folder.newFile("sampleText.txt");

        //write sample text in the created file
        List<String> lines = Arrays.asList("The first line", "The second line");
        Files.write(createdFile.toPath(), lines, Charset.forName("UTF-8"));

        Multiplication multiplication = new Multiplication(new EncryptionBase());
        multiplication.encrypt(Files.readAllBytes(createdFile.toPath()), KeyGen.randOddKey());//encrypt the file

        byte key = multiplication.getKey();
        File cypher = new File(createdFile.getAbsolutePath() + ".encrypted");
        multiplication.decrypt(Files.readAllBytes(cypher.toPath()), key);//decrypt the file
        byte[] plain = Files.readAllBytes(Paths.get(createdFile.getAbsolutePath().replace(".txt", "_decrypted.txt")));
        byte[] original = Files.readAllBytes(createdFile.toPath());
        //compare the original file and the decrypted one
        for(int i=0; i<plain.length; i++)
            assertEquals("Does not encrypt\\decrypt correcly", plain[i], original[i]);
    }
}