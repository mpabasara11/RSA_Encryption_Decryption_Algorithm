import java.security.*;
import java.io.*;




public class KeyGen {

	public void  generateKeyPair() throws Exception {


		KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
		generator.initialize(2048, new SecureRandom());
		KeyPair pair = generator.generateKeyPair();

        //save keypair in file
        FileOutputStream fos = new FileOutputStream("keypair.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(pair);
        oos.close();


   


    }




    public static void main(String[] args) throws Exception  {
        
        KeyGen obj = new KeyGen();
        obj.generateKeyPair();

        
    }
    
}
