package javatraining;

import java.io.File;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * @author shimi
 * Created by shimi on 28/06/2016.
 * Helper Class, scans a file path from user, verifies it, and if all is good - returns file.
 * Otherwise, keeps scanning for path.
 */
public class FileOpener {

    public static File OpenFile(InputStream in, PrintStream out) {

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
}
