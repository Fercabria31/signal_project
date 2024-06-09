package com.decorator;

public abstract class AlertDecorator implements Alert {
    protected Alert decoratedAlert;

    public AlertDecorator(Alert decoratedAlert) {
        this.decoratedAlert = decoratedAlert;
    }

    @Override
    public void trigger() {
        decoratedAlert.trigger();
    }
}
