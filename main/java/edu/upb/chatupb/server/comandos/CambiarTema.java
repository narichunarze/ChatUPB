/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upb.chatupb.server.comandos;

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
public class CambiarTema implements Comando {
    private String tipo;
    private String codigoTema; //0-3.
    private String codigoPersonaOrg;


    @Override
    public String tipoMensaje() {
        return tipo;
    }

    @Override
    public String generateCommand() {
        String comando = tipo + codigoTema + codigoPersonaOrg + System.lineSeparator();
        return comando;
    }

    public static CambiarTema parseo(String message) {
        CambiarTema contacto = new CambiarTema();
        String comando = message.substring(0,3);
        String codigoTema = message.substring(3,4);
        String codigoPersonaOrg = message.substring(4);

        contacto.tipo = comando;
        contacto.codigoTema = codigoTema;
        contacto.codigoPersonaOrg = codigoPersonaOrg;

        return contacto;
    }
    
}
