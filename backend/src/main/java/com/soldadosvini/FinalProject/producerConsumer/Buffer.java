
package com.soldadosvini.FinalProject.producerConsumer;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Buffer {
    
    static private int finishedProcess = 0;
    static private int producedProcess = 0;
    private final Queue<String> buffer;
    private int maxSize;
    
    // Constructor parametrizado: size

    public Buffer(int bufferSize) {
        buffer = new PriorityQueue<>(bufferSize);
        maxSize = bufferSize;
    }
    
    synchronized String consume() {
        String product;
        
        while(this.buffer.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        product = this.buffer.remove();
        notify();
        
        return product;
    }
    
    synchronized void produce(String product) {
        while(producedProcess >= maxSize) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        producedProcess++;
        this.buffer.add(product);
        
        notify();
    }
    

    synchronized static void processFinished(String string) {
        finishedProcess++;
        System.out.print(finishedProcess + " ");
        System.out.println(string);
    }


    synchronized public boolean isEmpty() {
        return this.buffer.isEmpty();
    }

    synchronized public boolean thereIsSpace() {
        return finishedProcess < this.maxSize;
    }
}
