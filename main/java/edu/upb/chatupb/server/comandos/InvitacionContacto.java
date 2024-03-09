/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upb.chatupb.server.comandos;

import edu.upb.chatupb.server.SocketClient;
import lombok.*;

/**
 *
 * @author Sarah
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvitacionContacto implements Comando {
    private String tipo;
    private String codigoPersona;
    private String nombre;
    private String mensaje;
    private String ip;
    private SocketClient socketClient;

    @Override
    public String tipoMensaje() {
         return this.tipo;
    }

    @Override
    public String generateCommand() {
        String comando = tipo + codigoPersona + String.format("%-" + 60 + "s", nombre) + mensaje + System.lineSeparator();
        return comando;
    }


    public static InvitacionContacto parseo(String message) {
        InvitacionContacto contacto = new InvitacionContacto();
        String comando = message.substring(0,3);
        String codigoPersona = message.substring(3,39);
        String nombre = message.substring(39,99);
        String mensaje= message.substring(99);


        contacto.tipo = comando;
        contacto.codigoPersona = codigoPersona;
        contacto.nombre = nombre;
        contacto.mensaje=mensaje;

        return contacto;
    }
    public InvitacionContacto ip(String ip){
        this.ip = ip;
        return this;
    }
    public InvitacionContacto socketClient (SocketClient socketClient){
        this.socketClient=socketClient;
        return this;
    }



}
