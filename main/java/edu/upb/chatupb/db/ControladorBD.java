package edu.upb.chatupb.db;

import com.mysql.cj.protocol.Message;
import edu.upb.chatupb.server.objects.Contacto;
import edu.upb.chatupb.server.objects.Mensaje;

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
        try (Connection conn = ConnectionDB.instance.getConnection();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO mensajeria.contactos VALUES (?, ?,?)")) {
            ps.setString(1, idPersona);
            ps.setString(2, nombre);
            ps.setString(3, ip);
            ps.executeUpdate();
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
    public static List<Mensaje> getMessages(String myId, String chatId) {
        System.out.println("llegue a getMessages");
        List<Mensaje> mensajesList = new ArrayList<>();

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
                String fechaHora= resultSet.getString("FechaHora");
                String IdEmisor=resultSet.getString("IdEmisor");
                Mensaje mensaje1= new Mensaje(messageId,idReceptor,IdEmisor,fechaHora,mensaje);
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

    private static List<Message> getListMessages(ResultSet resultSet) throws SQLException {
        List<Message> queryRes = new ArrayList<>();
        while (resultSet.next()) {
            String messageId = resultSet.getString("IdMensaje");
            String codReceiver = resultSet.getString("IdReceptor");
            String message = resultSet.getString("Mensaje");
            String fechaHora = resultSet.getString("FechaHora");
            String codSender = resultSet.getString("IdEmisor");

        }
        return queryRes;
    }
}










