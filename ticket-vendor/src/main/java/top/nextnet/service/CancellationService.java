package top.nextnet.service;

import top.nextnet.cli.UserInterface;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class CancellationService {

    @Inject
    UserInterface commerce;

    public void handleCancel(CancelationNotice notice){
        commerce.showErrorMessage("Ticket " + notice.getTicketId()+" is cancel");

    }
}
