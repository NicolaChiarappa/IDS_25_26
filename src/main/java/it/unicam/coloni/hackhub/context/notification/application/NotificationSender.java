package it.unicam.coloni.hackhub.context.notification.application;

public interface NotificationSender<T> {

    void send(T data);
}
