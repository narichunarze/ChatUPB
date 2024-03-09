package edu.upb.chatupb.Themes;

import javax.swing.*;

public class Theme0 implements Themes {


    @Override
    public void getColor(JPanel jPanel1, JPanel jPanel2) {
        jPanel1.setBackground(new java.awt.Color(139, 211, 221));
        jPanel2.setBackground(new java.awt.Color(139, 211, 221));
    }
    @Override
    public void getButtons(JButton SendTextBTTN, JButton DeleteHistoryBTTN, JButton CambiarTemaBTTN, JButton SendContactButton, JButton SendInviteBTTN,JButton EditProfileBTTN,JButton BuzzBTTN) {
        SendTextBTTN.setBackground(new java.awt.Color(243, 210, 193));
        DeleteHistoryBTTN.setBackground(new java.awt.Color(243, 210, 193));
        CambiarTemaBTTN.setBackground(new java.awt.Color(243, 210, 193));
        SendContactButton.setBackground(new java.awt.Color(243, 210, 193));
        SendInviteBTTN.setBackground(new java.awt.Color(243, 210, 193));
        EditProfileBTTN.setBackground(new java.awt.Color(243, 210, 193));
        BuzzBTTN.setBackground(new java.awt.Color(243, 210, 193));
    }
}

