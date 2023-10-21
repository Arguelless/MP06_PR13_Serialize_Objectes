import java.util.List;
import java.util.Scanner;

public class PR133mainTreballadors {
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        String filePath = "./src/PR133treballadors.csv";

        while (running) {

            System.out.println("Introduce la ID del trabajador: ");
            List<String> csvLines = UtilsCSV.read(filePath);
            String[] ids = UtilsCSV.getColumnData(csvLines, "id");

            try {
                String opcio = llegirLinia("ID (0 para salir): ");
                if (opcio.equals("0")) {
                    running = false;
                    break;
                }
                Boolean encontrado = false;

                for (String id : ids) {
                    if (id.equals(opcio)) {
                        encontrado = true;
                        break;
                    }
                }
                if (encontrado) {

                    String[] keys = UtilsCSV.getKeys(csvLines);
                    System.out.println("Datos del trabajador: ");
                    for (String key : keys) {
                        System.out.println(" - " + key + ": " + UtilsCSV.getColumnData(csvLines, key)[UtilsCSV.getLineNumber(csvLines, "id", opcio)]);
                    }

                    boolean runningModif = true;
                    while (runningModif) {
                        System.out.println("Introduce el parametro a modificar: ");
                        for (String key : keys) {
                            if (keys[keys.length - 1] == key) {
                                System.out.println(key);
                                break;
                            }
                            System.out.print(key + ", ");

                        }
                        String parametro = llegirLinia("Parametro (0 para salir): ");
                        if (parametro.equals("0")) {
                            runningModif = false;
                            break;
                        }
                        int columnPosition = UtilsCSV.csvGetColumnPosition(csvLines, parametro);

                        if (columnPosition != -1) {
                            System.out.println("Introduce el nuevo valor: ");
                            String valor = llegirLinia("Valor: ");

                            UtilsCSV.update(csvLines, UtilsCSV.getLineNumber(csvLines, "id", opcio), parametro, valor);

                            UtilsCSV.write(filePath, csvLines);
                            System.out.println("Valor modificado correctamente");
                            runningModif = false;
                        } else {
                            System.out.println("Parametro no encontrado");
                        }
                    }
                } else {
                    System.out.println("ID no encontrada");
                }

            } catch (NumberFormatException e) {
                System.out.println("Introduce un número");
            } catch (Exception e) {
                System.out.println(e);
            }

        }
    }

    static public String llegirLinia(String text) {
        System.out.print(text);
        return in.nextLine();
    }
}
