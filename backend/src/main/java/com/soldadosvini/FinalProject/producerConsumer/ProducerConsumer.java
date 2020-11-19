package com.soldadosvini.FinalProject.producerConsumer;


import com.soldadosvini.FinalProject.producerConsumer.Buffer;
import com.soldadosvini.FinalProject.producerConsumer.Consumer;
import com.soldadosvini.FinalProject.producerConsumer.Producer;
import com.soldadosvini.FinalProject.producerConsumer.Utils;

import java.sql.Array;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;

public class ProducerConsumer {

    private ArrayList<Consumer> consumers;
    private ArrayList<Producer> producers;
    private Buffer buffer;
    private Character[] operators;
    private int minRange;
    private int maxRange;



    public ProducerConsumer(int consumers, int producers, int bufferSize, Character[] operators, int minRange, int maxRange) {
        this.buffer = new Buffer(bufferSize);

        this.operators = operators;
        this.maxRange = maxRange;
        this.minRange = minRange;
        
       
        this.producers = new ArrayList<>();
        initProducers(producers);

        this.consumers = new ArrayList<>();
        initConsumers(consumers);
        
        
    }

    public void initConsumers(int size) {
        for (int i = 0; i < size; i++) {
            this.consumers.add(new Consumer(this.buffer));
        }
    }

    public void initProducers(int size) {
        for (int i = 0; i < size; i++) {
            this.producers.add(new Producer(this.buffer, this.operators, this.maxRange, this.minRange));
        }
    }

    public void start() {

        for (Producer  auxProducer : this.producers) {
            auxProducer.start();
        }

        for (Consumer  auxConsumer : this.consumers) {
            auxConsumer.start();
        }
    }


}