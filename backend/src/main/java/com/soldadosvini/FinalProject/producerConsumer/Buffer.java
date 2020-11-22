
package com.soldadosvini.FinalProject.producerConsumer;

import java.util.Observable;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Buffer extends Observable {

    static private int finishedProcess = 0;
    static private int producedProcess = 0;
    private final Queue<Operation> buffer;
    private final Queue<Operation> resolvedBuffer;
    private int maxSize;

    // Constructor parametrizado: size

    public Buffer(int bufferSize) {
        buffer = new PriorityQueue<>(bufferSize);
        resolvedBuffer = new PriorityQueue<>(bufferSize);
        maxSize = bufferSize;
    }

    synchronized Operation consume() {
        Operation product;

        while (this.buffer.isEmpty()) {
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

    synchronized void produce(Operation product) {
        while (producedProcess >= maxSize) {
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

    synchronized public boolean isEmpty() {
        return this.buffer.isEmpty();
    }

    synchronized public boolean thereIsSpace() {
        return finishedProcess < this.maxSize;
    }

    synchronized public void addToResolved(Operation aux) {
        finishedProcess++;
        System.out.print(finishedProcess + " " + aux.toString();
        this.resolvedBuffer.add(aux);
        this.setChanged();
        this.notifyObservers(aux.toString());
    }
}
