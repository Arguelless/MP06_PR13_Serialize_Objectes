import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class PR130mainPersonesHashmap {

    public static void main(String[] args) {
        HashMap<String, Integer> persones = new HashMap<String, Integer>();
        String nombreArchivo = "PR130persones.dat";

        persones.put("Marc", 12);
        persones.put("Pol", 32);
        persones.put("Ricardo", 51);
        persones.put("Biel", 11);
        persones.put("Natalia", 80);


        try {
            DataOutputStream dos = new DataOutputStream(new FileOutputStream(nombreArchivo));


            for (HashMap.Entry<String, Integer> entry : persones.entrySet()) {
                dos.writeUTF(entry.getKey());
                dos.writeInt(entry.getValue());
            }

            dos.close();
            DataInputStream dis = new DataInputStream(new FileInputStream(nombreArchivo));

            while (dis.available() > 0) {
                String nom = dis.readUTF();
                int edat = dis.readInt();
                System.out.println(nom + ": " + edat);
            }
            dis.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
