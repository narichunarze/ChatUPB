/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package edu.upb.chatupb.ui;

import edu.upb.chatupb.Interpreter.Expresion;
import edu.upb.chatupb.Interpreter.PalabraClave;
import edu.upb.chatupb.Themes.Theme0;
import edu.upb.chatupb.Themes.Theme1;
import edu.upb.chatupb.Themes.Theme2;
import edu.upb.chatupb.Themes.Theme3;
import edu.upb.chatupb.db.ControladorBD;
import edu.upb.chatupb.event.SocketEvent;
import edu.upb.chatupb.model.ContactCollection;
import edu.upb.chatupb.server.ChatServer;
import edu.upb.chatupb.server.Mediador;
import edu.upb.chatupb.server.SocketClient;
import edu.upb.chatupb.server.comandos.*;
import edu.upb.chatupb.server.objects.Contacto;
import edu.upb.chatupb.server.objects.Mensaje;
import lombok.Getter;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.UUID;

/**
 * @author Sarah
 */
@Getter
public class ChatUI extends javax.swing.JFrame implements SocketEvent {

    private String nombre;
    private String codigo;
    private ChatServer chatServer;
    private Connection conn;
    String nombrePropio = "Nari Chun";
    String idPropio = "4496f66e-2893-4b56-a923-53c45fa64a18";
    private SocketClient socketClient;
    private Contacto contactoSeleccionado;
    JPopupMenu jPopupMenu = new JPopupMenu();
    JMenuItem tema0 = new JMenuItem("Default");
    JMenuItem tema1 = new JMenuItem("Light");
    JMenuItem tema2 = new JMenuItem("Dark");
    JMenuItem tema3 = new JMenuItem("Clown");


    InetAddress localhost;

    private String selectedContact;

    public ChatUI(SocketClient socketClient) {
        this.socketClient = socketClient;
    }

    DefaultListModel<Contacto> modelContact;
    DefaultListModel<String> modelChat;
    DefaultListModel<Contacto> newModel = new DefaultListModel<>();


