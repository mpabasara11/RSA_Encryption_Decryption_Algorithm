import javax.crypto.Cipher;
import java.security.*;
import java.util.Base64;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Paths;


public class Encrypt {


 
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

 

	public String encryptData(String input,PublicKey publicKey) throws Exception {
  
       
		Cipher encryptCipher = Cipher.getInstance("RSA");
		encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
		byte[] cipherText = encryptCipher.doFinal(input.getBytes());
        
		return Base64.getEncoder().encodeToString(cipherText);

	}




    public void storeCipher(String cipher,String distinationName){

        //store string to text file
        try{

            java.nio.file.Files.writeString(Paths.get(distinationName+".txt"), cipher);

        } catch (IOException e){

            e.printStackTrace();

        }

    }


    public static void main(String[] args) throws Exception {  

        String keyFileName=args[0];
        String SourceFileName=args[1];
        String DistinationFileName=args[2];

        Encrypt encrypt = new Encrypt();


        KeyPair keyPair = encrypt.getKeys(keyFileName);
        PublicKey publicKey = keyPair.getPublic(); 
        String SourceString=encrypt.getSource(SourceFileName);
        String EncryptedString=encrypt.encryptData(SourceString,publicKey); 
        encrypt.storeCipher(EncryptedString,DistinationFileName);

    
    }
    
}
