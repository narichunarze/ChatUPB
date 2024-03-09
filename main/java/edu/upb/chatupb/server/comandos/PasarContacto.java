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
public class PasarContacto implements Comando {
    private String tipo;
    private String codigoPersona;
    private String nombre;
    private String ipOtraPersona;

    @Override
    public String tipoMensaje() {
        return this.tipo;
    }

    @Override
    public String generateCommand() {
        String comando = tipo + codigoPersona + String.format("%-" + 60 + "s", nombre) + ipOtraPersona + System.lineSeparator();
        return comando;
    }

    public static PasarContacto parseo(String message) {
        PasarContacto contacto = new PasarContacto();
        String comando = message.substring(0,3);
        String codigoPersona = message.substring(3,39);
        String nombre = message.substring(39,99);
        String ipOtraPersona = message.substring(99);

        contacto.tipo = comando;
        contacto.codigoPersona = codigoPersona;
        contacto.nombre = nombre;
        contacto.ipOtraPersona = ipOtraPersona;

        return contacto;
    }



}
