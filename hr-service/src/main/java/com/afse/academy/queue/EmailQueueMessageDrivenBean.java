package com.afse.academy.queue;

import com.afse.academy.Email;
import com.afse.academy.EmailDao;
import org.apache.log4j.Logger;

import javax.ejb.*;
import javax.inject.Inject;
import javax.jms.*;

@MessageDriven(name = "emailQueue", activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = EmailQueueServiceImpl.EMAIL_QUEUE_NAME),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")
})

@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class EmailQueueMessageDrivenBean implements MessageListener {
    @Inject
    private Logger logger;

    @EJB
    private EmailDao emailDao;

    @Override
    public void onMessage(Message message) {
        try {
            if(message instanceof ObjectMessage) {
                logger.info("MESSAGE BEAN: Message received:" +
                        message.getBody(Email.class).getEmail());
                emailDao.createEmail(message.getBody(Email.class));
            } else {
                logger.warn("Message of wrong type:" +
                        message.getClass().getName());
            }
        }
        catch (JMSException e) {
            logger.error("EmailQueueMessageDrivenBean.onMessage: JMSException:" +
                    e.toString());
        }

    }
}
