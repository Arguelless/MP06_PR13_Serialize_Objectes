import java.util.List;
import java.util.Scanner;


public class PR133mainTreballadors {
    static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
        boolean running = true;
        String filePath = "./src/PR133treballadors.csv";

        while (running) {
            
            String mete_id = "Introduce la ID del trabajador: ";
            System.out.println(mete_id);


            try {
                int opcio = Integer.valueOf(llegirLinia("ID: "));

                
                if (opcio == 0) {
                    System.out.println("No existe el trabajador");
                } else {
                    System.out.println();
                }
            
            } catch (NumberFormatException e) {
                System.out.println("Introduce un número");
            }
             catch (Exception e) {
                System.out.println(e);
            }
            List<String> csvLines = UtilsCSV.read(filePath);
            
            System.out.println(UtilsCSV.getColumnData(csvLines, "1"));
        }
    }
    static public String llegirLinia (String text) {
        System.out.print(text);
        return in.nextLine();
    }
}
