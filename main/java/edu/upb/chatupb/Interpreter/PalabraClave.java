/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upb.chatupb.Interpreter;

/**
 *
 * @author rlaredo
 */
public class PalabraClave extends Expresion {
    protected String[] palabrasClave;

    public PalabraClave(String[] palabrasClave) {
        this.palabrasClave = palabrasClave;
    }

    @Override
    public boolean evalua(String descripcion) {
        for (String palabra : palabrasClave) {
            if (descripcion.indexOf(palabra) != -1) {
                return true;
            }
        }
        return false;
    }

    // parte análisis sintáctico
    public static Expresion parsea(String[] palabrasClave) throws Exception {
        Expresion resultado;
        resultado = new PalabraClave(palabrasClave);
        siguientePieza();
        return resultado;
    }
}
