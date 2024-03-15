/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upb.chatupb;

import edu.upb.chatupb.ui.ChatUI;

import java.io.DataOutputStream;
import java.net.Socket;
import java.util.UUID;

/**
 *
 * @author rlaredo
 */


public class ClientePrueba {

    public static void main(String[] arg) {
        try {
            Socket socket = new Socket("172.16.77.58", 1900); //Lab1
           //Socket socket = new Socket("127.0.0.1", 1900); //casa
            //Socket socket = new Socket("172.16.76.111", 1900); //A1

            System.out.println("Cliente conectado...");
            //String ip="127.0.0.1";
//192.168.37.126

            DataOutputStream dout = new DataOutputStream(socket.getOutputStream());

            UUID uuidPersona = UUID.randomUUID();
            String longitudMensaje = String.format("000008");
            UUID uuidMensaje = UUID.randomUUID();
            String nombre = String.format("%-" + 60 + "s", "Prueba6");
            String idPropio="4496f66e-2893-4b56-a923-53c45fa64a14";
            ///String mensaje = "001"+uuidPersona+String.format("%-" + 60 + "s", "Prueba2")+"ahora si?"+"aljbd" +System.lineSeparator();
          //String mensaje = "003"+uuidMensaje.toString()+idPropio+"Como esta el clima en Santa Cruz?"+System.lineSeparator();
           //String mensaje = "005"+"1"+uuidPersona+System.lineSeparator();
            String mensaje="004"+"b2097e0e-d685-4f3a-87e3-db9e2cdfe6d5"+"mensaje editado2"+System.lineSeparator();
            System.out.println(mensaje);
            dout.write(mensaje.getBytes("UTF-8"));
            dout.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
