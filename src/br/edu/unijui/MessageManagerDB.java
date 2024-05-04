package br.edu.unijui;

import br.edu.unijui.Message.Priority;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import java.util.List;

/**
 * Avaliação
 * Disciplina de Programação para Camada de Negócio
 * @author <<< Put your full name here >>>
 */
public class MessageManagerDB {

    
    /**
     * Stores every message in the list of generated messages;
     * @param messageList the list of messages to be stored in the database.
     */
    public static void store(List<Message> messageList) {
        Connection con = getConnection();               
                
        // Insira aqui seu código para armazenar todas as mensagens no banco de dados.
        // Lembre-se de criar (abrir e fechar) uma transação para isso.
        // Lembre-se que é preciso usar blocos try-catch.

    }
    
    

    /**
     * Print messages with a given priority which are stored in the database
     *
     * @param priority the priority to select messages
     */
    public static void printMessages( Priority priority ) {
        
        Connection con = getConnection();
        
        // Insira aqui seu código para consultar e imprimir na tela todos os dados das mensagens recuperadas do banco de dados, de acordo com a prioridade informada.
        // Lembre-se que é preciso usar blocos try-catch.
        
    }

    
    /**
     * When invoked, it opens a connection to the database.
     *
     * @return the connection
     */
    private static Connection getConnection() {
        
        // Insira aqui o código necessário para abrir uma conexão com o banco de dados.
        // Lembre-se de que o método deve retornar essa conexão.

        return null; // <- Remova essa linha e substitua pelo retorno correto.
    }

}
