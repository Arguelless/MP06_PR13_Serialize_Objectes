import java.io.Serializable;

public class PR132Persona implements Serializable {

    private String nom;
    private String cognom;
    private int edat;

    public PR132Persona(String nom, String cognom, int edat) {
        this.nom = nom;
        this.cognom = cognom;
        this.edat = edat;
    }
    public String getNom() {
        return nom;
    }

    public int getEdat() {
        return edat;
    }
    public String getCognom() {
        return cognom;
    }

}
