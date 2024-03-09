package edu.upb.chatupb.server.comandos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Busqueda implements Comando {
    private String tipo;
    private String respuesta;

    @Override
    public String tipoMensaje() {
        return this.respuesta;
    }

    @Override
    public String generateCommand() {
        String comando = tipo + respuesta + System.lineSeparator();
        return comando;
    }

    public static Comando parseo(String message) {
        return new Busqueda(message.substring(2,5),
                message.substring(5));
    }
}