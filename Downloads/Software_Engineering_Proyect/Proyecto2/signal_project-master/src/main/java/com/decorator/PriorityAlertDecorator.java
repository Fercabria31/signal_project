package com.decorator;

import com.decorator.Alert;
import com.decorator.AlertDecorator;

public class PriorityAlertDecorator extends AlertDecorator {
    private int priorityLevel;

    public PriorityAlertDecorator(Alert decoratedAlert, int priorityLevel) {
        super(decoratedAlert);
        this.priorityLevel = priorityLevel;
    }

    @Override
    public void trigger() {
        System.out.println("Priority Level " + priorityLevel + " Alert:");
        super.trigger();
    }
}
