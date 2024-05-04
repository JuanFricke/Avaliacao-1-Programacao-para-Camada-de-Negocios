package br.edu.unijui;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

/**
 * Avaliação
 * Disciplina de Programação para Camada de Negócio
 * @author Professor Rafael Zancan Frantz
 */
public class MessageFactory {
    
    
    /**
     * This static method creates a list of random generated messages
     * @param numberOfMessages the number of messages to be created
     * @return a list of messages
     */
    public static List<Message> buildMessages( int numberOfMessages ) {
        List<Message> list = new ArrayList<>();
        Message msg = null;
        for ( int i=0; i<numberOfMessages; i++ ) {
            msg = new Message();
            msg.setExpirationDate( new GregorianCalendar( 2020, Calendar.DECEMBER, 31 ).getTime() );
            msg.setPriority( Message.Priority.values()[ new Random().nextInt( Message.Priority.values().length ) ] );
            msg.setContent( "This is the message body..." );
            list.add( msg );
        }
        return list;
    }
    
    
}
