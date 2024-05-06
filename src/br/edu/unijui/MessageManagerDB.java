package br.edu.unijui;

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.DatabaseMetaData;
import java.util.List;

/**
 * Avaliação
 * Disciplina de Programação para Camada de Negócio
 * @author <<< Put your full name here >>>
 */
public class MessageManagerDB {

    /**
     * When invoked, it opens a connection to the database.
     *
     * @return the connection
     */
    private static Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/APP", "app", "app");
            createTableIfNotExists(con); // Verifica e cria a tabela se ela não existir
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar ao banco de dados: " + ex.getMessage());
        }
        return con;
    }

    // Método para verificar e criar a tabela se ela não existir, não consegui via SQL
    private static void createTableIfNotExists(Connection con) throws SQLException {
        if (con != null) {
            DatabaseMetaData metaData = con.getMetaData();
            ResultSet resultSet = metaData.getTables(null, null, "MESSAGES", null);
            if (!resultSet.next()) { // Se a tabela não existir
                try (Statement stmt = con.createStatement()) {
                    String sql = "CREATE TABLE APP.MESSAGES (" +
                                 "ID VARCHAR(36) PRIMARY KEY, " +
                                 "CREATION_DATE DATE, " +
                                 "PRIORITY INT, " +
                                 "EXPIRATION_DATE DATE, " +
                                 "CONTENT VARCHAR(255))";
                    stmt.executeUpdate(sql);
                    System.out.println("Tabela MESSAGES criada com sucesso.");
                } catch (SQLException ex) {
                    System.out.println("Erro ao criar tabela MESSAGES: " + ex.getMessage());
                    throw ex; // Re-lança a exceção para que a conexão não seja usada
                }
            }
        }
    }

    /**
     * Stores every message in the list of generated messages;
     * @param messageList the list of messages to be stored in the database.
     */
    public static void store(List<Message> messageList) {
        Connection con = getConnection();
        if (con != null) {
            try {
                con.setAutoCommit(false); // Inicia a transação
                String sql = "INSERT INTO APP.MESSAGES (ID, CREATION_DATE, PRIORITY, EXPIRATION_DATE, CONTENT) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement pstmt = con.prepareStatement(sql);
                for (Message msg : messageList) {
                    pstmt.setString(1, msg.getId().toString());
                    pstmt.setDate(2, new java.sql.Date(msg.getCreationDate().getTime()));
                    pstmt.setInt(3, msg.getPriority().ordinal());
                    pstmt.setDate(4, new java.sql.Date(msg.getExpirationDate().getTime()));
                    pstmt.setString(5, msg.getContent());
                    pstmt.executeUpdate();
                }
                con.commit(); // Confirma a transação
                con.setAutoCommit(true); // Volta para o modo de auto commit
                pstmt.close();
                con.close();
            } catch (SQLException ex) {
                try {
                    con.rollback(); // Desfaz a transação em caso de erro
                    System.out.println("Erro ao armazenar mensagens: " + ex.getMessage());
                } catch (SQLException e) {
                    System.out.println("Erro ao desfazer transação: " + e.getMessage());
                }
            }
        }
    }

    /**
     * Print messages with a given priority which are stored in the database
     *
     * @param priority the priority to select messages
     */
    public static void printMessages(Message.Priority priority) {
        Connection con = getConnection();
        if (con != null) {
            try {
                Statement stmt = con.createStatement();
                String sql = "SELECT ID, CREATION_DATE, PRIORITY, EXPIRATION_DATE, CONTENT FROM MESSAGES WHERE PRIORITY = " + priority.ordinal();
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    System.out.println("ID: " + rs.getString("ID"));
                    System.out.println("Creation Date: " + rs.getDate("CREATION_DATE"));
                    System.out.println("Priority: " + rs.getInt("PRIORITY"));
                    System.out.println("Expiration Date: " + rs.getDate("EXPIRATION_DATE"));
                    System.out.println("Content: " + rs.getString("CONTENT"));
                    System.out.println("---------------------");
                }
                rs.close();
                stmt.close();
                con.close();
            } catch (SQLException ex) {
                System.out.println("Erro ao imprimir mensagens: " + ex.getMessage());
            }
        }
    }
}
