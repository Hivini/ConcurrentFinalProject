
package com.soldadosvini.FinalProject.producerConsumer;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Producer extends Thread {
    Buffer buffer;
    Character[] operators;
    int min, max;
    
    // Contructor parametrizado : Rango de numeros y posibles operadores
    public Producer(Buffer buffer, Character[] operators, int max, int min ) {
        this.buffer = buffer;
        this.operators = operators;
        this.max = max;
        this.min = min;
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
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
