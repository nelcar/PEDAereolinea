/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aerolinea;

import edu.uci.ics.jung.algorithms.shortestpath.DijkstraShortestPath;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;
import javax.xml.transform.Transformer;

/**
 *
 * @author Inspiron
 */
public class TDAGraph {

    SparseMultigraph<String, MyEdge> grafo;

    public TDAGraph() {
        grafo = new SparseMultigraph<String, MyEdge>();
        init();
    }

    private void init() {
        cargarVertices();
        cargarAristas();
    }

    private void cargarVertices() {
        try {
            File archivo = new File(".\\Vertices.txt");
            FileReader fileReader = new FileReader(archivo);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String temporal;
            while ((temporal = bufferedReader.readLine()) != null) {
                grafo.addVertex(temporal);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "No se pueden cargar los vertices");
        }
    }

    private void cargarAristas() {
        try {
            File archivo = new File(".\\Precio.txt");
            FileReader fileReader = new FileReader(archivo);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String temporal, desde, hacia, precio, aereolinea;
            StringTokenizer tokens = null;
            while ((temporal = bufferedReader.readLine()) != null) {
                tokens = new StringTokenizer(temporal, "@");
                while (tokens.hasMoreTokens()) {
                    aereolinea = tokens.nextToken();
                    desde = tokens.nextToken();
                    hacia = tokens.nextToken();
                    precio = tokens.nextToken();
                    grafo.addEdge(new MyEdge(Double.parseDouble(precio), aereolinea, desde, hacia), desde, hacia, EdgeType.DIRECTED);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            //JOptionPane.showMessageDialog(null, "No se pueden cargar los vertices");
        }
    }

    public ArrayList calcularCamino(Queue<String> ciudades) {//inicio calcular Camino
        ArrayList retVal = new ArrayList();
        org.apache.commons.collections15.Transformer<MyEdge, Double> optimusPrime = new org.apache.commons.collections15.Transformer<MyEdge, Double>() {
            public Double transform(MyEdge arista) {
                return arista.getPrecio();
            }
        };

        String tmp1, tmp2;
        tmp1 = ciudades.poll();
        tmp2 = ciudades.element();
        DijkstraShortestPath<String, MyEdge> dijkstra = new DijkstraShortestPath(grafo, optimusPrime);
        List<MyEdge> lista = dijkstra.getPath(tmp1, tmp2);

        retVal.add(lista);
        retVal.add(dijkstra.getDistance(tmp1, tmp2));

        while ((tmp1 = ciudades.poll()) != null && (tmp2 = ciudades.peek()) != null) {

            Number distancia = dijkstra.getDistance(tmp1, tmp2);
            ((List<MyEdge>) retVal.get(0)).addAll(dijkstra.getPath(tmp1, tmp2));
            retVal.set(1, (distancia.doubleValue() + Double.parseDouble(retVal.get(1).toString())));

        }
        return retVal;
    }//fin calcular Camino

}
