package george;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.security.*;

public class TimeCryptor {
    private Algorithms algorithm;
    private Regimes regime;
    private int sizeKey;
    private Key key;

    public TimeCryptor(Algorithms a, Regimes r) {
        if (a == Algorithms.DES) {
            sizeKey = 56;
        } else {
            sizeKey = 256;
        }
        algorithm = a;
        regime = r;
    }

    public long getTimeAlghoritm(byte[] arrByte) {
        long start = System.currentTimeMillis();
        byte[] encrypted = this.encrypt(arrByte);
        this.decrypt(encrypted);
        long finish = System.currentTimeMillis();
        System.out.print(algorithm+" : "+regime+": ");
        return finish - start;
    }

    private byte[] encrypt(byte[] arrByte) {
        byte[] encrypted = new byte[0];
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm.toString());
            keyGenerator.init(sizeKey);
            key = keyGenerator.generateKey();
            Cipher cipher = Cipher.getInstance(algorithm.toString() + "/" + regime.toString() + "/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            encrypted = cipher.doFinal(arrByte);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            System.out.println("Error: unknown algorithm.");
        } catch (InvalidKeyException e) {
            System.out.println("Error: invalid key.");
        } catch (IllegalBlockSizeException e) {
            System.out.println("Error: illegal block size.");
        } catch (BadPaddingException e) {
            System.out.println("Error: bad padding.");
        }
        return encrypted;
    }

    private void decrypt(byte[] arrByte) {
        try {

            KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm.toString());
            keyGenerator.init(sizeKey);
            Cipher cipher = Cipher.getInstance(algorithm.toString() + "/" + regime.toString() + "/PKCS5Padding");

            IvParameterSpec ivspec = null;
            if(regime==Regimes.CBC) {
                byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
                if(algorithm==Algorithms.DES || algorithm==Algorithms.RC2){
                    iv = new byte[]{0, 0, 0, 0, 0, 0, 0, 0};
                }
                ivspec = new IvParameterSpec(iv);
            }
            cipher.init(Cipher.DECRYPT_MODE, key, ivspec);
            cipher.doFinal(arrByte);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            System.out.println("Error: unknown algorithm.");
        } catch (InvalidKeyException e) {
            System.out.println("Error: invalid key.");
        } catch (IllegalBlockSizeException e) {
            System.out.println("Error: illegal block size.");
        } catch (BadPaddingException e) {
            System.out.println("Error: bad padding.");
        } catch (InvalidAlgorithmParameterException e) {
            System.out.println("Error: invalid algorithm.");
        }
    }
}
