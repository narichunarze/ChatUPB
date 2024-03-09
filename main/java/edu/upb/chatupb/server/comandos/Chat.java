/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upb.chatupb.server.comandos;

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
 * @author Sarah
 */
public class Chat implements Comando {
    private String tipo;
    private String codigoMensaje;
    private String codigoPersona;
    private String mensaje;
    
    //@Override
    public String tipoMensaje() {
        return this.tipo;
    }

    @Override
    public String generateCommand() {
        String comando = tipo + codigoMensaje + codigoPersona + mensaje + System.lineSeparator();
        return comando;
    }

    public static Chat parseo(String message) {
        Chat contacto = new Chat();
        String comando = message.substring(0,3);
        String codigoMensaje = message.substring(3,39);
        String codigoPersona = message.substring(39,75);
        String mensaje = message.substring(75);

        contacto.tipo = comando;
        contacto.codigoMensaje = codigoMensaje;
        contacto.codigoPersona = codigoPersona;
        contacto.mensaje = mensaje;
        System.out.println("estoy en parseo chat");
        return contacto;
    }


}
