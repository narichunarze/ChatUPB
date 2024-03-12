/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upb.chatupb.event;

import edu.upb.chatupb.Interpreter.Expresion;

/**
 *
 * @author rlaredo
 */
public class OperadorY extends OperadorBinario {

    public OperadorY(Expresion operandoIzquierdo,
                     Expresion operandoDerecho) {
        super(operandoIzquierdo, operandoDerecho);
    }

    public boolean evalua(String descripcion) {
        return operandoIzquierdo.evalua(descripcion)
                && operandoDerecho.evalua(descripcion);
    }

    // parte análisis sintáctico  
    public static Expresion parsea() throws Exception {
        Expresion resultadoIzquierdo, resultadoDerecho;
        resultadoIzquierdo = Expresion.parsea();
        while ((pieza != null) && (pieza.equals("y"))) {
            siguientePieza();
            resultadoDerecho = Expresion.parsea();
            resultadoIzquierdo = new OperadorY(
                    resultadoIzquierdo,
                    resultadoDerecho);
        }
        return resultadoIzquierdo;
    }
}
