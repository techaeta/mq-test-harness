package io.openliberty.mq.test;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;


import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class JMSHelper {
 
  public JMSHelper() {}
 


  public String sendMessage (String msg) {

    ConnectionFactory factory = null;
    Destination sendQueue = null;
    Destination receiveQueue = null;
    
    Connection connection = null;
    Session session = null;
    MessageProducer producer = null;

    try {
      Context context = new InitialContext();
      factory = (ConnectionFactory)context.lookup("jms/wmqCF");

      sendQueue = (Destination)context.lookup("jms/sendQueue");
      receiveQueue = (Destination)context.lookup("jms/receiveQueue");
      //System.out.println("Connecting to Queue Manager..");
      connection = factory.createConnection();
      connection.start();
      session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
      producer = session.createProducer(sendQueue);
      //System.out.println("Sending message...");
      TextMessage message = session.createTextMessage();
      message.setText(msg);
      message.setJMSReplyTo(receiveQueue);
      producer.send((Message)message);
      return msg;
    } catch (Exception e) {
      System.out.println("Error: " + e.getMessage());
      e.printStackTrace();
      return "Messaging Error";
    } finally {
      try {
        if (producer != null)
          producer.close(); 
        if (session != null)
          session.close(); 
        if (connection != null)
          connection.close(); 
      } catch (Exception e) {
        System.out.println("This is a bad place to be.");
        e.printStackTrace();    
      } 
    } 
  }

  public String receiveMessage () {
    ConnectionFactory factory = null;
    Destination sendQueue = null;
    Destination receiveQueue = null;
    
    Connection connection = null;
    Session session = null;
    MessageProducer producer = null;
    MessageConsumer consumer = null;

    try {
      Context context = new InitialContext();
      factory = (ConnectionFactory)context.lookup("jms/wmqCF");
      receiveQueue = (Destination)context.lookup("jms/receiveQueue");
      //System.out.println("Connecting to Queue Manager..");
      connection = factory.createConnection();
      connection.start();
      session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
      consumer = session.createConsumer(receiveQueue);
      //System.out.println("Waiting for response...");
      Message msg = consumer.receive(15000L);
      if (msg == null) {
        return null;
      } 
      String msgText = ((TextMessage)msg).getText();
      return msgText;

    } catch (Exception e) {
      System.out.println("Error: " + e.getMessage());
      e.printStackTrace();
      return "Messaging Error";
    } finally {
      try {
        if (consumer != null)
          consumer.close(); 
        if (session != null)
          session.close(); 
        if (connection != null)
          connection.close(); 
      } catch (Exception e) {
        System.out.println("This is a bad place to be.");
        e.printStackTrace();    
      } 
    } 
  }
}
