package com.decorator;

public class BasicAlert implements Alert {
    private String message;

    public BasicAlert(String message) {
        this.message = message;
    }

    @Override
    public void trigger() {
        System.out.println("Basic Alert: " + message);
    }
}