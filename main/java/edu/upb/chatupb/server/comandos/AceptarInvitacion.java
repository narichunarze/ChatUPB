/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upb.chatupb.server.comandos;

import edu.upb.chatupb.server.SocketClient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

/**
 *
 * @author naric
 */
public class AceptarInvitacion implements Comando {
    private String tipo;
    private String codigoPersona;
    private String nombre;
    private String ip;
    private SocketClient socketClient;

    @Override
    public String tipoMensaje() {
        return tipo;
    }

    @Override
    public String generateCommand() {
        String comando = tipo + codigoPersona + String.format("%-" + 60 + "s", nombre)  + System.lineSeparator();
        return comando;
    }

    public static AceptarInvitacion parseo(String message) {
        AceptarInvitacion contacto = new AceptarInvitacion();
        String comando = message.substring(0,3);
        String codigoPersona = message.substring(3,39);
        String nombre = message.substring(39,99);
        String ip = message.substring(99);


        contacto.tipo = comando;
        contacto.codigoPersona = codigoPersona;
        contacto.nombre = nombre;
        contacto.ip = ip;

        return contacto;
    }
    public AceptarInvitacion ip(String ip){
        this.ip = ip;
        return this;
    }
    public AceptarInvitacion socketClient (SocketClient socketClient){
        this.socketClient=socketClient;
        return this;
    }
}
