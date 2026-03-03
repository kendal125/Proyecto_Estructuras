/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clase_03_estructuras_datos;

/**
 *
 * @author Administrator
 */
public class SumaRecursiva {
    
    public int suma(int[] lista, int indice) {
        // Caso base
        if (indice == lista.length) {
            return 0;
        }
        // Caso recursivo
        return lista[indice] + suma(lista, indice + 1);
    }
    
    public int obten_maximo(int[] lista, int indice) {
        int maxDelResto = 0;
        
        // Caso base: último elemento
        if (indice == lista.length - 1) {
            return lista[indice];
        }

        // Caso recursivo
        maxDelResto = obten_maximo(lista, indice + 1);
        
        return Math.max(lista[indice], maxDelResto);
    }
    
    public int cuenta_elementos(int[] lista, int indice) {
        // Caso base: se llegó al final del arreglo
        if (indice == lista.length) {
            return 0;
        }

        // Caso recursivo
        return 1 + cuenta_elementos(lista, indice + 1);
    }

}
