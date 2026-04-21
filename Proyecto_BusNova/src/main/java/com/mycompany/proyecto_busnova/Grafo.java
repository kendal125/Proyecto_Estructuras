/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_busnova;

import java.util.*;

/**
 * Clase que representa un grafo dirigido y ponderado.
 * Permite agregar vértices, aristas con peso, mostrar el grafo,
 * verificar conectividad y calcular la ruta más corta.
 */
public class Grafo {

    /**
     * Mapa de adyacencia que almacena los vértices y sus conexiones.
     */
    private Map<Integer, List<Arista>> adyacencia;
    
    /**
     * Diccionario para almacenar el nombre de cada localidad según su ID.
     */
    private Map<Integer, String> nombresLocalidades;

    /**
     * Constructor del grafo.
     */
    public Grafo() {
        adyacencia = new HashMap<>();
        nombresLocalidades = new HashMap<>();
    }
    
    

    /**
     * Agrega un vértice al grafo.
     *
     * @param vertice identificador del vértice
     * @param nombre nombre de la localidad 
     */
    public void agregarVertice(int vertice, String nombre) {
        adyacencia.putIfAbsent(vertice, new ArrayList<>());
        nombresLocalidades.put(vertice, nombre);
    }

    /**
     * Agrega una arista dirigida con peso entre dos vértices.
     *
     * @param origen vértice de origen
     * @param destino vértice destino
     * @param peso costo o peso de la arista
     */
    public void agregarArista(int origen, int destino, double peso) {
        adyacencia.putIfAbsent(origen, new ArrayList<>());
        adyacencia.putIfAbsent(destino, new ArrayList<>());
        
        nombresLocalidades.putIfAbsent(origen, "Localidad " + origen);
        nombresLocalidades.putIfAbsent(destino, "Localidad " + destino);

        adyacencia.get(origen).add(new Arista(destino, peso));
    }

    /**
     * Muestra el grafo en formato texto.
     *
     * @return representación del grafo
     */
    public String mostrarGrafo() {
        if (adyacencia.isEmpty()) {
            return "El grafo está vacío.";
        }

        String resultado = "Rutas de la Terminal:\n\n";

        for (int vertice : adyacencia.keySet()) {
            String nombreOrigen = nombresLocalidades.get(vertice);
            resultado += "[" + vertice + "] " + nombreOrigen + " -> ";

            List<Arista> conexiones = adyacencia.get(vertice);
            if (conexiones.isEmpty()) {
                resultado += "Sin rutas de salida";
            } else {
                for (Arista a : conexiones) {
                    String nombreDest = nombresLocalidades.get(a.getDestino());
                    resultado += nombreDest + " (Costo: " + a.getPeso() + ") | ";
                }
            }
            resultado += "\n";
        }

        return resultado;
    }

    /**
     * Verifica si existe conexión entre dos vértices usando BFS.
     *
     * @param origen vértice inicial
     * @param destino vértice objetivo
     * @return true si están conectados, false en caso contrario
     */
    public boolean estanConectados(int origen, int destino) {
        if (!adyacencia.containsKey(origen) || !adyacencia.containsKey(destino)) {
            return false;
        }

        Set<Integer> visitados = new HashSet<>();
        Queue<Integer> cola = new LinkedList<>();

        cola.add(origen);
        visitados.add(origen);

        while (!cola.isEmpty()) {
            int actual = cola.poll();

            if (actual == destino) {
                return true;
            }

            for (Arista a : adyacencia.get(actual)) {
                if (!visitados.contains(a.getDestino())) {
                    visitados.add(a.getDestino());
                    cola.add(a.getDestino());
                }
            }
        }

        return false;
    }

    /**
     * Calcula la ruta más corta entre dos vértices usando el algoritmo de Dijkstra.
     *
     * @param origen vértice inicial
     * @param destino vértice final
     * @return texto con el costo mínimo de la ruta
     */
    public String rutaMasCorta(int origen, int destino) {
        if (!adyacencia.containsKey(origen) || !adyacencia.containsKey(destino)) {
            return "Error: Origen o destino no existen en el grafo.";
        }

        Map<Integer, Double> distancias = new HashMap<>();
        Map<Integer, Integer> previos = new HashMap<>();
        Set<Integer> visitados = new HashSet<>();

        for (int v : adyacencia.keySet()) {
            distancias.put(v, Double.MAX_VALUE);
        }

        distancias.put(origen, 0.0);

        while (visitados.size() < adyacencia.size()) {

            int actual = -1;
            double minDist = Double.MAX_VALUE;

            for (int v : distancias.keySet()) {
                if (!visitados.contains(v) && distancias.get(v) < minDist) {
                    minDist = distancias.get(v);
                    actual = v;
                }
            }

            if (actual == -1) {
                break;
            }

            visitados.add(actual);

            for (Arista a : adyacencia.get(actual)) {
                double nuevaDist = distancias.get(actual) + a.getPeso();

                if (nuevaDist < distancias.get(a.getDestino())) {
                    distancias.put(a.getDestino(), nuevaDist);
                    previos.put(a.getDestino(), actual);
                }
            }
        }
        
        String nomOrigen = nombresLocalidades.get(origen);
        String nomDestino = nombresLocalidades.get(destino);

        return "Costo mínimo desde " + nomOrigen + " hasta " + nomDestino + ": " + distancias.get(destino);
    }

    // Getters
    public Map<Integer, List<Arista>> getAdyacencia() {
        return adyacencia;
    }
    
    public Map<Integer, String> getNombresLocalidades() {
        return nombresLocalidades;
    }
}
/**
 * Clase que representa una arista dentro de un grafo dirigido y ponderado.
 * <p>
 * Una arista conecta un vértice de origen con un vértice destino y posee
 * un peso asociado que representa el costo, distancia o valor de la conexión.
 * </p>
 * 
 * <p>
 * Esta clase es utilizada dentro de la estructura del grafo para almacenar
 * las conexiones entre localidades y sus respectivos costos.
 * </p>
 * 
 * <p>
 * Incluye un constructor vacío para permitir la serialización y deserialización
 * de objetos mediante librerías como Jackson.
 * </p>
 */
class Arista {

    /**
     * Identificador del vértice destino al que apunta la arista.
     */
    private int destino;

    /**
     * Peso o costo asociado a la arista.
     * Puede representar distancia, tiempo o cualquier métrica definida.
     */
    private double peso;

    /**
     * Constructor vacío.
     * <p>
     * Requerido para procesos de deserialización desde archivos JSON.
     * </p>
     */
    public Arista() {
    }

    /**
     * Constructor con parámetros.
     *
     * @param destino vértice destino de la arista
     * @param peso peso o costo de la conexión
     */
    public Arista(int destino, double peso) {
        this.destino = destino;
        this.peso = peso;
    }

    /**
     * Obtiene el vértice destino de la arista.
     *
     * @return identificador del vértice destino
     */
    public int getDestino() {
        return destino;
    }

    /**
     * Establece el vértice destino de la arista.
     *
     * @param destino nuevo vértice destino
     */
    public void setDestino(int destino) {
        this.destino = destino;
    }

    /**
     * Obtiene el peso o costo de la arista.
     *
     * @return peso de la conexión
     */
    public double getPeso() {
        return peso;
    }

    /**
     * Establece el peso o costo de la arista.
     *
     * @param peso nuevo valor del peso
     */
    public void setPeso(double peso) {
        this.peso = peso;
    }

    /**
     * Devuelve una representación en texto de la arista.
     *
     * @return String con destino y peso
     */
    @Override
    public String toString() {
        return "Destino: " + destino + " | Peso: " + peso;
    }
}