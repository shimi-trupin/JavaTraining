package javatraining.dirEncryption;

import com.google.inject.Inject;
import javatraining.designPatterns.Encryption;
import javatraining.designPatterns.EncryptionDecorator;
import javatraining.tools.EncryptionResult;
import lombok.Getter;
import lombok.Setter;
import javatraining.tools.FileCreator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

/**
 * Created by shimi on 03/08/2016.
 */
public class AsyncTask implements Runnable {

    @Getter @Setter private File file;
    @Getter @Setter private List<Byte> key;
    @Getter @Setter private EncryptionDecorator encryption;
    private int action;
    private final int ENCRYPTION = 0;
    private final int DECRYPTION = 1;

    @Inject
    public AsyncTask(int action, File file, List<Byte> key, EncryptionDecorator encryption) {
        this.action = action;
        this.file = file;
        this.key = key;
        this.encryption = encryption;
    }

    @Override
    public void run() {

        //convert file to byte array (data)
        FileCreator fileCreator = new FileCreator();
        EncryptionResult encryptionResult;
        try {
            byte[] data = Files.readAllBytes(file.toPath());

            if (action == ENCRYPTION) {
                encryptionResult = encryption.encrypt(data, key);

                String filePath = file.getAbsolutePath();
                String subDir = filePath.substring(0, filePath.lastIndexOf("\\")) + "\\encrypted_decrypted";

                filePath = filePath.substring(filePath.lastIndexOf("\\"));
                filePath = subDir + filePath;
                fileCreator.createFile(filePath, encryptionResult.getData());
            }

            else if (action == DECRYPTION){
                data = encryption.decrypt(data, key);

                String filePath = file.getAbsolutePath();
                String subDir = filePath.substring(0, filePath.lastIndexOf("\\")) + "\\encrypted_decrypted";

                filePath = filePath.substring(filePath.lastIndexOf("\\"));
                filePath = subDir + filePath;
                fileCreator.createFile(filePath, data);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        //if encryption
        //encrypt data
        //save file in subDir

        //if decryption
        //decrypt data
        //save file in subDir

    }
}
