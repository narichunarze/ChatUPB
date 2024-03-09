package edu.upb.chatupb.ui;

import javax.swing.*;
import java.awt.*;

public class EditProfileDialog extends JDialog {
    public EditProfileDialog(JFrame parent) {
        super(parent, "Edit Profile", true); // Diálogo modal vinculado al padre

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new java.awt.Color(139, 211, 221)); // Establece el color de fondo del panel principal
        mainPanel.setLayout(new BorderLayout());

        // Configura el diseño del diálogo
        setLayout(new BorderLayout());

        // Panel para la foto del perfil
        JPanel photoPanel = new JPanel(new GridBagLayout());
        photoPanel.setBackground(new java.awt.Color(139, 211, 221)); // Puedes ajustar el color según lo necesites
        ImageIcon defaultPhoto = new ImageIcon("C:\\Users\\naric\\IdeaProjects\\chatupb\\src\\imagenes\\istockphoto-1316161163-612x612.jpg"); // Ruta de la foto por defecto
        JLabel photoLabel = new JLabel(defaultPhoto); // Crea un JLabel para la foto

        // Configura el tamaño preferido de la foto
        photoLabel.setPreferredSize(new Dimension(200, 200)); // Tamaño de la foto

        // Configura las restricciones para centrar la foto en el panel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0; // Proporción horizontal
        gbc.weighty = 1.0; // Proporción vertical
        gbc.anchor = GridBagConstraints.CENTER; // Centra el componente
        photoPanel.add(photoLabel, gbc);



        mainPanel.add(photoPanel, BorderLayout.NORTH); // Añade el panel de la foto arriba del diálogo

        add(mainPanel, BorderLayout.CENTER);

        // Configuracion botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // Alinea los botones a la derecha
        JButton acceptButton = new JButton("Aceptar");
        JButton cancelButton = new JButton("Cancelar");

        // Agrega ActionListener para el botón "Aceptar"
        acceptButton.addActionListener(e -> {
            // Lógica para aceptar cambios en el perfil y guardarlos
            // Aquí debes implementar la lógica para guardar los cambios
            dispose(); // Cierra el diálogo al hacer clic en Aceptar
        });

        // Agrega ActionListener para el botón "Cancelar"
        cancelButton.addActionListener(e -> {
            // Lógica para cancelar cambios en el perfil
            dispose(); // Cierra el diálogo al hacer clic en Cancelar
        });

        buttonPanel.add(acceptButton);
        buttonPanel.add(cancelButton);

        add(buttonPanel, BorderLayout.SOUTH);

        JLabel nameLabel = new JLabel("Nombre:");
        JTextField nameField = new JTextField("Nari Chun Arze");

        // Etiqueta y campo de texto para el código
        JLabel codeLabel = new JLabel("Código:");
        JTextField codeField = new JTextField("4496f66e-2893-4b56-a923-53c45fa64a18");

        // Agrega las etiquetas y campos de texto al panel principal
        mainPanel.add(nameLabel);
        mainPanel.add(nameField);
        mainPanel.add(codeLabel);
        mainPanel.add(codeField);

        add(mainPanel, BorderLayout.CENTER); // Agrega el panel principal al centro del diálogo


        // Configuración del diálogo
        setSize(600, 600); // Tamaño del diálogo
        setBackground(new java.awt.Color(228, 163, 213));
        setLocationRelativeTo(parent); // Centra el diálogo respecto a la ventana principal
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Cierra el diálogo al hacer clic en el botón de cierre
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            EditProfileDialog dialog = new EditProfileDialog(new JFrame());
            dialog.setVisible(true);
        });
    }
}