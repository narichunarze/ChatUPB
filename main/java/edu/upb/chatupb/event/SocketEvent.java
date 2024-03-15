/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package edu.upb.chatupb.event;

import edu.upb.chatupb.server.comandos.Comando;

import java.sql.SQLException;
import java.util.EventListener;

/**
 * @author Sarah
 */
public interface SocketEvent extends EventListener {

    void onInvited(Comando comando, String ipAddres);

    void onAcceptedInvitation(Comando comando, String ipAdress);

    void onChat(Comando comando) throws SQLException;

    void onEditMessage(Comando comando, String ipAddres) throws SQLException;

    void onChangeTheme(Comando comando, String ipAddres);


    void onDeleteHistory(Comando comando, String ipAddres);

    void onPassContact(Comando comando, String ipAddres);

    void onScreenBuzz(Comando comando, String ipAddres);

    void onRejectedInvitation(Comando comando, String ipAddres);

    void onSearch(Comando comando, String ipAddres);



}