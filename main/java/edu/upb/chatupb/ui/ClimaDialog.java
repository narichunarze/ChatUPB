package edu.upb.chatupb.ui;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ClimaDialog {
    private static final List<String> respuestasClima = new ArrayList<>();

    static {
        // Agrega las posibles respuestas de clima a la lista
        respuestasClima.add("soleado");
        respuestasClima.add("lluvioso");
        respuestasClima.add("nublado");
        respuestasClima.add("ventoso");
        respuestasClima.add("Todas las anteriores");
    }

    public static boolean contienePalabraClima(String mensaje) {
        return mensaje.toLowerCase().contains("clima");
    }

    public static String obtenerRespuestaClimaAleatoria() {
        // Obtén una respuesta aleatoria de la lista de respuestas de clima

        Random random = new Random();
        int index = random.nextInt(respuestasClima.size());
        return respuestasClima.get(index);
    }

    public static void mostrarDialogoClima() {
        // Obtén una respuesta aleatoria de la lista de respuestas de clima
        String respuestaClima = obtenerRespuestaClimaAleatoria();

        // Muestra un diálogo con la respuesta del clima
        JOptionPane.showMessageDialog(null, "El clima en Santa Cruz es: " + respuestaClima);
    }

    public static void main(String[] args) {
        // Ejemplo de cómo utilizar el método para verificar si un mensaje contiene la palabra "clima"
        String mensaje = "Hoy vamos a hablar del clima";
        if (contienePalabraClima(mensaje)) {
            mostrarDialogoClima();
        }
    }
}
