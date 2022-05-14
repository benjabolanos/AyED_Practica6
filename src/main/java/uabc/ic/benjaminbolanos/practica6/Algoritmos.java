package uabc.ic.benjaminbolanos.practica6;

import java.util.Arrays;

/**
 *
 * @author benjabolanos
 */
public class Algoritmos {
    
    public int quicksortNS(int a[]){
        recursivoNS(a, 0, a.length-1);
        return 0;
    }
    
    public void recursivoNS(int a[], int inicio, int fin)
    {
        int i, j, central; 
        double pivote;
        central = (inicio + fin) / 2;
        pivote = a[central];
        i = inicio;
        j = fin;
        do {
            while (a[i] < pivote) i++;
            while (a[j] > pivote) j--;
            if (i <= j) {
                int aux = a[i];
                a[i] = a[j];
                a[j] = aux;
                i++;
                j--;
            }
        } while (i <= j);
        if (inicio < j)
            recursivo(a, inicio, j);
        if (i < fin)
            recursivo(a, i, fin);
    }
    
    public static int quicksort(int a[]){
        recursivo(a, 0, a.length-1);
        return 0;
    }
    
    public static void recursivo(int a[], int inicio, int fin)
    {
        int i, j, central; 
        double pivote;
        central = (inicio + fin) / 2;
        pivote = a[central];
        i = inicio;
        j = fin;
        do {
            while (a[i] < pivote) i++;
            while (a[j] > pivote) j--;
            if (i <= j) {
                int aux = a[i];
                a[i] = a[j];
                a[j] = aux;
                i++;
                j--;
            }
        } while (i <= j);
        if (inicio < j)
            recursivo(a, inicio, j);
        if (i < fin)
            recursivo(a, i, fin);
    }

    public static int insercion(int[] arreglo) {
        for (int i = 1; i < arreglo.length; i++) {
            int aux = arreglo[i], k = i - 1;
            while (k >= 0 && aux < arreglo[k]) {
                arreglo[k + 1] = arreglo[k];
                k--;
            }
            arreglo[k + 1] = aux;
        }
        return 0;
    }

    public static int seleccion(int[] arreglo) {
        for (int i = 0; i < arreglo.length; i++) {
            int menor = arreglo[i], k = i;
            for (int j = i + 1; j < arreglo.length; j++) {
                if (arreglo[j] < menor) {
                    menor = arreglo[j];
                    k = j;
                }
            }
            arreglo[k] = arreglo[i];
            arreglo[i] = menor;
        }
        return 0;
    }

    public static int shell(int[] arreglo) {
        int inter = arreglo.length + 1;
        while (inter > 0) {
            inter = (int) Math.floor(inter / 2);
            boolean b = true;
            while (b) {
                b = false;
                int i = 0;
                while (i + inter < arreglo.length) {
                    if (arreglo[i] > arreglo[i + inter]) {
                        int aux = arreglo[i];
                        arreglo[i] = arreglo[i + inter];
                        arreglo[i + inter] = aux;
                        b = true;
                    }
                    i++;
                }
            }
        }
        return 0;
    }

    public static int bucketsort(int[] array) {
        int max = max(array);
        int[] buckets = new int[max + 1];
        
        for(int i = 0; i < array.length; i++){
            buckets[array[i]]++;
        }
        
        int index = 0;
        for(int i = 0; i < buckets.length; i++){
            for(int j = 0; j < buckets[i]; j++){
                array[index] = i;
            }
        }
        return 0;
    }

    private static int max(int[] array) {
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }

    public static int radixsort(int[] a) {
        int[][] bucket = new int[10][a.length];
        int[] bucketOfElement = new int[10];
        int max = max(a);
        int maxLength = (max + "").length();
        for (int m = 0, n = 1; m < maxLength; m++, n *= 10) {
            for (int i = 0; i < a.length; i++) {
                int digit = a[i] / n % 10;
                bucket[digit][bucketOfElement[digit]] = a[i];
                bucketOfElement[digit]++;
            }
            int index = 0;
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < bucketOfElement[j]; k++) {
                    a[index] = bucket[j][k];
                    index++;
                }
                bucketOfElement[j] = 0;
            }
        }
        return 0;
    }
    
    public static int arraySort(int[] a){
        Arrays.sort(a);
        return 0;
    }
}
