package br.edu.unijui;

import java.util.*;

/**
 * Avaliação
 * Disciplina de Programação para Camada de Negócio
 * @author Professor Rafael Zancan Frantz
 */
public class Message {

    // Atributos
    private UUID id;
    private Date creationDate;
    private Priority priority;
    private Date expirationDate;
    private String content;

    
    public enum Priority {
        LOWEST,LOW,NORMAL,HIGH,HIGHEST;
    }


    // Construtor
    public Message() {
        this( UUID.randomUUID(), new Date() );        
    }
    
    public Message( UUID identifier, Date creationDate ) {
        this.id = identifier;
        this.creationDate = creationDate;
    }
        
    // Operações
    
    public UUID getId() {
        return id;
    }
    
    public Priority getPriority() {
        return priority;
    }
    
    public void setPriority( Priority priority ) {
        this.priority = priority;
    }   
    
    public void setContent( String content ) {
        this.content = content;
    }
    
    public String getContent() {
        return content;
    }
    
    public Date getCreationDate() {
        return creationDate;
    }
    
    public Date getExpirationDate() {
        return expirationDate;
    }
    
    public void setExpirationDate( Date date ) {
        expirationDate = date;
    }
    
}

