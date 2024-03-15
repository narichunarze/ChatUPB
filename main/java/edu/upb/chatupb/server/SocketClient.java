/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upb.chatupb.server;

import edu.upb.chatupb.event.SocketEvent;
import edu.upb.chatupb.server.comandos.*;
import lombok.ToString;

import javax.swing.*;
import javax.swing.event.EventListenerList;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Queue;

@ToString

/**
 *
 *
 * @author naric
 */
public class SocketClient extends Thread {

    private static final String C_INVITACION = "001";
    private static final String C_ACEPTACION = "002";
    private static final String C_CHAT = "003";
    private static final String C_EDITMESSAGE = "004";
    private static final String C_CHANGETHEME = "005";
    private static final String C_DELETEHISTORY = "006";
    private static final String C_SHARECONTACT = "007";
    private static final String C_SEARCH = "008";
    private static final String C_BUZZ = "009";
    private static final String C_REJECT = "010";


    private Socket socket;
    private SocketEvent listener;
    private String ip;
    private final EventListenerList listenerList = new EventListenerList();
    private final DataOutputStream dout;
    private Queue <Comando> comandos = new LinkedList<>();



    public SocketClient(Socket socket) throws IOException {
        this.socket = socket;
        this.ip = socket.getInetAddress().getHostAddress();
        dout = new DataOutputStream(socket.getOutputStream());
    }

    public SocketClient (String ip) throws IOException{
        this.socket = new Socket(ip,1900);
        this.ip = ip;
        dout = new DataOutputStream(socket.getOutputStream());
    }

