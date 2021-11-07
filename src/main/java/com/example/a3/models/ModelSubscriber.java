package com.example.a3.models;

/**
 * An interface used for drawing model subscribers.
 */
public interface ModelSubscriber {

    // Notify the subscribers that the model has changed.
    void modelChanged();

}
