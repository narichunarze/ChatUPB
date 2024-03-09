/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upb.chatupb.server;

import edu.upb.chatupb.event.SocketEvent;
import edu.upb.chatupb.server.comandos.Comando;

import javax.swing.event.EventListenerList;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 *
 * @author rlaredo
 */
public class Mediador extends Thread {



    private static final Map<String, SocketClient> clients = new HashMap<>();
    private static final Queue<MessageMed> MESSAGE_MEDS = new LinkedList<>();

    private static final EventListenerList listenerList = new EventListenerList();

    @Override
    public void run() {
        while (true) {
            synchronized (MESSAGE_MEDS) {
                if (MESSAGE_MEDS.isEmpty()) {
                    try {
                        this.MESSAGE_MEDS.wait();
                    } catch (Exception e) {
                        e.printStackTrace();
                        continue;
                    }
                }

                MessageMed messageMed = MESSAGE_MEDS.poll();
                SocketClient sc = null;

                try {
                    if (!clients.containsKey(messageMed.getIp())) {
                        sc = new SocketClient(messageMed.getIp());
                        sc.start();
                        clients.put(messageMed.getIp(), sc);
                    } else {
                        sc = clients.get(messageMed.getIp());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    continue;
                }

                sc.addComand(messageMed.getCommand());
                try {
                    sc.sendComands();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public synchronized static void sendMessage(String ip, Comando command) {
        synchronized (MESSAGE_MEDS) {
            MESSAGE_MEDS.add(new MessageMed(ip, command));
            MESSAGE_MEDS.notify();
        }
    }

    public static void addClient(SocketClient client) {
        clients.put(client.getIp(), client);
    }

    public static void addSocketEventListener(SocketEvent messageEvent) {
        listenerList.add(SocketEvent.class, messageEvent);
    }

    public static void removeSocketEventListener(SocketEvent messageEvent) {
        listenerList.remove(SocketEvent.class, messageEvent);
    }

    public static void sendEventIvited(Comando comando) {
        Object[] listeners = listenerList.getListenerList();
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == SocketEvent.class) {
                SocketEvent event = (SocketEvent) listeners[i + 1];
                
                java.awt.EventQueue.invokeLater(() -> {
                    event.onInvited(comando,"");
                });
                
            }
        }
    }

    public static void sendEventAccept(Comando comando) {
        Object[] listeners = listenerList.getListenerList();
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == SocketEvent.class) {
                SocketEvent event = (SocketEvent) listeners[i + 1];
                java.awt.EventQueue.invokeLater(() -> {
                    event.onAcceptedInvitation(comando,"");
                });
            }
        }
    }

    public static void sendEventChat(Comando comando) {
        Object[] listeners = listenerList.getListenerList();
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == SocketEvent.class) {
                SocketEvent event = (SocketEvent) listeners[i + 1];
                java.awt.EventQueue.invokeLater(() -> {
                    try {
                        event.onChat(comando);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });
            }
        }
    }

    public static void sendEventEditMessage(Comando comando) {
        Object[] listeners = listenerList.getListenerList();
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == SocketEvent.class) {
                SocketEvent event = (SocketEvent) listeners[i + 1];
                java.awt.EventQueue.invokeLater(() -> {
                    event.onEditMessage(comando,"");
                });
            }
        }
    }

    public static void sendEventChangeTheme(Comando comando) {
        Object[] listeners = listenerList.getListenerList();
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == SocketEvent.class) {
                SocketEvent event = (SocketEvent) listeners[i + 1];
                java.awt.EventQueue.invokeLater(() -> {
                    event.onChangeTheme(comando,"");
                });
            }
        }
    }

    public static void sendEventDeleteHistory(Comando comando) {
        Object[] listeners = listenerList.getListenerList();
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == SocketEvent.class) {
                SocketEvent event = (SocketEvent) listeners[i + 1];
                java.awt.EventQueue.invokeLater(() -> {
                    event.onDeleteHistory(comando,"");
                });
            }
        }
    }

    public static void sendEventShareContact(Comando comando) {
        Object[] listeners = listenerList.getListenerList();
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == SocketEvent.class) {
                SocketEvent event = (SocketEvent) listeners[i + 1];
                java.awt.EventQueue.invokeLater(() -> {
                    event.onPassContact(comando,"");
                });
            }
        }
    }

    public static void sendEventSearch(Comando comando) {
        Object[] listeners = listenerList.getListenerList();
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == SocketEvent.class) {
                SocketEvent event = (SocketEvent) listeners[i + 1];
                java.awt.EventQueue.invokeLater(() -> {
                    event.onSearch(comando,"");
                });
            }
        }
    }

    public static void sendEventBuzz(Comando comando) {
        Object[] listeners = listenerList.getListenerList();
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == SocketEvent.class) {               
                SocketEvent event = (SocketEvent) listeners[i + 1];
                java.awt.EventQueue.invokeLater(() -> {
                    event.onScreenBuzz(comando,"");
                });
            }
        }
    }

    public static void sendEventRefuseRequest(Comando comando) {
        Object[] listeners = listenerList.getListenerList();
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == SocketEvent.class) {
                SocketEvent event = (SocketEvent) listeners[i + 1];
                java.awt.EventQueue.invokeLater(() -> {
                    event.onRejectedInvitation(comando,"");
                });
            }
        }
    }

}