    @Override
    public void run() {

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            String message;


            while ((message = br.readLine()) != null) {
                //Interpretar mensaje
                message = message.toString();
                System.out.println(message);
                String comando = message.substring(0, 3);
                System.out.println("comando");
                System.out.println(comando);
                switch (comando) {
                    case C_INVITACION -> {
                        System.out.println("comienzo socket");
                        Comando comandoInvitacion = InvitacionContacto.parseo(message).ip(ip).socketClient(this);
                        System.out.println( comandoInvitacion);
                        Mediador.sendEventIvited(comandoInvitacion);
                        System.out.println("llego a despues de evento ");
                    }

                    case C_ACEPTACION -> {
                        System.out.println("entro a aceptacion");
                        Comando comandoAceptar = AceptarInvitacion.parseo(message).ip(ip).socketClient(this);
                        Mediador.sendEventAccept(comandoAceptar);
                        System.out.println("despues de inseratar el contacto");
                    }


                    case C_CHAT -> {
                        System.out.println("entro al principio de chat");
                            try {

                                Comando comandoChatear = Chat.parseo(message);
                                System.out.println("comandoChatear");

                                System.out.println(comandoChatear);
                                Mediador.sendEventChat(comandoChatear);
                                System.out.println("entro HASTA CHAT FINAL");


                            }
                            catch (Exception e){
                                e.printStackTrace();
                                System.out.println(e.getMessage());
                            }




                    }

                    case C_EDITMESSAGE -> {
                        Comando comandoEditar = EditarMensaje.parseo(message);
                        Mediador.sendEventEditMessage(comandoEditar);
                    }


                    case C_CHANGETHEME -> {
                        Comando comandoCambiarTema = CambiarTema.parseo(message);
                        Mediador.sendEventChangeTheme(comandoCambiarTema);
                    }

                    case C_DELETEHISTORY -> {
                        System.out.println("llega a on delete");
                        Comando comandoBorrar = BorrarHistorial.parseo(message);
                        Mediador.sendEventDeleteHistory(comandoBorrar);
                    }


                    case C_SHARECONTACT -> {
                        Comando comandoPasarContacto = PasarContacto.parseo(message);
                        Mediador.sendEventShareContact(comandoPasarContacto);
                    }


                    case C_SEARCH -> {
                        Comando comandoBusqueda = Busqueda.parseo(message);
                        sendEventSearch(comandoBusqueda,ip);
                    }


                    case C_BUZZ -> {
                        Comando comandoZumbido = ZumbidoPantalla.parseo(message);
                        Mediador.sendEventBuzz(comandoZumbido);
                    }


                    case C_REJECT -> {
                        Comando comandoRechazar = RechazoInvitacion.parseo(message);
                        Mediador.sendEventIvited(comandoRechazar);
                    }


                    default -> {
                        System.out.println("Comando no reconocido" + comando);
                    }

                }
            }
        } catch (
                Exception e) {
//            e.printStackTrace();

        }

    }

    private void showInvitationDialog(String nombre, String mensajeInvitacion) {
        showDialog(nombre, mensajeInvitacion);
    }

    private void showDialog(String nombre, String mensajeInvitacion) {
        JOptionPane.showMessageDialog(null, mensajeInvitacion, nombre, JOptionPane.INFORMATION_MESSAGE);
    }

    public void addSocketEventListener(SocketEvent messageEvent) {
        listenerList.add(SocketEvent.class, messageEvent);
    }

    public void removeSocketEventListener(SocketEvent messageEvent) {
        listenerList.remove(SocketEvent.class, messageEvent);
    }

    private void sendEventIvited(Comando comando,String ipAddres) {
        Object[] listeners = listenerList.getListenerList();
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == SocketEvent.class) {
                ((SocketEvent) listeners[i + 1]).onInvited(comando,ipAddres);
                System.out.println("final evento");
                // System.out.println("Mensaje enviado a UI: " + comando.generateCommand());
            }
        }
    }

    private void sendEventAcceptInvited(Comando comando,String ipAddres) {
        Object[] listeners = listenerList.getListenerList();
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == SocketEvent.class) {
                ((SocketEvent) listeners[i + 1]).onAcceptedInvitation(comando,ipAddres);
                // System.out.println("Mensaje enviado a UI: " + comando.generateCommand());
            }
        }
    }

    private void sendEventRejectedInvited(Comando comando,String ipAddres) {
        Object[] listeners = listenerList.getListenerList();
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == SocketEvent.class) {
                ((SocketEvent) listeners[i + 1]).onRejectedInvitation(comando,ipAddres);
                // System.out.println("Mensaje enviado a UI: " + comando.generateCommand());
            }
        }
    }

    private void sendEvenChat(Comando comando) throws SQLException {
        Object[] listeners = listenerList.getListenerList();
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == SocketEvent.class) {
                ((SocketEvent) listeners[i + 1]).onChat(comando);
                // System.out.println("Mensaje enviado a UI: " + comando.generateCommand());
            }
        }
    }

    private void sendEventEditMessage(Comando comando,String ipAddres) throws SQLException {
        Object[] listeners = listenerList.getListenerList();
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == SocketEvent.class) {
                ((SocketEvent) listeners[i + 1]).onEditMessage(comando,ipAddres);
                // System.out.println("Mensaje enviado a UI: " + comando.generateCommand());
            }
        }
    }

    private void sendEventChangeTheme(Comando comando,String ipAddres) {
        Object[] listeners = listenerList.getListenerList();
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == SocketEvent.class) {
                ((SocketEvent) listeners[i + 1]).onChangeTheme(comando,ipAddres);
                // System.out.println("Mensaje enviado a UI: " + comando.generateCommand());
            }
        }
    }

    private void sendEventDeleteHistory(Comando comando,String ipAddres) {
        Object[] listeners = listenerList.getListenerList();
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == SocketEvent.class) {
                ((SocketEvent) listeners[i + 1]).onDeleteHistory(comando,ipAddres);
                // System.out.println("Mensaje enviado a UI: " + comando.generateCommand());
            }
        }
    }

    private void sendEventPassContact(Comando comando,String ipAddres) {
        Object[] listeners = listenerList.getListenerList();
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == SocketEvent.class) {
                ((SocketEvent) listeners[i + 1]).onPassContact(comando,ipAddres);
                // System.out.println("Mensaje enviado a UI: " + comando.generateCommand());
            }
        }
    }

    private void sendEventSearch(Comando comando,String ipAddres) {
        Object[] listeners = listenerList.getListenerList();
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == SocketEvent.class) {
                ((SocketEvent) listeners[i + 1]).onSearch(comando,ipAddres);
                // System.out.println("Mensaje enviado a UI: " + comando.generateCommand());
            }
        }
    }

    private void sendEventScreenBuzz(Comando comando,String ipAddres) {
        Object[] listeners = listenerList.getListenerList();
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == SocketEvent.class) {
                ((SocketEvent) listeners[i + 1]).onScreenBuzz(comando,ipAddres);
                // System.out.println("Mensaje enviado a UI: " + comando.generateCommand());
            }
        }
    }

    public void addComand (Comando comando){this.comandos.add(comando);}

    public void sendComands() throws Exception{
        if(dout == null || socket.isClosed()){
            throw new Exception("la conexion está cerrada");
        }

        while (!comandos.isEmpty()){
            Comando comando = this.comandos.poll();
            dout.write(comando.generateCommand().getBytes("UTF-8"));
            dout.flush();
        }
    }

    public String getIp() {
        return ip;
    }
}
