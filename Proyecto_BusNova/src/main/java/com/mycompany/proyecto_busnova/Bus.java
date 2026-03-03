/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_busnova;

/**
 *
 * @author lopez
 */
public class Bus {

    private int id;
    private char tipo; // P = Preferencial, D = Directo, N = Normal
    private Cola cola;
    private boolean inspectorOcupado;

    public Bus(int id, char tipo) {
        this.id = id;
        this.tipo = tipo;
        this.cola = new Cola();
        this.inspectorOcupado = false;
    }

    public int getId() {
        return id;
    }

    public char getTipo() {
        return tipo;
    }

    public Cola getCola() {
        return cola;
    }

    public boolean isInspectorOcupado() {
        return inspectorOcupado;
    }

    public void setInspectorOcupado(boolean inspectorOcupado) {
        this.inspectorOcupado = inspectorOcupado;
    }
}
