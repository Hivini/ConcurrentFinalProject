package com.soldadosvini.FinalProject.producerConsumer;

import java.util.ArrayList;

public class ProducerConsumer {

    private ArrayList<Consumer> consumers;
    private ArrayList<Producer> producers;
    public Buffer buffer;
    private Character[] operators;
    private int minRange;
    private int maxRange;
    private int waitTime;

    public ProducerConsumer(int consumers, int producers, int bufferSize, Character[] operators, int minRange,
            int maxRange, int waitTime) {
        this.buffer = new Buffer(bufferSize);

        this.operators = operators;
        this.maxRange = maxRange;
        this.minRange = minRange;
        this.waitTime = waitTime;

        this.producers = new ArrayList<>();
        initProducers(producers);

        this.consumers = new ArrayList<>();
        initConsumers(consumers);

    }

    public void initConsumers(int size) {
        for (int i = 0; i < size; i++) {
            this.consumers.add(new Consumer(this.buffer, this.waitTime));
        }
    }

    public void initProducers(int size) {
        for (int i = 0; i < size; i++) {
            this.producers.add(new Producer(this.buffer, this.operators, this.maxRange, this.minRange, this.waitTime));
        }
    }

    public void start() {

        for (Producer auxProducer : this.producers) {
            auxProducer.start();
        }

        for (Consumer auxConsumer : this.consumers) {
            auxConsumer.start();
        }
    }

}