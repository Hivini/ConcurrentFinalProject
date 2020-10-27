
package producerconsumer;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Buffer {
    
    private final Queue<Character> buffer;
    private int maxSize;
    
    Buffer(int bufferSize) {
        buffer = new PriorityQueue<>(bufferSize);
        maxSize = bufferSize;
    }
    
    synchronized char consume() {
        char product = 0;
        
        if(this.buffer.isEmpty()) {
            try {
                wait(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        product = this.buffer.remove();
        notify();
        
        return product;
    }
    
    synchronized void produce(char product) {
        if(this.buffer.size() >= maxSize) {
            try {
                wait(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.buffer.add(product);
        
        notify();
    }
    
    static int count = 1;
    synchronized static void print(String string) {
        System.out.print(count++ + " ");
        System.out.println(string);
    }
    
}
