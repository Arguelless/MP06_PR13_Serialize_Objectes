import java.io.RandomAccessFile;

public class PR134Estudiants {
    private static final int ID_SIZE = 4; // bytes
    private static final int CHAR_SIZE = 2; // bytes per caràcter en UTF-16
    private static final int NAME_SIZE = 20; // Longitud màxima en caràcters del nom

    public static void main(String[] args) {
        try (RandomAccessFile raf = new RandomAccessFile("./src/estudiants.dat", "rw")) {
            raf.setLength(0);
            // Afegir estudiants
            afegirEstudiant(raf, 1, "Eric Casas", 9.3f);
            afegirEstudiant(raf, 2, "Eric Casas", 9.3f);

            // Consultar i mostrar els videojocs afegits
            mostrarEstudiant(raf, 1, "Original");
            mostrarEstudiant(raf, 2, "Original");

            // Actualitzar els noms dels videojocs
            actualitzarNotaEstudiant(raf, 1, 8.5f);
            actualitzarNotaEstudiant(raf, 2, 3.7f);

            // Consultar i mostrar els videojocs actualitzats
            mostrarEstudiant(raf, 1, "Actualitzat");
            mostrarEstudiant(raf, 2, "Actualitzat");

            // Tornar a actualitzar el primer videojoc
            actualitzarNotaEstudiant(raf, 1, 9.9f);
            mostrarEstudiant(raf, 1, "Re-actualitzat");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void afegirEstudiant(RandomAccessFile raf, int id, String nom, float nota) throws Exception {
        raf.seek(raf.length());
        raf.writeInt(id);
        raf.writeChars(getPaddedName(nom));
        raf.writeFloat(nota);
    }


    public static String consultarNota(RandomAccessFile raf, int id) throws Exception {
        raf.seek(getSeekPosition(id));
        int readId = raf.readInt();
    
        char[] chars = new char[NAME_SIZE];
        for (int i = 0; i < NAME_SIZE; i++) {
            chars[i] = raf.readChar();
        }
        float nota = raf.readFloat();
        return readId + ": " + new String(chars).trim() + " -> " + nota;
    }

    public static void actualitzarNotaEstudiant(RandomAccessFile raf, int id, float novaNota) throws Exception {
        raf.seek(getSeekPosition(id) + ID_SIZE + NAME_SIZE * CHAR_SIZE);
        raf.writeFloat(novaNota);

    }

    public static void mostrarEstudiant(RandomAccessFile raf, int id, String msg) throws Exception {
        System.out.println(msg + " " + id + ": " + consultarNota(raf, id));
    }

    /**
     * Calcula la posició (offset) dins del fitxer on s'inicia el registre del
     * videojoc amb l'ID especificat.
     *
     * @param id L'identificador del videojoc.
     * @return La posició dins del fitxer on s'inicia el registre del videojoc.
     */
    private static long getSeekPosition(int id) {
        // L'operació (id - 1) serveix per obtenir un índex basat en 0.
        // (ID_SIZE + NAME_SIZE * CHAR_SIZE) calcula la mida total en bytes d'un
        // registre de videojoc.
        // ID_SIZE representa la mida en bytes de l'ID del videojoc.
        // NAME_SIZE * CHAR_SIZE representa la mida total en bytes del nom del videojoc.
        return (id - 1) * (ID_SIZE + NAME_SIZE * CHAR_SIZE + ID_SIZE);
    }

    /**
     * Retorna una versió del nom del videojoc que sempre té una longitud fixa
     * (NAME_SIZE).
     * Si el nom és més llarg que NAME_SIZE, es trunca. Si és més curt, s'omple amb
     * espais en blanc.
     *
     * @param name El nom original del videojoc.
     * @return El nom amb una longitud fixa de NAME_SIZE caràcters.
     */
    private static String getPaddedName(String name) {
        // Si el nom és més llarg que la mida màxima permesa (NAME_SIZE),
        // es trunca per ajustar-se a aquesta mida.
        if (name.length() > NAME_SIZE) {
            return name.substring(0, NAME_SIZE);
        }
        // Si el nom és més curt que NAME_SIZE, s'omple amb espais en blanc fins a
        // assolir aquesta mida.
        // String.format amb "%1$-" + NAME_SIZE + "s" assegura que la cadena resultant
        // tingui una longitud fixa.
        return String.format("%1$-" + NAME_SIZE + "s", name);
    }
}
