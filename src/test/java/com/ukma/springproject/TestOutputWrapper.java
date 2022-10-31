package com.ukma.springproject;

public class TestOutputWrapper {
    private StringBuilder outputMock = new StringBuilder();

    public void write(String s) {
        outputMock.append(s);
    }

    public String getOutput() {
        return outputMock.toString();
    }

    public void clean () {
        outputMock = new StringBuilder();
    }
}
