package com.afse.academy.service;

import com.afse.academy.entities.Email;
import com.afse.academy.queue.EmailQueueService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

@Stateless
public class EmailService {
    @EJB
    private EmailQueueService emailQueue;

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void sendRegistrationEmail(String emp){
        Email email = new Email();
        email.setEmail(emp);
        emailQueue.send(email);
    }
}
