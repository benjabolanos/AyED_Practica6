package uabc.ic.benjaminbolanos.practica6;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 *
 * @author benjabolanos
 */
public class Simulacion {
    private ArrayList<Consumer<int[]>> listadoAlgoritmos;
    
    public Simulacion(){
        enlistarAlgoritmos();
    }
    
    /**
     * Método para guardar la referencia de los algoritmos de ordenamiento.
     */
    private void enlistarAlgoritmos(){
        listadoAlgoritmos = new ArrayList<>(List.of(
                Algoritmos::insercion, Algoritmos::quicksort,
                Algoritmos::seleccion, Algoritmos::shell, 
                Algoritmos::bucketsort,Algoritmos::radixsort, 
                Algoritmos::arraySort));
    }
    
    /**
     * Método que ordena los elementos de cada array con las funciones de
     * listadoAlgoritmos y guarda los tiempos que tardan los arreglos en ordenarse
     * en un array de tipo long.
     * @param arr Array con elementos a ordenar.
     * @return Tiempos que tardó cada arreglo en ordenarse.
     */
    public double[][] simular(int[][][] arr){
        //arr.length = cantidad de algoritmos
        //arr[0].length = cantidad de arreglos a ordenar
        //en cada espacio se guarda el tiempo que tarde cada arreglo en ordenarse
        double[][] tiempos = new double[arr.length][arr[0].length];
        
        //Correr simulacion por cada algoritmo en cada arreglo
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr[i].length; j++){
                long inicio = System.nanoTime();
                listadoAlgoritmos.get(i).accept(arr[i][j]);
                long fin = System.nanoTime();
                tiempos[i][j] = fin - inicio;
            }
            //arr[0] quicksort
        }
        return tiempos;
    }
}
