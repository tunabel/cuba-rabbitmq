package com.company.rabbitmq.web.screens.lender;

import com.company.rabbitmq.entity.Lender;
import com.company.rabbitmq.service.AgencyCreationEvent;
import com.company.rabbitmq.service.RabbitSenderService;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.screen.*;
import org.springframework.context.event.EventListener;

import javax.inject.Inject;

@UiController("rabbitmq_Lender.browse")
@UiDescriptor("lender-browse.xml")
@LookupComponent("table")
@LoadDataBeforeShow
public class LenderBrowse extends MasterDetailScreen<Lender> {
    @Inject
    private Notifications notifications;

    @Inject
    private RabbitSenderService rabbitSenderService;
    @Inject
    private GroupTable<Lender> table;

    @Subscribe("sendToMQ")
    public void onSendToMQClick(Button.ClickEvent event) {
        publishMessageToMQ(table.getSingleSelected());
    }

    @Subscribe
    public void onBeforeCommitChanges(BeforeCommitChangesEvent event) {
        publishMessageToMQ(getEditedEntity());
    }

    @EventListener
    protected void onAgencyCreated(AgencyCreationEvent event) {
        createTrayNotification(event.getMessage());
    }

    private void publishMessageToMQ(Lender lender) {
        String result = rabbitSenderService.send(lender);

       createTrayNotification(result);
    }

    private void createTrayNotification(String message) {
        notifications
                .create()
                .withType(Notifications.NotificationType.TRAY)
                .withCaption("The Rabbit is notified with "+message)
                .show();
    }

}