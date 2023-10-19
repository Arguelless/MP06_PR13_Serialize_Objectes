import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class PR132main implements Serializable {

    public static void main(String[] args) {
        PR132Persona p1 = new PR132Persona("Andres", "Sanchez", 20);
        PR132Persona p2 = new PR132Persona("Paula", "Martinez", 20);
        PR132Persona p3 = new PR132Persona("Joan", "Perez", 20);

        ArrayList<PR132Persona> persones = new ArrayList<PR132Persona>();
        persones.add(p1);
        persones.add(p2);
        persones.add(p3);

        String column1Format = "%-12.12s"; 
        String column2Format = "%-15.15s"; 
        String column3Format = "%4.4s"; 
        String formatInfo = column1Format + " " + column2Format + " " + column3Format;
        try {
            DataOutputStream dos = new DataOutputStream(new FileOutputStream("PR132People.dat"));
            writeSerializableObject(persones, dos);
            System.out.println("Objecte serialitzat correctament");
            dos.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            DataInputStream dis = new DataInputStream(new FileInputStream("PR132People.dat"));
            ArrayList<PR132Persona> obj = (ArrayList<PR132Persona>) readSerializableObject(dis);
            System.out.format(formatInfo, "Nom","Cognom","Edat");
            System.out.println();
            for (PR132Persona persona : obj) {
                System.out.format(formatInfo, persona.getNom(),persona.getCognom(),persona.getEdat());
                System.out.println();
            }
            dis.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    static void writeSerializableObject(Object obj, DataOutputStream dos) {
        try {
            // Transforma l'objecte a bytes[]
            byte[] data = serializableObjectToBytes(obj);
            // Guarda la longitud de l'array
            dos.writeInt(data.length);
            // Guarda els bytes de l'objecte
            dos.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static byte[] serializableObjectToBytes(Object obj) {
        // Transforma l'objecte a bytes[]
        ObjectOutputStream oos;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.flush();
            return bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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

    static Object bytesToSerializableObject(byte[] data) {
        try {
            ByteArrayInputStream in = new ByteArrayInputStream(data);
            ObjectInputStream is = new ObjectInputStream(in);
            return is.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
