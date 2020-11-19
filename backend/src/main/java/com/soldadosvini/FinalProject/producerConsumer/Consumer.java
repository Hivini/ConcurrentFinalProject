
package com.soldadosvini.FinalProject.producerConsumer;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Consumer extends Thread {
    Buffer buffer;
    int waitTime;
    
    public Consumer(Buffer buffer, int waitTime) {
        this.buffer = buffer;
        this.waitTime = waitTime;
    }
    
    @Override
    public void run() {
        System.out.println("Running Consumer...");
        String product;
        

        while(this.buffer.thereIsSpace()){
            product = this.buffer.consume(); // get the operacion: (+ 4 3)
            String result = Utils.resolveTask(product);
            // call scheme interpreter
            // get result

            Buffer.processFinished("Consumer result: " + result); // let the buffer know
            
            try {
                Thread.sleep(this.waitTime);
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }


}
