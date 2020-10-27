
package producerconsumer;

public class ProducerConsumer {

    public static void run(int bufferSize) {
        
        Buffer buffer = new Buffer(bufferSize);
        
        Producer producer = new Producer(buffer);
        producer.start();
        
        Consumer consumer = new Consumer(buffer);
        consumer.start();
    }
    
}
