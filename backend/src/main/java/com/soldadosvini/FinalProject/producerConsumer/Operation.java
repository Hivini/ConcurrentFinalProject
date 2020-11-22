package com.soldadosvini.FinalProject.producerConsumer;

public class Operation {

    private String ID;
    private String operation;
    private String result;

    public Operation() {

    }

    public Operation(String id, String operation) {
        this.ID = id;
        this.operation = operation;
    }

    public Operation(String id, String operation, String result) {
        this.ID = id;
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
        return String.format("{id:%s, operation:%s, result=%s}", this.ID, this.operation, this.result);
    }
}