package uabc.ic.benjaminbolanos.practica6;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.apache.commons.lang3.ArrayUtils;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.DefaultXYDataset;

/**
 *
 * @author benjabolanos
 */
public class Grafica {

    private final DefaultXYDataset dataset;
    private final XYLineAndShapeRenderer renderer;
    private double[] rangosN;
    private int contadorSeries;

    public Grafica() {
        dataset = new DefaultXYDataset();
        renderer = new XYLineAndShapeRenderer();
        contadorSeries = 0;
    }

    private void obtenerDatos(boolean[] algoritmosSeleccionados) {
        Color[] colores = new Color[]{Color.BLUE, Color.BLACK, Color.GREEN,
            Color.RED, Color.YELLOW, Color.MAGENTA, Color.ORANGE};
        String[] NOMBRES_ALGORITMOS = new String[]{"Insercion",
            "Quicksort", "Seleccion", "Shell", "Bucketsort", "RadixSort", "Sort"};

        for (int i = 0; i < 7; i++) {
            if (algoritmosSeleccionados[i]) {
                try ( CSVReader reader = new CSVReader(new FileReader("src/main/resources/csv/" + NOMBRES_ALGORITMOS[i] + ".csv"))) {
                    String[] valores;
                    ArrayList<Double> valoresDouble = new ArrayList();
                    while ((valores = reader.readNext()) != null) {
                        valoresDouble.add(Double.parseDouble(valores[2]));
                    }
                    agregarSerie(
                            (double[]) ArrayUtils.toPrimitive(valoresDouble.toArray(new Double[0])), //ArrayList a double[]
                            NOMBRES_ALGORITMOS[i],
                            colores[i]);
                } catch (FileNotFoundException e) {
                    System.out.println("Error al leer archivo CSV.");
                } catch (IOException | CsvValidationException ex) {
                    Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    private double[] crearRangos(int n) {
        double[] rangos = new double[n];
        for (int i = 100, j = 0; j < n; j++, i *= 5) {
            rangos[j] = i;
        }
        return rangos;
    }

    private void agregarSerie(double[] tiempos, String nombre, Color color) {
        rangosN = crearRangos(tiempos.length);
        dataset.addSeries(nombre, new double[][]{tiempos, rangosN});
        renderer.setSeriesPaint(contadorSeries, color);
        renderer.setSeriesStroke(contadorSeries, new BasicStroke(2));
        contadorSeries++;
    }

    public void crearGrafica(boolean[] b) {
        obtenerDatos(b);
        JFreeChart grafica = ChartFactory.createXYLineChart(
                "Practica 6",
                "Tiempo en Nanosegundos",
                "N", dataset,
                PlotOrientation.HORIZONTAL,
                true,
                true,
                true
        );
        grafica.getXYPlot().getRangeAxis().setRange(0, rangosN[rangosN.length - 1]);
        grafica.getXYPlot().setRenderer(renderer);

        BufferedImage image = grafica.createBufferedImage(800, 440);
        try {
            ImageIO.write(image, "png", new File("src/main/resources/imagenes/grafica.png"));
        } catch (IOException ex) {
            Logger.getLogger(Grafica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
