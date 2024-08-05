package com.geplatform.geviews.components;


import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import org.springframework.stereotype.Service;

@Service
public class NotificationUi {

    public NotificationUi(){

    }

    public void errorNotification(String message) {
        // When creating a notification using the constructor,
        // the duration is 0-sec by default which means that
        // the notification does not close automatically.
        Notification notification = new Notification();
        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
        notification.setPosition(Notification.Position.TOP_CENTER);
        notification.setDuration(5000);
        notification.setText(message);
        notification.open();
    }

    public void successNotification(String message){

        Notification notification = new Notification();

        notification.setPosition(Notification.Position.TOP_CENTER);
        notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
       notification.setDuration(5000); // todo costanti
        notification.setText(message);

        notification.open();

    }


}
