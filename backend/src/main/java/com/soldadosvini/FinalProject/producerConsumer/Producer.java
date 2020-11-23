
package com.soldadosvini.FinalProject.producerConsumer;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Producer extends Thread {
    Buffer buffer;
    Character[] operators;
    int min, max, waitTime;
    public boolean stop = false;

    // Contructor parametrizado : Rango de numeros y posibles operadores
    public Producer(Buffer buffer, Character[] operators, int max, int min, int waitTime) {
        this.buffer = buffer;
        this.operators = operators;
        this.max = max;
        this.min = min;
        this.waitTime = waitTime;
    }

    @Override
    public void run() {
        System.out.println("Running Producer...");
        Random r = new Random();
        int auxIndexOperator = r.ints(0, this.operators.length).findFirst().getAsInt();
        String operationStr = "";

        while (this.buffer.thereIsSpace() && !stop) {
            operationStr = Utils.generateTask(this.min, this.max, this.operators[auxIndexOperator]);
            Operation auxOperation = new Operation(operationStr);

            System.out.println("produced");
            System.out.println(auxOperation);

            this.buffer.produce(auxOperation);

            try {
                Thread.sleep(this.waitTime);
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
