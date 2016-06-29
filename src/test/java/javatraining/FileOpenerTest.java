package javatraining;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.MethodRule;
import org.junit.rules.TemporaryFolder;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Created by shimi on 28/06/2016.
 */
public class FileOpenerTest {

    @Rule
    public TemporaryFolder folder= new TemporaryFolder();

    @Test
    public void openFile() throws IOException {
//        System.out.println("Test 1 - open file functionality");

        File createdFile = folder.newFile("sampleText.txt");

        PipedOutputStream out = new PipedOutputStream();
        PipedInputStream in = new PipedInputStream();

        in.connect(out);

        out.write((createdFile.getPath() + "\n").getBytes());

        FileOpener fileOpener = new FileOpener();
        File openedFile = fileOpener.OpenFile(in,System.out);

        System.out.println(createdFile.getPath());
        assertEquals("Files Are not the same!!", openedFile, createdFile);

    }

    @Test
    public void FileOpenerMockTest() throws Exception {

//        System.out.println("Test 2 - Mock test");
//        System.out.println("MOCK TEST!!");

        FileOpener fileOpener = Mockito.mock(FileOpener.class);

        when(fileOpener.OpenFile(null, System.out)).thenReturn(new File("test"));

        assertEquals("Did not open method OpenFile!", fileOpener.OpenFile(null, System.out),new File("test"));

    }
}