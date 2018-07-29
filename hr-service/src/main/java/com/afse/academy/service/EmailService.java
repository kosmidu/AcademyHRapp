package com.afse.academy.service;

import com.afse.academy.Email;
import com.afse.academy.EmailDao;
import com.afse.academy.Employee;
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
        /*String message = "First name:" + emp.getFirstName() +
                "\nLast name:" + emp.getLastName();*/
        email.setEmail(emp);
        emailQueue.send(email);
    }
}
