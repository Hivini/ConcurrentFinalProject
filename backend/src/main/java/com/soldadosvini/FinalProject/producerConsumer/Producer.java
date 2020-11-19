
package com.soldadosvini.FinalProject.producerConsumer;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Producer extends Thread {
    Buffer buffer;
    Character[] operators;
    int min, max, waitTime;

    
    // Contructor parametrizado : Rango de numeros y posibles operadores
    public Producer(Buffer buffer, Character[] operators, int max, int min, int waitTime ) {
        this.buffer = buffer;
        this.operators = operators;
        this.max = max;
        this.min = min;
        this.waitTime = waitTime;
    }
    
    @Override
    public void run() {
        System.out.println("Running Producer...");
        Random r = new Random(System.currentTimeMillis());
        int auxIndexOperator = r.ints(0, this.operators.length).findFirst().getAsInt();
        String operation = "";


        while(this.buffer.thereIsSpace()) {
            operation = Utils.generateTask(this.min, this.max, this.operators[auxIndexOperator]);
            this.buffer.produce(operation);
            System.out.println("Producer produced: " + operation);
            
            try {
                Thread.sleep(this.waitTime);
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
