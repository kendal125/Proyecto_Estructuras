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
     * Constructor del grafo.
     */
    public Grafo() {
        adyacencia = new HashMap<>();
    }

    /**
     * Agrega un vértice al grafo.
     *
     * @param vertice identificador del vértice
     */
    public void agregarVertice(int vertice) {
        adyacencia.putIfAbsent(vertice, new ArrayList<>());
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

        adyacencia.get(origen).add(new Arista(destino, peso));
    }

    /**
     * Muestra el grafo en formato texto.
     *
     * @return representación del grafo
     */
    public String mostrarGrafo() {
        String resultado = "";

        for (int vertice : adyacencia.keySet()) {
            resultado += vertice + " -> ";

            for (Arista a : adyacencia.get(vertice)) {
                resultado += a.destino + "(" + a.peso + ") ";
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
                if (!visitados.contains(a.destino)) {
                    visitados.add(a.destino);
                    cola.add(a.destino);
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

        Map<Integer, Double> distancias = new HashMap<>();
        Map<Integer, Integer> previos = new HashMap<>();
        Set<Integer> visitados = new HashSet<>();

        // Inicializar distancias
        for (int v : adyacencia.keySet()) {
            distancias.put(v, Double.MAX_VALUE);
        }

        distancias.put(origen, 0.0);

        while (visitados.size() < adyacencia.size()) {

            int actual = -1;
            double minDist = Double.MAX_VALUE;

            // Buscar el nodo con menor distancia
            for (int v : distancias.keySet()) {
                if (!visitados.contains(v) && distancias.get(v) < minDist) {
                    minDist = distancias.get(v);
                    actual = v;
                }
            }

            if (actual == -1) break;

            visitados.add(actual);

            // Relajar aristas
            for (Arista a : adyacencia.get(actual)) {
                double nuevaDist = distancias.get(actual) + a.peso;

                if (nuevaDist < distancias.get(a.destino)) {
                    distancias.put(a.destino, nuevaDist);
                    previos.put(a.destino, actual);
                }
            }
        }

        return "Costo mínimo desde " + origen + " hasta " + destino + ": " + distancias.get(destino);
    }
}

/**
 * Clase que representa una arista del grafo.
 */
class Arista {

    /**
     * Nodo destino de la arista.
     */
    int destino;

    /**
     * Peso o costo de la arista.
     */
    double peso;

    /**
     * Constructor de la arista.
     *
     * @param destino nodo destino
     * @param peso peso de la arista
     */
    public Arista(int destino, double peso) {
        this.destino = destino;
        this.peso = peso;
    }
}