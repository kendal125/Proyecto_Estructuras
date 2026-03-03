/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clase_03_estructuras_datos;

/**
 *
 * @author Administrator
 */
public class InvertirNumero {
    
    public String invertir_recursivo1(int numero) {
        // Caso base
        if (numero < 10) {
            return String.valueOf(numero);
        }
        // Caso recursivo
        return (numero % 10) + invertir_recursivo1(numero / 10);
    }
    
    public String invertir_recursivo2(int numero) {
        // Caso base: un solo dígito
        if (numero < 10) {
            return numero + ""; // convierte a String sin usar valueOf
        }
        // Caso recursivo: último dígito + inverso del resto
        return (numero % 10) + "" + invertir_recursivo2(numero / 10);
    }
    
    public String invertir_iterativo(int numero) {
        String invertido = ""; // variable para construir el número invertido
        int digito = 0;

        while (numero > 0) {
            digito = numero % 10;           // obtiene el último dígito
            invertido = invertido + digito; // lo agrega al resultado
            numero = numero / 10;           // elimina el último dígito
        }

        return invertido;
    }
}
