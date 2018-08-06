package com.afse.academy.queue;

import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.Serializable;

public abstract class AbstractQueueService<T extends Serializable> {

    @Inject
    private Logger logger;

    /**
     * Lookup to initialize the {@link ConnectionFactory}
     * and the {@link Queue}
     */
    public abstract void init();

    protected abstract ConnectionFactory getConnectionFactory();

    protected abstract Queue getQueue();

    @PostConstruct
    public void initialization() {
        init();
    }

    /**
     * @param msg The actual message to be sent to the {@link Queue
     * @return true if the message was sent successfully
     * @throws MyException
     */
    public boolean send(T msg, int priority) {
        Connection connection = null;
        Session session = null;

        if (!isValid()) {
            return false;
        }

        try {
            connection = getConnectionFactory().createConnection();
            session = connection.createSession(true, Session.SESSION_TRANSACTED);
            ObjectMessage message = session.createObjectMessage();
            message.setObject(msg);
            message.setJMSPriority(priority);
            session.createProducer(getQueue())
                    .send(message);
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            releaseConnection(connection, session);
        }
        return true;
    }

    /**
     * Validates the configuration of the service
     *
     * @return true if everything is valid to proceed
     */
    private boolean isValid() {
        if (getConnectionFactory() == null) {
            logger.error("No connection factory was initialized to send message!");
            return false;
        }
        if (getQueue() == null) {
            logger.error("No queue was initialized to send message!");
            return false;
        }
        return true;
    }

    /**
     * Release the connection and the session of the JMS
     *
     * @param connection
     * @param session
     */
    private void releaseConnection(Connection connection, Session session) {
        try {
            if (session != null) session.close();
        } catch (JMSException e) {
            logger.error(e.getMessage());
        }
        try {
            if (connection != null) connection.close();
        } catch (JMSException e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * @param <T>        The type if the bounded object
     * @param lookupName the name of the bean
     * @return The object bound to <tt>name</tt>
     */
    protected <T> T doLookup(String lookupName) {
        try {
            return InitialContext.doLookup(lookupName);
        } catch (NamingException e) {
            logger.error("Cannot find queue name <" + lookupName + ">", e);
            return null;
        }
    }


}
