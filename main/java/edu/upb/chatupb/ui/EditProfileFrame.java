package edu.upb.chatupb.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class EditProfileFrame extends JFrame {
    private JLabel profilePictureLabel;
    private JTextField nameTextField;
    private JButton changePictureButton;
    private String imagePath;

    private final int PICTURE_WIDTH = 200; // Ancho deseado de la imagen
    private final int PICTURE_HEIGHT = 200; // Alto deseado de la imagen

    public EditProfileFrame() {
        setTitle("Editar Perfil");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        getContentPane().setBackground(new java.awt.Color(139, 211, 221));

        profilePictureLabel = new JLabel();
        profilePictureLabel.setPreferredSize(new Dimension(PICTURE_WIDTH, PICTURE_HEIGHT)); // Establecer dimensiones deseadas
        profilePictureLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(profilePictureLabel, BorderLayout.NORTH);

        JPanel editPanel = new JPanel(new GridLayout(2, 1));
        nameTextField = new JTextField("NARI CHUN ARZE");
        nameTextField.setText("NARI CHUN ARZE");
        nameTextField.setEditable(false);
        editPanel.add(nameTextField);

        changePictureButton = new JButton("Cambiar Foto de Perfil");
        changePictureButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Lógica para cambiar la foto de perfil
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    // Obtener la imagen seleccionada
                    ImageIcon icon = new ImageIcon(fileChooser.getSelectedFile().getAbsolutePath());
                    // Escalar la imagen para que se ajuste a las dimensiones deseadas
                    Image image = icon.getImage().getScaledInstance(PICTURE_WIDTH, PICTURE_HEIGHT, Image.SCALE_SMOOTH);
                    profilePictureLabel.setIcon(new ImageIcon(image));
                }
            }
        });

        editPanel.add(changePictureButton);

        add(editPanel, BorderLayout.CENTER);
    }
}
