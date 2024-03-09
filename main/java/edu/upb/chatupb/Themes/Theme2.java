package edu.upb.chatupb.Themes;

import javax.swing.*;
import java.awt.*;

public class Theme2 implements Themes{

    @Override
    public void getColor(JPanel jPanel1, JPanel jPanel2) {
        jPanel1.setBackground(new Color(1,37 ,56));
        jPanel2.setBackground(new Color(1,37 ,56));
    }
    @Override
    public void getButtons(JButton SendTextBTTN, JButton DeleteHistoryBTTN, JButton CambiarTemaBTTN, JButton SendContactButton, JButton SendInviteBTTN,JButton EditProfileBTTN,JButton BuzzBTTN) {
        SendTextBTTN.setBackground(new Color(251,152 ,51));
        DeleteHistoryBTTN.setBackground(new Color(251,152 ,51));
        CambiarTemaBTTN.setBackground(new Color(251,152 ,51));
        SendContactButton.setBackground(new Color(251,152 ,51));
        SendInviteBTTN.setBackground(new Color(251,152 ,51));
        EditProfileBTTN.setBackground(new Color(251,152 ,51));
        BuzzBTTN.setBackground(new Color(251,152 ,51));
    }
}
