package javatraining.dirEncryption;

import com.google.inject.Inject;
import javatraining.designPatterns.Encryption;
import javatraining.designPatterns.EncryptionDecorator;
import javatraining.tools.EncryptionResult;
import lombok.Getter;
import lombok.Setter;
import javatraining.tools.FileCreator;
import javatraining.tools.FileOpener;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

/**
 * Created by shimi on 01/08/2016.
 */
public class SyncDir implements Runnable {

    private static final int ENCRYPTION = 0;
    private static final int DECRYPTION = 1;
    private int action;
//    @Getter @Setter private T encryption;
    @Getter @Setter private EncryptionDecorator algorithm;
    @Getter @Setter private List<Byte> keys;
    @Getter @Setter private String dirPath;

    @Inject
    public SyncDir(int action, EncryptionDecorator algorithm, String dirPath, List<Byte> keys) {
        this.action = action;
        this.algorithm = algorithm;
        this.dirPath = dirPath;
        this.keys = keys;
    }

    @Override
    public void run() {
        File dir = new File(dirPath);
        if (!dir.isDirectory())
            return;
        File[] fileList = dir.listFiles();
        if (fileList.length == 0)
            return;

        String subDir = dirPath + "\\encrypted_decrypted";
        if(!(new File(subDir)).mkdir())
            return;

        FileCreator fileCreator = new FileCreator();


        if (action == ENCRYPTION)
        {
            EncryptionResult encryptionResult;
            try {
                for (File file : fileList)
                {

                    byte[] data = Files.readAllBytes(file.toPath());

                    encryptionResult = algorithm.encrypt(data, keys);

                    String filePath = file.getAbsolutePath();
                    filePath = filePath.substring(filePath.lastIndexOf("\\")) /*+ ".encrypted"*/;
                    filePath = subDir + filePath;

                    fileCreator.createFile(filePath, encryptionResult.getData());

                }
                fileCreator.serializeKey(subDir + "\\key.bin", keys);

                System.out.println("\nfinished");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (action == DECRYPTION)
        {
            keys = FileOpener.getKeysDeserialization(dirPath + "\\key.bin");
            if (keys == null)
                return;

            try {
                for (File file : fileList) {
//                    if (!Objects.equals(file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf("\\")), "key.bin"))
                    if(!file.getAbsolutePath().equals(dirPath + "\\key.bin")){
//                        keys = FileOpener.getKeysDeserialization(file.getAbsolutePath());

                        byte[] data = Files.readAllBytes(file.toPath());

                        data = algorithm.decrypt(data, keys);

//                        String path = file.getAbsolutePath();

//                        // remove .encrypted at the end (if there is)
//                        path = path.substring(0, path.lastIndexOf(".encrypted"));
//                        // copy file name without format
//                        String file_path = path.substring(path.lastIndexOf("\\"), path.lastIndexOf("."));
//                        //add _decrypted to name and file format
//                        file_path = subDir + file_path + "_decrypted" + path.substring(path.lastIndexOf("."), path.length());

                        String filePath = file.getAbsolutePath();
                        filePath = filePath.substring(filePath.lastIndexOf("\\")) ;
                        filePath = subDir + filePath;

                        fileCreator.createFile(filePath, data);
                    }
                }

            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
