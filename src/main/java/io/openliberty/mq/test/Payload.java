package io.openliberty.mq.test;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Payload {
    private String message = "MQ OK";
    private String destination = null;
    
    private String replyTo = null;
    
    private String ipAddress = null;
    
    private String status = null;
    
    public String getMessage() {
      return this.message;
    }
    
    public void setMessage(String message) {
      this.message = message;
    }
    
    public String getDestination() {
      return this.destination;
    }
    
    public void setDestination(String destination) {
      this.destination = destination;
    }
    
    public String getReplyTo() {
      return this.replyTo;
    }
    
    public void setReplyTo(String replyTo) {
      this.replyTo = replyTo;
    }
    
    public String getIpAddress() {
      return this.ipAddress;
    }
    
    public void setIpAddress(String ipAddress) {
      this.ipAddress = ipAddress;
    }
    
    public String getStatus() {
      return this.status;
    }
    
    public void setStatus(String status) {
      this.status = status;
    }
  }

