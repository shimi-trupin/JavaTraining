package javatraining;

import java.io.File;
import java.util.Scanner;

/**
 * Created by shimi on 30/05/2016.
 * Chapter 1 exercise
 */
public class App {
    public static void main(String[] args)
    {
        System.out.println("Type 'e' for encryption, or type 'd' for decryption");
        Scanner scanner = new Scanner(System.in);
        File file;
        FileOpener fileOpener = new FileOpener();

        String inp = scanner.nextLine();//scan for user input
        if (inp.equals("e") || inp.equals("E"))//encryption
        {
//            file = openFile();
//            file = fileOpener.OpenFile(System.in, System.out);
//            System.out.println("$file$: encrypted file");

            Encryption.Encrypt();
//            Encryption.enc();
        }
        else if (inp.toLowerCase().equals("d"))//decryption
        {
//            file = openFile();
            file = fileOpener.OpenFile(System.in, System.out);
            System.out.println("$file$: decrypted file");
        }
        else
            System.out.println("Incorrect Input");
//        File file = new File(src);

    }

    /*
    * Helper function
    * scans a file path from user, verifies it, and if all is good - returns file.
    * Otherwise, keeps scanning for path.
    * */
    private static File openFile()
    {
        File file;
        Scanner scanner = new Scanner(System.in);
        String path;
        while (true)
        {
            System.out.println("Enter a path to source file");
            path = scanner.nextLine();//scan for path from user
            try {
                file = new File(path);
                if (file.exists() && file.isFile()) {
                    return file;
                }
                else System.out.println("Incorrect path. Try again!");
            }
            catch (Exception e)
            {
                // if any error occurs
                e.printStackTrace();
            }

        }
    }
}
