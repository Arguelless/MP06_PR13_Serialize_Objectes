import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class PR131mainEscriu implements Serializable {
    public static void main(String[] args) {
        PR131hashmap hashmap = new PR131hashmap();
        try {
            DataOutputStream dos = new DataOutputStream(new FileOutputStream(hashmap.getNombreArchivo()));
            writeSerializableObject(hashmap, dos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        
    }

        
   static void writeSerializableObject (Object obj, DataOutputStream dos) {
       try {
           // Transforma l'objecte a bytes[]
           byte [] data = serializableObjectToBytes(obj);
           // Guarda la longitud de l'array
           dos.writeInt(data.length);
           // Guarda els bytes de l'objecte
           dos.write(data);
       } catch (IOException e) { e.printStackTrace(); }
   }
   
   static byte[] serializableObjectToBytes (Object obj) {
       // Transforma l'objecte a bytes[]
       ObjectOutputStream oos;
       ByteArrayOutputStream bos = new ByteArrayOutputStream();
       try {
           oos = new ObjectOutputStream(bos);
           oos.writeObject(obj);
           oos.flush();
           return bos.toByteArray();
       } catch (IOException e) { e.printStackTrace(); }
       return null;
   }

}
