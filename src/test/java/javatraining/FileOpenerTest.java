package javatraining;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

import static org.junit.Assert.*;

/**
 * Created by shimi on 28/06/2016.
 */
public class FileOpenerTest {

    @Rule
    public TemporaryFolder folder= new TemporaryFolder();

    @Test
    public void openFile() throws IOException {
        File createdFile = folder.newFile("sampleText.txt");

        PipedOutputStream out = new PipedOutputStream();
        PipedInputStream in = new PipedInputStream();

        in.connect(out);

        out.write((createdFile.getPath() + "\n").getBytes());

        File openedFile = FileOpener.OpenFile(in,System.out);

        System.out.println(createdFile.getPath());
        assertEquals("Files Are not the same!!", openedFile, createdFile);
    }
}