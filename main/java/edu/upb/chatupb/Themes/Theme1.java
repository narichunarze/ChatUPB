package edu.upb.chatupb.Themes;

import javax.swing.*;
import java.awt.*;

public class Theme1 implements Themes{

    @Override
    public void getColor(JPanel jPanel1, JPanel jPanel2) {
        jPanel1.setBackground(Color.white);
        jPanel2.setBackground(Color.white);
    }

    @Override
    public void getButtons(JButton SendTextBTTN, JButton DeleteHistoryBTTN, JButton CambiarTemaBTTN, JButton SendContactButton, JButton SendInviteBTTN,JButton EditProfileBTTN, JButton BuzzBTTN) {
        SendTextBTTN.setBackground(new Color(37,211 ,102));
        DeleteHistoryBTTN.setBackground(new Color(37,211 ,102));
        CambiarTemaBTTN.setBackground(new Color(37,211 ,102));
        SendContactButton.setBackground(new Color(37,211 ,102));
        SendInviteBTTN.setBackground(new Color(37,211 ,102));
        EditProfileBTTN.setBackground(new Color(37,211 ,102));
        BuzzBTTN.setBackground(new Color(37,211 ,102));
    }
}
