/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.clase_03_estructuras_datos;

import javax.swing.JOptionPane;

/**
 *
 * @author Administrator
 */
public class Clase_03_estructuras_datos {
    
    public static void ejercicio1_1() {
        // Tenemos una lista inicial
        int[] lista_numeros = {1, 2, 3, 4, 5};
        int resultado = 0;
        
        SumaRecursiva nuevaSuma = new SumaRecursiva();
        resultado = nuevaSuma.suma(lista_numeros, 0); // especificamos la lista y su indice 0 como parametros
        
        System.out.println("Resultado de la suma recursiva es: " + resultado);
    }
    
    public static void ejercicio1_2() {
        // Tenemos una lista inicial
        int[] lista_numeros = {3, 7, 9, 2, 5};
        int resultado = 0;
        
        SumaRecursiva nuevaSuma = new SumaRecursiva();
        resultado = nuevaSuma.obten_maximo(lista_numeros, 0); // especificamos la lista y su indice 0 como parametros
        
        System.out.println("El maximo, usando recursividad, es: " + resultado);
    }
    
    public static void ejercicio1_3() {
        // Tenemos una lista inicial
        int[] lista_numeros = {10, 20, 30, 40, 50};
        int resultado = 0;
        
        SumaRecursiva nuevaSuma = new SumaRecursiva();
        resultado = nuevaSuma.cuenta_elementos(lista_numeros, 0); // especificamos la lista y su indice 0 como parametros
        
        System.out.println("La cantidad de elementos, usando recursividad, es: " + resultado);
    }
    
    public static void ejercicio2() {
        Factorial nuevoFac = new Factorial();
        String entrada_tmp = "";
        int numero    = 0;
        int resultado_r = 0;
        int resultado_i = 0;
        
        entrada_tmp    = JOptionPane.showInputDialog("Ingrese un numero (calcular Factorial): ");
        numero = Integer.parseInt(entrada_tmp); // convertimos
        
        // calculamos el factorial, primero recursivo luego iterativo
        resultado_r = nuevoFac.calcular_recursivo(numero);
        resultado_i = nuevoFac.calcular_iterativo(numero);
        
        JOptionPane.showMessageDialog(null, "Factorial de " + numero + " (recursivo) es: " + resultado_r 
                + "; (iterativo) es: " + resultado_i);
    }
    
    public static void ejercicio3() {
        InvertirNumero nuevoInv = new InvertirNumero();
        String entrada_tmp = "";
        String resultado_r = "";
        String resultado_i = "";
        int numero    = 0;
        
        entrada_tmp    = JOptionPane.showInputDialog("Ingrese un numero para invertir: ");
        numero = Integer.parseInt(entrada_tmp); // convertimos
        
        resultado_r = nuevoInv.invertir_recursivo1(numero);
        resultado_i = nuevoInv.invertir_iterativo(numero);
        
        JOptionPane.showMessageDialog(null, "El numero: " + numero + " invertido es (recursivo): " + resultado_r 
                + "; (iterativo) es: " + resultado_i);
    }

    public static void main(String[] args) {
        ejercicio1_1();
        
        // ejercicio1_2();
        
        // ejercicio1_3();
        
        // ejercicio2();
        
        // ejercicio3();
    }
}
