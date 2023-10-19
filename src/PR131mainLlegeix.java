import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class PR131mainLlegeix implements Serializable {

    public static void main(String[] args) {
        PR131hashmap hashmap = new PR131hashmap();
        try {
            DataInputStream dis = new DataInputStream(new FileInputStream(hashmap.getNombreArchivo()));
            PR131hashmap obj = (PR131hashmap) readSerializableObject(dis);

            for (Map.Entry<String, Integer> entry : obj.getPersones().entrySet()) {
                String key = entry.getKey();
                Integer value = entry.getValue();
                System.out.println("Clave: " + key + ", Valor: " + value);
            }
            System.out.println(obj.getPersones());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }



    static Object readSerializableObject(DataInputStream dis) {
        try {
            // Llegeix la longitud de l'array
            int lgth = dis.readInt();
            byte[] data = new byte[lgth];
            // LLegeix l'array que conté l'objecte
            dis.readFully(data, 0, lgth);
            // Transforma l'array de bytes en objecte
            return bytesToSerializableObject(data);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new java.lang.AbstractMethodError();
    }

   static Object bytesToSerializableObject (byte[] data) {
       try {
           ByteArrayInputStream in = new ByteArrayInputStream(data);
           ObjectInputStream is = new ObjectInputStream(in);
           return is.readObject();
       } catch (ClassNotFoundException e) { e.printStackTrace();
       } catch (IOException e) { e.printStackTrace(); }
       return null;
    }
}