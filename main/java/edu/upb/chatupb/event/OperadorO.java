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
public class OperadorO extends OperadorBinario {

    public OperadorO(Expresion operandoIzquierdo,
                     Expresion operandoDerecho) {
        super(operandoIzquierdo, operandoDerecho);
    }

    public boolean evalua(String descripcion) {
        return operandoIzquierdo.evalua(descripcion)
                || operandoDerecho.evalua(descripcion);
    }

    // parte análisis sintáctico  
    public static Expresion parsea() throws Exception {
        Expresion resultadoIzquierdo, resultadoDerecho;
        resultadoIzquierdo = OperadorY.parsea();
        while ((pieza != null) && (pieza.equals("o"))) {
            siguientePieza();
            resultadoDerecho = OperadorY.parsea();
            resultadoIzquierdo = new OperadorO(resultadoIzquierdo,
                    resultadoDerecho);
        }
        return resultadoIzquierdo;
    }
}
