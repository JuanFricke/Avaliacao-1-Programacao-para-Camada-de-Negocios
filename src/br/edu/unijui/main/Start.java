package br.edu.unijui.main;

import br.edu.unijui.Message;
import br.edu.unijui.MessageFactory;
import br.edu.unijui.MessageManagerDB;
import java.net.UnknownHostException;
import java.util.List;

/**
 * Avaliação
 * Disciplina de Programação para Camada de Negócio
 * @author Professor Rafael Zancan Frantz
 */
public class Start {

    private static final int NUMBER_OF_MESSAGES = 100;

    public static void main(String[] args) throws UnknownHostException {

        // Cria uma lista de mensagens aleatórias, com a quantidade de mensagens de acordo com número informado.
        List<Message> list = MessageFactory.buildMessages( NUMBER_OF_MESSAGES );

        // Armazena todas as mensagens criadas no banco de dados.
        MessageManagerDB.store(list);

        // Lista mensagens com a prioridade informada.
        MessageManagerDB.printMessages(Message.Priority.NORMAL);

    }

}
