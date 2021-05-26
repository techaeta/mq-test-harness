package io.openliberty.mq.test;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Counter {
    private int lastSent = 0;
    private int lastReceived = 0;
    
    
    public int getLastReceived() {
      return this.lastReceived;
    }

    public void setLastReceived(int i) {
      this.lastReceived=i;
    }

    public int getLastSent() {
      return this.lastSent;
    }

    public void setLastSent(int i) {
      this.lastSent=i;
    }
  
  }

