import java.io.Serializable;
import java.util.HashMap;

public class PR131hashmap implements Serializable {

    private HashMap<String, Integer> persones = new HashMap<String, Integer>();
    private String nombreArchivo;

    public PR131hashmap(){

        nombreArchivo = "PR131HashMapData.ser";

        persones.put("Macarrones", 12);
        persones.put("Marc", 32);
        persones.put("Ricardo", 42);
        persones.put("Blai", 16);
        persones.put("Natalia", 33);

    }
    public String getNombreArchivo() {
        return nombreArchivo;
    }
    public HashMap<String, Integer> getPersones() {
        return persones;
    }
    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }
    public void setPersones(HashMap<String, Integer> persones) {
        this.persones = persones;
    }


}
