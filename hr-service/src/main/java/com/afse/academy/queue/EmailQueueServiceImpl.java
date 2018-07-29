package com.afse.academy.queue;

import com.afse.academy.Email;
import com.afse.academy.MyException;
import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import javax.jms.Message;
import javax.jms.Queue;

@Stateless
public class EmailQueueServiceImpl extends AbstractQueueService<Email> implements EmailQueueService {

    public static final String CONNECTION_FACTORY_LOOKUP_NAME = "java:jboss/DefaultJMSConnectionFactory";
            /*"jms/HREmailConnectionFactory"*/
    public static final String EMAIL_QUEUE_NAME = "queue/emailQueue";

    private ConnectionFactory connectionFactory;
    private Queue queue;

    @Inject
    private Logger logger;

    @Override
    public void init() {
        this.connectionFactory = doLookup(CONNECTION_FACTORY_LOOKUP_NAME);
        this.queue = doLookup(EMAIL_QUEUE_NAME);
    }

    @Override
    public void send(Email message) {
        try {
            send(message, Message.DEFAULT_PRIORITY);
        }
        catch (MyException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    protected ConnectionFactory getConnectionFactory() {
        return this.connectionFactory;
    }

    @Override
    protected Queue getQueue() {
        return this.queue;
    }
}