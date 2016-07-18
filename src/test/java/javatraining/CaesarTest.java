package javatraining;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by shimi on 17/07/2016.
 */
public class CaesarTest {

    @Rule
    public TemporaryFolder folder= new TemporaryFolder();

    @Test
    public void CaesarAlgorithmTest() throws Exception {
        File createdFile = folder.newFile("sampleText.txt");

        //write sample text in the created file
        List<String> lines = Arrays.asList("The first line", "The second line");
        Files.write(createdFile.toPath(), lines, Charset.forName("UTF-8"));

        Caesar caesar = new Caesar(new EncryptionBase());
        caesar.encrypt(createdFile);//encrypt the file

        byte key = caesar.getKey();
        File cypher = new File(createdFile.getAbsolutePath() + ".encrypted");
        caesar.decrypt(cypher, key);//decrypt the file
        byte[] plain = Files.readAllBytes(Paths.get(createdFile.getAbsolutePath().replace(".txt", "_decrypted.txt")));
        byte[] original = Files.readAllBytes(createdFile.toPath());
        //compare the original file and the decrypted one
        for(int i=0; i<plain.length; i++)
        assertEquals("Does not encrypt\\decrypt correctly", plain[i], original[i]);
    }
}