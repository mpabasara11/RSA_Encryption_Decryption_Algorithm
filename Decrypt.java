import javax.crypto.Cipher;
import java.security.*;
import java.util.Base64;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Paths;


public class Decrypt {

    public KeyPair getKeys(String keyFileName) throws Exception {

        
        //get keypair from file
        FileInputStream fis = new FileInputStream(keyFileName+".txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        KeyPair keyPair = (KeyPair) ois.readObject();
        ois.close();
        

       return keyPair;

}


public String getSource(String sourceName){


    //get string from text file
    String source = "";
    try {

        source = java.nio.file.Files.readString(Paths.get(sourceName+".txt"));

    } catch (IOException e) {

        e.printStackTrace();

    }

    return source;
}



public  String decryptData(String cipherText, PrivateKey privateKey) throws Exception {

    byte[] bytes = Base64.getDecoder().decode(cipherText);
    Cipher decriptCipher = Cipher.getInstance("RSA");
    decriptCipher.init(Cipher.DECRYPT_MODE, privateKey);

    return new String(decriptCipher.doFinal(bytes));
}


public void storeDecrypted(String decryptedString,String distinationName){

    //store string to text file
    try{

        java.nio.file.Files.writeString(Paths.get(distinationName+".txt"), decryptedString);

    } catch (IOException e){

        e.printStackTrace();

    }

}



    public static void main(String[] args) throws Exception {

        String keyFileName=args[0];
        String SourceFileName=args[1];
        String DistinationFileName=args[2];

        Decrypt decrypt = new Decrypt();

        KeyPair keyPair = decrypt.getKeys(keyFileName);
        PrivateKey privateKey = keyPair.getPrivate();
        String SourceString= decrypt.getSource(SourceFileName);
        String DecryptedString=  decrypt.decryptData(SourceString, privateKey);
        decrypt.storeDecrypted(DecryptedString, DistinationFileName);

       
    
        
    }
    
}
