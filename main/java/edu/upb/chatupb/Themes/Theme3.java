package edu.upb.chatupb.Themes;

import javax.swing.*;
import java.awt.*;

public class Theme3 implements Themes{
    @Override
    public void getColor(JPanel jPanel1, JPanel jPanel2) {
        jPanel1.setBackground(Color.red);
        jPanel2.setBackground(Color.red);
    }
    @Override
    public void getButtons(JButton SendTextBTTN, JButton DeleteHistoryBTTN, JButton CambiarTemaBTTN, JButton SendContactButton, JButton SendInviteBTTN,JButton EditProfileBTTN, JButton BuzzBTTN) {
        SendTextBTTN.setBackground(Color.YELLOW);
        DeleteHistoryBTTN.setBackground(Color.YELLOW);
        CambiarTemaBTTN.setBackground(Color.YELLOW);
        SendContactButton.setBackground(Color.YELLOW);
        SendInviteBTTN.setBackground(Color.YELLOW);
        EditProfileBTTN.setBackground(Color.YELLOW);
        BuzzBTTN.setBackground(Color.YELLOW);
    }
}
