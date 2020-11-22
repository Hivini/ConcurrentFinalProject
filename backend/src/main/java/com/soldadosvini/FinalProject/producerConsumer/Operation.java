package com.soldadosvini.FinalProject.producerConsumer;

import java.util.UUID;

public class Operation implements Comparable<Operation> {

    private String ID;
    private String operation;
    private String result;

    public Operation() {

    }

    public Operation(String operation) {
        this.ID = UUID.randomUUID().toString();
        this.operation = operation;
    }

    public Operation(String operation, String result) {
        this.ID = UUID.randomUUID().toString();
        this.operation = operation;
        this.result = result;
    }

    public String getID() {
        return ID;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setID(String iD) {
        this.ID = iD;
    }

    @Override
    public String toString() {
        return String.format("{id: %s, operation: %s, result: %s}", this.ID, this.operation, this.result);
    }

    @Override
    public int compareTo(Operation o) {
        return 0;
    }

}