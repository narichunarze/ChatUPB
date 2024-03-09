package edu.upb.chatupb.server.objects;

import edu.upb.chatupb.server.SocketClient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
@Builder
public class Contacto {
    String id;
    String name;
    String ip;
    private boolean isStateConnect = false;
    private SocketClient socketClient;

    @Override
    public String toString(){return name;}

}
