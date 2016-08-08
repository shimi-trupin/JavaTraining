package javatraining.tools;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.util.List;

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

    public void serializeKey(String path, List<Byte> key)
    {
        try
        {
            FileOutputStream fileOut = new FileOutputStream(path);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(key);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in " + path);
        }catch(IOException i)
        {
            i.printStackTrace();
        }
    }
}
