/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package edu.upb.chatupb;

import edu.upb.chatupb.server.Mediador;
import edu.upb.chatupb.ui.ChatUI;

/**
 * @author Sarah
 */
public class Chatupb {

    public static void main(String[] args) {
        Mediador mediador = new Mediador();
        mediador.start();
        System.out.println("inciciando el mediador...");


        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ChatUI().setVisible(true);
            }
        });
    }
}