    /**
     * Creates new form ChatUI2
     */
    public ChatUI() {
        initComponents();
        createPopUpMenu();

        jPanel1.setBackground(new Color(139, 211, 221));
        conn = ControladorBD.getInstance().getConnection(); // Obtener la conexión directamente desde ControladorDB

        // Crear un objeto Scanner para aceptar la entrada del usuario
        Scanner scanner = new Scanner(System.in);


        try {
            this.chatServer = new ChatServer(this);
            this.chatServer.start();
            chatList = new JList<String>();
            modelContact = new DefaultListModel<>();
            modelChat = new DefaultListModel<String>();

            this.contactList.setModel(modelContact);
            this.chatList.setModel(modelChat);

            jScrollPane1.setViewportView(contactList);
            //jScrollPane1.setViewportView(comboBoxContactos);
            jScrollPane2.setViewportView(chatList);


            fillContactList();
            fillUserInfo();
            fillComboBoxContactos();
            Mediador.addSocketEventListener(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createPopUpMenu() {
        jPopupMenu.add(tema0);
        jPopupMenu.add(tema1);
        jPopupMenu.add(tema2);
        jPopupMenu.add(tema3);
    }


    private void fillContactList() {
        java.util.List<Contacto> contactos = ControladorBD.getContacts();
        ContactCollection contactCollection = new ContactCollection();
        if (contactos != null) {
            for (Contacto contacto : contactos) {
                contactCollection.add(contacto);
            }

            newModel.clear();


            while (contactCollection.hasNext()) {
                newModel.addElement((Contacto) contactCollection.getnext());
            }
        }
        contactList.setModel(newModel);


    }

    private void fillUserInfo() {
        Contacto contacto = ControladorBD.getUserName();
        if (contacto != null) {
            this.nombre = contacto.getName();
            this.codigo = contacto.getId();
        }
    }

    private void fillComboBoxContactos() {
        /*DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        java.util.List<Contacto> contactos = ControladorBD.getContacts();
        if (contactos != null) {
            for (Contacto contacto : contactos) {
                model.addElement(contacto.getName());
            }
        }*/
        //comboBoxContactos.setModel(model);
        DefaultListModel<Contacto> newModel = new DefaultListModel<>();

        java.util.List<Contacto> contactos = ControladorBD.getContacts();
        if (contactos != null) {
            for (Contacto contacto : contactos) {
                newModel.addElement(contacto);
            }
        }
        contactList.setModel(newModel);
    }


    // Resto del código del constructor...


    private JDialog createDialog(final JFrame frame, String message, String tittle, JButton... buttons) {
        final JDialog modelDialog = new JDialog(frame, tittle,
                Dialog.ModalityType.DOCUMENT_MODAL);
        modelDialog.setBounds(132, 132, 300, 200);
        Container dialogContainer = modelDialog.getContentPane();
        dialogContainer.setLayout(new BorderLayout());
        dialogContainer.add(new JLabel("    " + message),
                BorderLayout.CENTER);
        JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout());
        for (JButton button : buttons) {
            panel1.add(button);
        }
        dialogContainer.add(panel1, BorderLayout.SOUTH);
        return modelDialog;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jDialog2 = new javax.swing.JDialog();
        jOptionPane1 = new javax.swing.JOptionPane();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        textArea = new javax.swing.JTextField();
        SendTextBTTN = new javax.swing.JButton();
        DeleteHistoryBTTN = new javax.swing.JButton();
        CambiarTemaBTTN = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        chatList = new JList<String>();
        jPanel2 = new javax.swing.JPanel();
        jTextField2 = new javax.swing.JTextField();
        BuzzBTTN = new javax.swing.JButton();
        EditProfileBTTN = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        contactList = new javax.swing.JList<>();
        SendContactButton = new javax.swing.JButton();
        SendInviteBTTN = new javax.swing.JButton();

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
                jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
                jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jDialog2Layout = new javax.swing.GroupLayout(jDialog2.getContentPane());
        jDialog2.getContentPane().setLayout(jDialog2Layout);
        jDialog2Layout.setHorizontalGroup(
                jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog2Layout.setVerticalGroup(
                jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(139, 211, 221));

        textArea.setBackground(new java.awt.Color(254, 246, 228));
        textArea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textAreaActionPerformed(evt);
            }
        });


        SendTextBTTN.setBackground(new java.awt.Color(243, 210, 193));
        SendTextBTTN.setText("Send");
        textArea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    sendTextArea(new java.awt.event.ActionEvent(this, java.awt.event.ActionEvent.ACTION_PERFORMED, ""));
                }
            }


        });
        SendTextBTTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendTextArea(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, ""));
            }
        });


        DeleteHistoryBTTN.setBackground(new java.awt.Color(243, 210, 193));
        DeleteHistoryBTTN.setText("Delete History");

        CambiarTemaBTTN.setBackground(new java.awt.Color(243, 210, 193));
        CambiarTemaBTTN.setText("Change Theme");
        CambiarTemaBTTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jPopupMenu.show(CambiarTemaBTTN, 0, 0);
            }
        });
        tema0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Contacto selectedContact = contactList.getSelectedValue();

                if (selectedContact != null) {
                    CambiarTema cambiarTema = CambiarTema.builder()
                            .tipo("005")
                            .codigoTema("0")
                            .codigoPersonaOrg(idPropio)
                            .build();
                    Mediador.sendMessage(selectedContact.getIp(), cambiarTema);
                    System.out.println("selected contact ip: " + selectedContact.getIp());
                    JOptionPane.showMessageDialog(jOptionPane1, "Tema Enviado",
                            "Error", JOptionPane.ERROR_MESSAGE);


                } else {
                    System.out.println("No contact selected.");
                }

            }
        });
        tema1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Contacto selectedContact = contactList.getSelectedValue();
                if (selectedContact != null) {
                    System.out.println("estoy en enviar tema 1");
                    CambiarTema cambiarTema = CambiarTema.builder()
                            .tipo("005")
                            .codigoTema("1")
                            .codigoPersonaOrg(idPropio)
                            .build();
                    Mediador.sendMessage(selectedContact.getIp(), cambiarTema);
                    JOptionPane.showMessageDialog(jOptionPane1, "Tema Enviado",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    System.out.println("selected contact ip: " + selectedContact.getIp());
                    System.out.println("codigo tema enviado: " + cambiarTema.getCodigoTema());


                } else {
                    System.out.println("No contact selected.");
                }
            }
        });
        tema2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Contacto selectedContact = contactList.getSelectedValue();

                if (selectedContact != null) {
                    CambiarTema cambiarTema = CambiarTema.builder()
                            .tipo("005")
                            .codigoTema("2")
                            .codigoPersonaOrg(idPropio)
                            .build();
                    Mediador.sendMessage(selectedContact.getIp(), cambiarTema);
                    JOptionPane.showMessageDialog(jOptionPane1, "Tema Enviado",
                            "Error", JOptionPane.ERROR_MESSAGE);


                } else {
                    System.out.println("No contact selected.");
                }

            }
        });
        tema3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Contacto selectedContact = contactList.getSelectedValue();

                if (selectedContact != null) {
                    CambiarTema cambiarTema = CambiarTema.builder()
                            .tipo("005")
                            .codigoPersonaOrg(idPropio)
                            .codigoTema("3")
                            .build();
                    Mediador.sendMessage(selectedContact.getIp(), cambiarTema);
                    JOptionPane.showMessageDialog(jOptionPane1, "Tema Enviado",
                            "Error", JOptionPane.ERROR_MESSAGE);


                } else {
                    System.out.println("No contact selected.");
                }

            }
        });

        jScrollPane2.setViewportView(chatList);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(DeleteHistoryBTTN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGap(186, 186, 186)
                                                .addComponent(CambiarTemaBTTN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(textArea)
                                                .addGap(24, 24, 24)
                                                .addComponent(SendTextBTTN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addContainerGap())
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE)
                                        .addGap(14, 14, 14)))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(DeleteHistoryBTTN)
                                        .addComponent(CambiarTemaBTTN))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(SendTextBTTN, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(textArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addContainerGap(34, Short.MAX_VALUE)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(54, Short.MAX_VALUE)))
        );

        jPanel2.setBackground(new java.awt.Color(139, 211, 221));

        jTextField2.setBackground(new java.awt.Color(61, 169, 252));
        jTextField2.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jTextField2.setForeground(new java.awt.Color(255, 255, 255));
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTextField2.setText("ChatUPB");
        jTextField2.setSelectionColor(new java.awt.Color(0, 0, 0));
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        BuzzBTTN.setText("Enviar Alarma");
        BuzzBTTN.setBackground(new java.awt.Color(243, 210, 193));

        EditProfileBTTN.setText("Edit Profile");
        EditProfileBTTN.setBackground(new java.awt.Color(243, 210, 193));
        EditProfileBTTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditProfileBTTNActionPerformed(evt);
            }
        });

        contactList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                contactListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(contactList);

        SendContactButton.setText("Send Contact");
        SendContactButton.setBackground(new java.awt.Color(243, 210, 193));
        SendContactButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear un nuevo JPanel para mostrar la lista de contactos y el botón de enviar
                JPanel panel = new JPanel();
                JComboBox<Contacto> contactListComboBox = new JComboBox<>();

                // Llenar el JComboBox con los contactos disponibles
                java.util.List<Contacto> contactos = ControladorBD.getContacts();
                for (Contacto contacto : contactos) {
                    contactListComboBox.addItem(contacto);
                }

                JButton enviarButton = new JButton("Enviar");
                enviarButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Obtener el contacto seleccionado

                        Contacto selectedContact = (Contacto) contactListComboBox.getSelectedItem();
                        Contacto contactoSel = contactList.getSelectedValue();
                        if (contactoSel != null) {
                            PasarContacto pasarContacto = PasarContacto.builder()
                                    .tipo("007")
                                    .codigoPersona(selectedContact.getId())
                                    .nombre(selectedContact.getName())
                                    .ipOtraPersona(selectedContact.getIp())
                                    .build();
                            System.out.println("nombre " + selectedContact.getName());
                            System.out.println("ip del otro: " + selectedContact.getIp());

                            Mediador.sendMessage(contactoSel.getIp(), pasarContacto);
                            JOptionPane.showMessageDialog(jOptionPane1, "Contacto enviado",
                                    "Error", JOptionPane.INFORMATION_MESSAGE);

                        } else {
                            JOptionPane.showMessageDialog(jOptionPane1, "Por favor, selecciona un contacto primero.",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        }


                        //mandarlo con socket


                        // Cerrar el diálogo después de enviar el contacto
                        ((Window) panel.getRootPane().getParent()).dispose();
                    }
                });

                panel.add(contactListComboBox);
                panel.add(enviarButton);

                // Crear y mostrar un JDialog para mostrar el panel
                JDialog dialog = new JDialog();
                dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                dialog.getContentPane().add(panel);
                dialog.pack();
                dialog.setLocationRelativeTo(null); // Centrar el diálogo en la pantalla
                dialog.setVisible(true);
            }
        });


        SendInviteBTTN.setText("Send Invite");
        SendInviteBTTN.setBackground(new java.awt.Color(243, 210, 193));
        SendInviteBTTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear un nuevo JPanel para mostrar el formulario de contacto y el botón de enviar
                JPanel panel = new JPanel();
                panel.setLayout(new GridLayout(4, 2)); // Diseño de cuadrícula para organizar los componentes

                // Crear etiquetas y campos de texto para la dirección IP, código y nombre
                JLabel ipLabel = new JLabel("IP:");
                JTextField ipField = new JTextField();

                // Botón de enviar
                JButton enviarButton = new JButton("Enviar");

                enviarButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Obtener los valores de los campos de texto
                        String ip = ipField.getText();

                        ipField.setText("");

                        // Validar que los campos no estén vacíos
                        if (ip.isEmpty()) {
                            JOptionPane.showMessageDialog(panel, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        InvitacionContacto invitacion = InvitacionContacto.builder()
                                .tipo("001")
                                .codigoPersona(idPropio)
                                .nombre(nombrePropio)
                                .mensaje("Invitación a conectar")
                                .build();
                        System.out.println("pase hasta despues de build");
                        Mediador.sendMessage(ip, invitacion);


                        // Cerrar el diálogo después de enviar los datos
                        Window window = SwingUtilities.getWindowAncestor(panel);
                        window.dispose();
                    }
                });

                // Agregar componentes al panel
                panel.add(ipLabel);
                panel.add(ipField);
                panel.add(enviarButton);


                // Crear y mostrar un JDialog para mostrar el panel
                JDialog dialog = new JDialog();
                dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                dialog.getContentPane().add(panel);
                dialog.pack();
                dialog.setLocationRelativeTo(null); // Centrar el diálogo en la pantalla
                dialog.setVisible(true);
            }
        });
        BuzzBTTN.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                Contacto selectedContact = contactList.getSelectedValue();
                System.out.println("nombre: " + selectedContact.getName());
                System.out.println("llegue a zumbido");
                if (selectedContact != null) {
                    ZumbidoPantalla zumbidoPantalla = ZumbidoPantalla.builder()
                            .tipo("009")
                            .build();
                    Mediador.sendMessage(selectedContact.getIp(), zumbidoPantalla);
                    System.out.println("ip: " + selectedContact.getIp());

                }
            }

        });


        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(EditProfileBTTN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGap(18, 18, 18))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(jTextField2)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                                                .addComponent(BuzzBTTN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addGap(13, 13, 13)))
                                                .addGap(42, 42, 42)))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(SendContactButton)
                                        .addComponent(SendInviteBTTN))
                                .addContainerGap(22, Short.MAX_VALUE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                                        .addGap(126, 126, 126)))
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(SendInviteBTTN)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(207, 207, 207)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(EditProfileBTTN)
                                                        .addComponent(SendContactButton))
                                                .addComponent(BuzzBTTN, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(18, Short.MAX_VALUE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addContainerGap(42, Short.MAX_VALUE)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(91, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(0, 0, 0)
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(1, 1, 1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void textAreaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textAreaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textAreaActionPerformed

    private void EditProfileBTTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditProfileBTTNActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EditProfileBTTNActionPerformed

    private void SendTextBTTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SendTextBTTNActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SendTextBTTNActionPerformed

    private void getChat(String contactID, String contactName) {
        java.util.List<Mensaje> messagesListDB = ControladorBD.getMessages(idPropio, contactID);
        DefaultListModel<String> model = new DefaultListModel<>();

        if (messagesListDB == null) {
            System.out.println("lista VACIA");
            return;
        }

        for (Mensaje message : messagesListDB) {
            String formattedMessage;
            if (message.getIdSender().equals(idPropio)) {
                formattedMessage = nombrePropio + ": " + message.getMessage();
            } else {
                formattedMessage = contactName + ": " + message.getMessage();
            }
            model.addElement(formattedMessage);
        }

        // Establecer el modelo de la lista de chat con el modelo creado
        chatList.setModel(model);

    }

    private void contactListValueChanged(ListSelectionEvent evt) {
        Contacto selectedContact = contactList.getSelectedValue();

        if (selectedContact != null) {
            getChat(selectedContact.getId(), selectedContact.getName());
        }
    }

    private void sendTextArea(ActionEvent actionEvent) {
        Contacto selectedContact = contactList.getSelectedValue();
        String randomUUID = UUID.randomUUID().toString();

        if (selectedContact != null) {
            String contactId = selectedContact.getId();

            String text = textArea.getText().trim();
            textArea.setText("");

            if (!text.isEmpty()) {
                Chat chat = Chat.builder()
                        .tipo("003")
                        .codigoMensaje(UUID.randomUUID().toString())
                        .codigoPersona(idPropio)
                        .mensaje(text)
                        .build();


                Mediador.sendMessage(selectedContact.getIp(), chat);
                ControladorBD.insertMessage(randomUUID, contactId, idPropio, text);

                getChat(contactId, selectedContact.getName());
            }

        } else {
            System.out.println("No contact selected.");
        }
    }



    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ChatUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChatUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChatUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChatUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChatUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BuzzBTTN;
    private javax.swing.JButton CambiarTemaBTTN;
    private javax.swing.JButton DeleteHistoryBTTN;
    private javax.swing.JButton EditProfileBTTN;
    private javax.swing.JButton SendContactButton;
    private javax.swing.JButton SendInviteBTTN;
    private javax.swing.JButton SendTextBTTN;
    private JList<String> chatList;
    private javax.swing.JList<Contacto> contactList;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JDialog jDialog2;
    private javax.swing.JOptionPane jOptionPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField textArea;
    // End of variables declaration//GEN-END:variables


    @Override
    public void onInvited(Comando comando, String ipAddres) {

        InvitacionContacto invitacionContacto = (InvitacionContacto) comando;

        Object[] options = {"Aceptar", "Rechazar"};
        System.out.println("llego en invited");
        int n = JOptionPane.showOptionDialog(this,
                invitacionContacto.getNombre() +
                        "\n\n\n\n" + "Te ha invitado a chatear",
                "Invitación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
        switch (n) {
            case JOptionPane.YES_OPTION -> {
                try {

                    AceptarInvitacion aceptarInvitacion = AceptarInvitacion.builder()
                            .tipo("002")
                            .codigoPersona(idPropio)
                            .nombre(nombrePropio)
                            .build();
                    System.out.println("antes de sendMessage");
                    Mediador.sendMessage(((InvitacionContacto) comando).getIp(), aceptarInvitacion);
                    Contacto contacto = Contacto.builder()
                            .id(invitacionContacto.getCodigoPersona())
                            .name(invitacionContacto.getNombre())
                            .ip(invitacionContacto.getIp())
                            .isStateConnect(true)
                            .build();
                    contactList.repaint();
                    fillContactList();
                    ControladorBD.insertContact(contacto.getId(), contacto.getName(), contacto.getIp());
                    System.out.println("paso sendMessage");

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            case JOptionPane.NO_OPTION -> {
                try {
                    RechazoInvitacion rechazoInvitacion = RechazoInvitacion.builder()
                            .tipo("010")
                            .build();
                    Mediador.sendMessage(((InvitacionContacto) comando).getIp(), rechazoInvitacion);

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
            default -> {
            }

        }


    }


    @Override
    public void onAcceptedInvitation(Comando comando, String ipAdress) {
        System.out.println("llegue a on accepted");
        AceptarInvitacion acceptedContact = (AceptarInvitacion) comando;
        System.out.println("ip de la persona: " + ((AceptarInvitacion) comando).getIp());

        JDialog dialog = createDialog(this, "Aceptacion", (acceptedContact.getNombre()));
        dialog.setVisible(true);
        contactList.repaint();

        ControladorBD.insertContact(acceptedContact.getCodigoPersona(),
                acceptedContact.getNombre(),
                ((AceptarInvitacion) comando).getIp());

        fillContactList();
    }


    @Override
    public void onChat(Comando comando) throws SQLException {
        Contacto selectedContact = contactList.getSelectedValue();
        Chat chat = (Chat) comando;

        ControladorBD.insertMessage(chat.getCodigoMensaje(), idPropio, chat.getCodigoPersona(), chat.getMensaje());
        modelChat.addElement(chat.getMensaje());
        modelChat.clear();
        getChat(chat.getCodigoPersona(), chat.getCodigoMensaje());

        String[] palabrasClave = {"clima", "tiempo", "SantaCruz", "Cochabamba"};
        Expresion expresion = new PalabraClave(palabrasClave);
        System.out.println("mensaje sin espacios: "+chat.getMensaje().trim());

        try {
            if (expresion.evalua(chat.getMensaje().trim())) {
                System.out.println("entro a true para la palabra evalua");
                Chat chat1 = Chat.builder()
                        .tipo("003")
                        .codigoMensaje(UUID.randomUUID().toString())
                        .codigoPersona(idPropio)
                        .mensaje("el pronostico es: " + expresion.obtenerRespuestaClimaAleatoria())
                        .build();

                Mediador.sendMessage(selectedContact.getIp(), chat1);
            } else {
                System.out.println("La descripción no responde a la consulta");
            }
        } catch (Exception e) {
            System.out.println("Error al analizar la consulta: " + e.getMessage());
        }
    }


    @Override
    public void onEditMessage(Comando comando, String ipAddres) {
        JDialog dialog = createDialog(this, "Editar Mensaje",
                ((EditarMensaje) comando).getMensaje().toString());
        dialog.show();

    }

    @Override
    public void onChangeTheme(Comando comando, String ipAddres) {
        System.out.println("estoy en onChange comienzo");
        CambiarTema changeTheme = (CambiarTema) comando;
        System.out.println("codigoTema: " + ((CambiarTema) comando).getCodigoTema());
        String tema = changeTheme.getCodigoTema();
        System.out.println("tema: " + tema);
        switch (tema) {
            case "0" -> {
                new Theme0().getColor(this.jPanel1, this.jPanel2);
                new Theme0().getButtons(this.SendTextBTTN, this.DeleteHistoryBTTN, this.CambiarTemaBTTN, this.SendContactButton, this.SendInviteBTTN, this.EditProfileBTTN, this.BuzzBTTN);
            }
            case "1" -> {
                new Theme1().getColor(this.jPanel1, this.jPanel2);
                new Theme1().getButtons(this.SendTextBTTN, this.DeleteHistoryBTTN, this.CambiarTemaBTTN, this.SendContactButton, this.SendInviteBTTN, this.EditProfileBTTN, this.BuzzBTTN);
            }
            case "2" -> {
                new Theme2().getColor(this.jPanel1, this.jPanel2);
                new Theme2().getButtons(this.SendTextBTTN, this.DeleteHistoryBTTN, this.CambiarTemaBTTN, this.SendContactButton, this.SendInviteBTTN, this.EditProfileBTTN, this.BuzzBTTN);
            }
            case "3" -> {
                new Theme3().getColor(this.jPanel1, this.jPanel2);
                new Theme3().getButtons(this.SendTextBTTN, this.DeleteHistoryBTTN, this.CambiarTemaBTTN, this.SendContactButton, this.SendInviteBTTN, this.EditProfileBTTN, this.BuzzBTTN);
            }

        }

    }

    @Override
    public void onDeleteHistory(Comando comando, String ipAddres) {
        JDialog dialog = createDialog(this, "Borrar Chat",
                ((BorrarHistorial) comando).toString());
        dialog.show();

    }

    @Override
    public void onPassContact(Comando comando, String ipAddres) {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        JButton aceptarButton = new JButton("Aceptar");

        panel.add(new JLabel("Te ha llegado el contacto de: " + ((PasarContacto) comando).getNombre()));
        panel.add(aceptarButton);

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);

        String ipCompartido = ((PasarContacto) comando).getIpOtraPersona();


        // Acción para el botón "Aceptar" Enconces me llega un contacto y tengo que abrir un socket aca? o con el de invitacion se mantiene?
        aceptarButton.addActionListener(e -> {
            // Guardar el contacto en la base de datos
            InvitacionContacto invitacion = InvitacionContacto.builder()
                    .tipo("001")
                    .codigoPersona(idPropio)
                    .nombre(nombrePropio)
                    .mensaje("Invitación a conectar")
                    .build();
            System.out.println("pase hasta despues de build");
            System.out.println("ip compartido: " + ipCompartido);
            Mediador.sendMessage(ipCompartido, invitacion);

            // Cerrar el diálogo
            frame.dispose();
        });

    }


    private javax.swing.Timer shakeTimer;
    private int shakeMagnitude = 1000;
    private Color[] colors = {Color.RED, Color.BLUE, Color.BLUE, Color.RED}; // Colores disponibles
    private int colorIndex = 0;

    // Método para ejecutar el zumbido de pantalla
    private void executeScreenBuzz() {
        shakeTimer = new javax.swing.Timer(50, new ActionListener() {
            private int shakeCount = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (shakeCount < 5) {
                    // Cambiar el color de fondo de la ventana
                    getContentPane().setBackground(colors[colorIndex]);
                    colorIndex = (colorIndex + 1) % colors.length; // Cambiar al siguiente color
                    if (shakeCount % 2 == 0) {
                        setLocation(getX() + shakeMagnitude, getY());
                    } else {
                        setLocation(getX() - shakeMagnitude, getY());
                    }
                    shakeCount++;
                } else {
                    shakeTimer.stop();
                    shakeCount = 0;
                }
            }
        });
        shakeTimer.start();
    }

    @Override
    public void onScreenBuzz(Comando comando, String ipAddres) {
        JDialog dialog = createDialog(this, "Notificacion",
                "RESPONDE CTM");
        dialog.show();
        if (comando instanceof ZumbidoPantalla) {
            executeScreenBuzz();
        }


    }

    @Override
    public void onRejectedInvitation(Comando comando, String ipAddres) {
        JDialog dialog = createDialog(this, "Rechazo de invitacion",
                ((RechazoInvitacion) comando).toString());
        dialog.show();
        socketClient.interrupt();


    }

    @Override
    public void onSearch(Comando comando, String ipAddres) {
        JDialog dialog = createDialog(this, "Busqueda",
                ((Busqueda) comando).toString());
        dialog.show();

    }

    private void showInvitationDialog(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    private static JDialog createDialog(final JFrame frame, String origin, String message) {
        final JDialog modelDialog = new JDialog(frame, origin,
                Dialog.ModalityType.DOCUMENT_MODAL);

        modelDialog.setBounds(132, 132, 300, 200);
        Container dialogContainer = modelDialog.getContentPane();

        dialogContainer.setLayout(new BorderLayout());
        dialogContainer.add(new JLabel(message),
                BorderLayout.CENTER);

        JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout());

        JButton okButton = new JButton("Ok");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modelDialog.setVisible(false);
            }
        });

        panel1.add(okButton);
        dialogContainer.add(panel1, BorderLayout.SOUTH);

        return modelDialog;
    }


}
