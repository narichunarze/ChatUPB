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
public class EditarMensaje implements Comando {
    private String tipo;
    private String codigoMensaje;
    private String mensaje;

    @Override
    public String tipoMensaje() {
        return this.tipo;
    }

    @Override
    public String generateCommand() {
        String comando = tipo + codigoMensaje + mensaje + System.lineSeparator();
        return comando;
    }

    public static EditarMensaje parseo(String message) {
        EditarMensaje contacto = new EditarMensaje();
        String comando = message.substring(0,3);
        String codigoMensaje = message.substring(3,39);
        String mensaje = message.substring(39);

        contacto.tipo = comando;
        contacto.codigoMensaje = codigoMensaje;
        contacto.mensaje = mensaje;

        return contacto;
    }




}
