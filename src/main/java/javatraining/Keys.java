package javatraining;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by shimi on 26/07/2016.
 */
public class Keys implements Serializable {
    @Getter @Setter private byte key1;
    @Getter @Setter private byte key2;

    public Keys(byte key1, byte key2) {
        this.key1 = key1;
        this.key2 = key2;
    }
}
