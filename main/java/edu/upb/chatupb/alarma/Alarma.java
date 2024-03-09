/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upb.chatupb.alarma;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author naric
 */
public class Alarma extends JFrame implements Runnable{
    int altoV=500;
    int anchoV=500;
    Image img;
    Thread hilo;
    int incremento=0;
    public static void main(String[] args) {
        new Alarma().setVisible(true);
        
    }
    public Alarma(){
        setSize(altoV,anchoV);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("ATENCIOOOON");
        hilo=new Thread(this);
        Toolkit herramienta=Toolkit.getDefaultToolkit();
        img=herramienta.getImage(getClass().getResource("/imagenes/imagenFondo-removebg-preview.png"));
        hilo.start();
    }


    @Override
    public void paint(Graphics g) {
        Graphics2D g2D;
        g2D=(Graphics2D)g;
        int mx=(incremento%8)*103;
        g2D.drawImage(img,200,200,200+103,200+110,mx,incremento,mx+103,incremento+110,null,this);
        repaint();
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
