
package com.soldadosvini.FinalProject.producerConsumer;

import java.util.Observable;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Buffer extends Observable {

    private int finishedProcess = 0;
    private int producedProcess = 0;
    private final Queue<Operation> buffer;
    private final Queue<Operation> resolvedBuffer;
    private int maxSize;

    // Constructor parametrizado: size

    public Buffer(int bufferSize) {
        buffer = new PriorityQueue<>(bufferSize);
        resolvedBuffer = new PriorityQueue<>(bufferSize);
        this.maxSize = bufferSize;
        this.finishedProcess = 0;
        this.producedProcess = 0;
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

        notifyAll();
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
        this.producedProcess++;
        this.buffer.add(product);
        this.setChanged();
        this.notifyObservers(product);
        notifyAll();
    }

    synchronized public boolean isEmpty() {
        return this.buffer.isEmpty();
    }

    synchronized public boolean thereIsSpace() {
        return this.finishedProcess < this.maxSize;
    }

    synchronized public void addToResolved(Operation aux) {
        this.finishedProcess++;
        this.resolvedBuffer.add(aux);
        this.setChanged();
        this.notifyObservers(aux);
    }

    public int taskLeft() {
        return this.producedProcess - this.finishedProcess;
    }

    public String solvedTask() {

        String result = "{ ";

        for (Operation auxOp : this.resolvedBuffer) {
            result += auxOp.toString() + ",";
        }

        return result + "}";
    }

    public String toSolve() {

        String result = "{ ";

        for (Operation auxOp : this.buffer) {
            result += auxOp.toString() + ",";
        }

        return result + "}";
    }
}
