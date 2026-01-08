package com.example.demo.security;



import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.engines.AESLightEngine;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.encoders.Hex;

public class Crypto {

    private static final String AESKey = "21d9c6a69b11689455ccf2c0d9c7b338a1098b9057f5244cb372184fd126b2bb";

    public static String performEncrypt(String keyText, String plainText) {
        try{
            byte[] key = Hex.decode(keyText.getBytes());
            byte[] ptBytes = plainText.getBytes();
            BufferedBlockCipher cipher = new PaddedBufferedBlockCipher(new CBCBlockCipher(new AESLightEngine()));
            cipher.init(true, new KeyParameter(key));
            byte[] rv = new byte[cipher.getOutputSize(ptBytes.length)];
            int oLen = cipher.processBytes(ptBytes, 0, ptBytes.length, rv, 0);
            cipher.doFinal(rv, oLen);
            return new String(Hex.encode(rv));
        } catch(Exception e) {
            return "Error";
        }
    }

    public static String performEncrypt(String cryptoText) {
        return performEncrypt(AESKey, cryptoText);
    }

    public static String performDecrypt(String keyText, String cryptoText) {
        try {
            byte[] key = Hex.decode(keyText.getBytes());
            byte[] cipherText = Hex.decode(cryptoText.getBytes());
            BufferedBlockCipher cipher = new PaddedBufferedBlockCipher(new CBCBlockCipher(new AESLightEngine()));
            cipher.init(false, new KeyParameter(key));
            byte[] rv = new byte[cipher.getOutputSize(cipherText.length)];
            int oLen = cipher.processBytes(cipherText, 0, cipherText.length, rv, 0);
            cipher.doFinal(rv, oLen);
            return new String(rv).trim();
        } catch(Exception e) {
            return "Error";
        }
    }

    public static String performDecrypt(String cryptoText) {
        return performDecrypt(AESKey, cryptoText);
    }

    public static void main(String[] args) {
        String strToEncrypt = "jdbc:sqlserver://localhost:1433;trustServerCertificate=true;databaseName=DocIn;schema=dbprject";//put text to encrypt in here
        System.out.println("Encryption Result : "+performEncrypt(strToEncrypt));

//        String strToDecrypt = "0d3f28a8cb0577e4fd42fbfdf2f72eac";//put text to decrypt in here
        String strToDecrypt = "b5b9ae7303c0d6e4dc804c8f73e54879c0300bfb59d2801305594c4b3044512455a1e6eea38043361022bd1d114884e34a3d375e4e0447176003d0197e3da880f3c8627f6975efe2d671b1ebc65fd925c40e5f3012da543e907f81caf6e42de7";//put text to decrypt in here
        System.out.println("Decryption Result : "+performDecrypt(strToDecrypt));
    }
}
