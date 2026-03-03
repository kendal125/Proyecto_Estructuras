/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clase_03_estructuras_datos;

/**
 *
 * @author Administrator
 */
public class Factorial {
    
    public int calcular_recursivo(int numero) {        
	if (numero == 1) { 
            // Caso base: cuando n es 1            
            return 1;        
	} else {    
            return numero * calcular_recursivo(numero - 1); // Llamada recursiva        
	}    
    }
    
    public int calcular_iterativo(int numero) {
        int resultado = 1;

        for (int i = 1; i <= numero; i++) {
            resultado *= i;
        }

        return resultado;
    }
    
}
