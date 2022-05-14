package uabc.ic.benjaminbolanos.practica6;

import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author benjabolanos
 */
public class Control {

    private final int CANTIDAD_ALGORITMOS = 7;
    private int arreglosPorAlgoritmo;
    private int[][][] arrays;
    private final Simulacion simulacion = new Simulacion();
    private final String[] NOMBRES_ALGORITMOS = new String[]{"Insercion",
        "Quicksort", "Seleccion", "Shell", "Bucketsort", "RadixSort", "Sort"};
    private double[][] tiempos;

    public Control() {
        this.arreglosPorAlgoritmo = 6;
        reiniciarArreglos();
    }

    /**
     * Método para simular los algoritmos seleccionados, obteniendo los tiempos
     * graficandolos y creando archivos para guardarlos.
     *
     * @param algoritmosSeleccionados Booleanos que indican cuales algoritmos
     * simular
     */
    public void simular(boolean[] algoritmosSeleccionados) {
        System.out.println("Iniciando simulacion con " + arreglosPorAlgoritmo + "arreglos por algoritmo.");
        reiniciarArreglos();
        tiempos = simulacion.simular(arrays);
        crearArchivo(algoritmosSeleccionados);
        graficar(algoritmosSeleccionados);
        mostrarResultados(algoritmosSeleccionados);
        System.out.println("\nSimulación finalizada.");
    }

    /**
     * Método para reiniciar arreglos de numeros aleatorios.
     */
    private void reiniciarArreglos() {
        Random r = new Random();
        arrays = new int[CANTIDAD_ALGORITMOS][arreglosPorAlgoritmo][];
        for (int j = 0, i = 100; j < arrays[0].length; j++, i *= 5) {
            arrays[0][j] = new int[i];
            for (int k = 0; k < arrays[0][j].length; k++) {
                arrays[0][j][k] = r.nextInt(100);
            }
        }
        for (int i = 1; i < arrays.length; i++) {
            arrays[i] = arrays[0].clone();
        }
    }

    /**
     * Método para mostrar los resultados de la simulación en la consola.
     * @param algoritmosSeleccionados Booleanos que indican los algoritmos de los
     * cuales se motrarán resultados.
     */
    public void mostrarResultados(boolean[] algoritmosSeleccionados) {
        for (int i = 0; i < NOMBRES_ALGORITMOS.length; i++) {
            if(algoritmosSeleccionados[i]){
                System.out.println("Tiempos de " + NOMBRES_ALGORITMOS[i]);
                for (int j = 0; j < tiempos[i].length; j++) {
                    System.out.println("\tArreglo " + j + ": " + tiempos[i][j]);
                }
                System.out.println();
            }
        }
    }

    /**
     * Método para graficar los resultados de los algoritmos seleccionados
     *
     * @param algoritmosSeleccionados Booleanos que indican cuales algoritmos
     * graficar
     */
    public void graficar(boolean[] algoritmosSeleccionados) {
        Grafica grafica = new Grafica();
        grafica.crearGrafica(algoritmosSeleccionados);
    }

    /**
     * Método para crear archivos de tipo CSV para guardar los tiempos de
     * ordenamiento de los algoritmos seleccionados.
     *
     * @param algoritmosSeleccionados Booleanos que indican de cuales algoritmos
     * guardar sus datos en aun archivo de tipo CSV.
     */
    public void crearArchivo(boolean[] algoritmosSeleccionados) {
        for (int j = 0; j < CANTIDAD_ALGORITMOS; j++) {
            if (algoritmosSeleccionados[j]) {
                try ( CSVWriter writer = new CSVWriter(new FileWriter("src/main/resources/csv/" + NOMBRES_ALGORITMOS[j] + ".csv"))) {
                    for (int i = 0, n = 100; i < arreglosPorAlgoritmo; i++, n *= 5) {
                        writer.writeNext(new String[]{String.valueOf(i), String.valueOf(n), String.valueOf(tiempos[j][i])}, false);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    /**
     * Metódo para cambiar la cantidad de arreglos que se simularán por
     * algoritmo.
     *
     * @param cantidad Nueva cantidad de arreglos por algoritmo.
     */
    public void setArreglosPorAlgoritmos(int cantidad) {
        this.arreglosPorAlgoritmo = cantidad;
    }
}
