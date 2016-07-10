package javatraining;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Created by shimi on 05/07/2016.
 */
public class Decryption {

    public static void decrypt(){
        FileOpener fileOpener = new FileOpener();
        File file = fileOpener.openFile(System.in, System.out);

        System.out.println("Enter a key (byte):");
        Scanner scanner = new Scanner(System.in);
        byte key = scanner.nextByte();

        try {
            byte[] data = Files.readAllBytes(file.toPath());//file to bytes

            String cypher = file.getAbsolutePath();

            String path = cypher.substring(0, cypher.lastIndexOf(".encrypted"));// remove .encrypted at the end (if there is)
            String file_path = path.substring(0, path.lastIndexOf("."));// copy file path without format
            file_path = file_path + "_decrypted" + path.substring(path.lastIndexOf("."), path.length());//add _decrypted to name and file format

            File plain = new File(file_path);//create file
            for (int i=0; i<data.length; i++)//write to file with decrypted bytes
            {
                data[i] = (byte) ((data[i] - key) % 256);
            }
            Files.write(Paths.get("C:\\Users\\shimi\\Desktop\\Untitled-5_decrypted.jpg"), data);


        }
        catch (Exception e) {
            System.out.println("Could not write file");
        }
    }
}
