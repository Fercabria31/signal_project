package com.decorator;

import com.decorator.Alert;
import com.decorator.AlertDecorator;

import java.util.Timer;
import java.util.TimerTask;

public class RepeatedAlertDecorator extends AlertDecorator {
    private int intervalInSeconds;

    public RepeatedAlertDecorator(Alert decoratedAlert, int intervalInSeconds) {
        super(decoratedAlert);
        this.intervalInSeconds = intervalInSeconds;
    }

    @Override
    public void trigger() {
        super.trigger();
        // Schedule task to re-trigger alert after interval
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                trigger(); // Re-trigger alert
            }
        }, intervalInSeconds * 1000); // Convert seconds to milliseconds
    }
}