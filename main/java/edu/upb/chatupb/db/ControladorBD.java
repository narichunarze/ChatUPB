package edu.upb.chatupb.db;

import edu.upb.chatupb.server.objects.ChatInfo;
import edu.upb.chatupb.server.objects.Contacto;
import edu.upb.chatupb.server.objects.Mensaje;

import javax.swing.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static edu.upb.chatupb.db.ConnectionDB.instance;

public class ControladorBD {
    public static ConnectionDB getInstance() {
        return instance;
    }


    public static boolean insertContact(String idPersona, String nombre, String ip) {
        try (Connection conn = ConnectionDB.instance.getConnection()) {
            // Verificar si el nombre ya existe en la base de datos
            PreparedStatement psSelect = conn.prepareStatement("SELECT COUNT(*) FROM mensajeria.contactos WHERE IdPersona = ?");
            psSelect.setString(1, idPersona);
            ResultSet rs = psSelect.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            rs.close();

            // Si el nombre ya existe, actualizar la dirección IP
            if (count > 0) {
                PreparedStatement psUpdate = conn.prepareStatement("UPDATE mensajeria.contactos SET IpContacto = ? WHERE IdPersona = ?");
                psUpdate.setString(1, ip);
                psUpdate.setString(2, idPersona);
                psUpdate.executeUpdate();
                System.out.println("Se actualizó la dirección IP para el contacto: " + nombre);
            } else { // Si el nombre no existe, insertar un nuevo contacto
                PreparedStatement psInsert = conn.prepareStatement("INSERT INTO mensajeria.contactos VALUES (?, ?, ?)");
                psInsert.setString(1, idPersona);
                psInsert.setString(2, nombre);
                psInsert.setString(3, ip);
                psInsert.executeUpdate();
                System.out.println("Se insertó un nuevo contacto: " + nombre);
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return false;
        }
    }


    public static List<Contacto> getContacts() {
        try (Connection con = ConnectionDB.instance.getConnection();
             PreparedStatement statement = con.prepareStatement("SELECT * FROM mensajeria.contactos")) {
            ResultSet resultSet = statement.executeQuery();

            List<Contacto> contactos = new ArrayList<>();

            while (resultSet.next()) {
                Contacto contacto = Contacto.builder()
                        .id(resultSet.getString("idPersona"))
                        .name(resultSet.getString("Nombre"))
                        .ip(resultSet.getString("IpContacto"))
                        .isStateConnect(true).build();
                contactos.add(contacto);
            }

            return contactos;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Contacto getUserName() {
        try (Connection con = ConnectionDB.instance.getConnection();
             PreparedStatement statement = con.prepareStatement(
                     "SELECT * FROM mensajeria.contactos " +
                             "WHERE idPersona = '4496f66e-2893-4b56-a923-53c45fa64a18'")) {

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return Contacto.builder()
                        .id(resultSet.getString("IdPersona"))
                        .name(resultSet.getString("Nombre"))
                        .ip(resultSet.getString("IpContacto"))
                        .isStateConnect(true).build();
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static List<ChatInfo> getMessages(String myId, String chatId) {
        System.out.println("llegue a getMessages");
        List<ChatInfo> mensajesList = new ArrayList<>();

        String combineQuery = "SELECT * FROM mensajeria.mensajes " +
                "WHERE (IdEmisor = ? AND IdReceptor = ?) OR (IdReceptor = ? AND IdEmisor = ?)" + "ORDER BY FechaHora";


        try (Connection con = ConnectionDB.instance.getConnection()) {
            PreparedStatement preparedStatement = con.prepareStatement(combineQuery);
            preparedStatement.setString(1, myId);
            preparedStatement.setString(2, chatId);
            preparedStatement.setString(3, myId);
            preparedStatement.setString(4, chatId);

            ResultSet resultSet = preparedStatement.executeQuery();


            while(resultSet.next()){
                String messageId=resultSet.getString("IdMensaje");
                String idReceptor=resultSet.getString("IdReceptor");
                String mensaje=resultSet.getString("Mensaje");
                String IdEmisor=resultSet.getString("IdEmisor");
                ChatInfo mensaje1= new ChatInfo(messageId,idReceptor,IdEmisor,mensaje);
                mensajesList.add(mensaje1);

            }
            return mensajesList;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void insertMessage(String messageId, String receiverId, String senderId, String message) {

        Date date = new Date(System.currentTimeMillis());
        // Define el formato en el que quieres mostrar la hora
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // Formatea la fecha y hora
        String formattedDate = sdf.format(date);
        try (Connection con = ConnectionDB.instance.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(
                     "INSERT INTO mensajeria.mensajes " +
                             "(IdMensaje, IdReceptor, Mensaje, FechaHora, IdEmisor) " +
                             "VALUES (?, ?, ?, ?, ?)")) {

            preparedStatement.setString(1, messageId);
            preparedStatement.setString(2, receiverId);
            preparedStatement.setString(3, message);
            preparedStatement.setString(4, formattedDate);
            preparedStatement.setString(5, senderId);

            preparedStatement.execute();
        } catch (SQLException e) {
            System.err.println("Error inserting message into the database: " + e.getMessage());
        }
    }

    public static void deleteMessages(String idContacto, String idPropio) {
        // Construir la consulta SQL para eliminar los mensajes del contacto y los tuyos
        String query = "DELETE FROM mensajes WHERE (IdReceptor = ? AND IdEmisor = ?) OR (IdReceptor = ? AND IdEmisor = ?)";

        // Preparar la declaración y establecer los parámetros
        try (Connection con = ConnectionDB.instance.getConnection();
             PreparedStatement statement = con.prepareStatement(query)) {
            // Establecer los parámetros en la consulta SQL
            statement.setString(1, idPropio);
            statement.setString(2, idContacto);
            statement.setString(3, idContacto);
            statement.setString(4, idPropio);

            // Ejecutar la consulta SQL
            int rowsAffected = statement.executeUpdate();

            // Verificar si se eliminaron mensajes y mostrar un mensaje de éxito
            if (rowsAffected > 0) {
                System.out.println("Se eliminaron los mensajes del contacto seleccionado y los tuyos en ese chat.");
            } else {
                System.out.println("No se encontraron mensajes para eliminar.");
            }
        } catch (SQLException e) {
            // Manejar cualquier excepción SQL
            e.printStackTrace();
        }
    }

    public static void actualizarMensaje(String idMensaje, String nuevoMensaje) throws SQLException {
        PreparedStatement pstmt = null;

        try(Connection con = ConnectionDB.instance.getConnection()){

            String sql = "UPDATE Mensajes SET Mensaje = ? WHERE IdMensaje = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, nuevoMensaje);
            pstmt.setString(2, idMensaje);
            pstmt.executeUpdate();
        }
    }

}









