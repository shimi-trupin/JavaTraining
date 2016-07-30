package javatraining;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Created by shimi on 25/07/2016.
 */
public class FileCreator {
    public File createFile(String fileName, byte[] data)
    {
        File file = new File(fileName);
        try {
            Files.write(file.toPath(), data);
            return file;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
