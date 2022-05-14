package uabc.ic.benjaminbolanos.practica6;

import java.util.Scanner;

/**
 *
 * @author bbola
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Control c = new Control();
        Scanner scan = new Scanner(System.in);
        c.simular(new boolean[]{true, true, true, true, true, true, true});
        scan.nextLine();
        c.setArreglosPorAlgoritmos(5);
        c.simular(new boolean[]{true, true, false, true, true, true, true});
    }
    
}
