package javatraining;

import java.io.*;
import java.util.Scanner;

/**
 * Created by shimi on 28/06/2016.
 * Helper Class, scans a file path from user, verifies it, and if all is good - returns file.
 * Otherwise, keeps scanning for path.
 * @author shimi
 */
public class FileOpener {

    /**
     * scans a file path from user, verifies it, and if all is good - returns file.
     * Otherwise, keeps scanning for path.
     * @param in input stream for file source
     * @param out output stream
     * @return file that was opened
     */
    public /*static*/ File openFile(InputStream in, PrintStream out) {

        File file;
        Scanner scanner = new Scanner(in);
        String path;

        while (true)
        {
            out.println("Enter a path to source file");
            path = scanner.nextLine();//scan for path from user
            try {
                file = new File(path);
                if (file.exists() && file.isFile()) {
                    return file;
                }
                else out.println("Incorrect path. Try again!");
            }
            catch (Exception e)
            {
                // if any error occurs
                e.printStackTrace();
            }

        }
    }

    public static Keys getKeysDeserialization(String path){
        Keys keys = null;
        try
        {
            FileInputStream fileIn = new FileInputStream(path);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            keys = (Keys) in.readObject();
            in.close();
            fileIn.close();
        }catch(IOException i)
        {
            i.printStackTrace();
            return null;
        }catch(ClassNotFoundException c)
        {
            System.out.println("Employee class not found");
            c.printStackTrace();
            return null;
        }
        return keys;
    }
}
