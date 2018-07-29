package com.afse.academy.queue;

import com.afse.academy.MyException;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.Serializable;

public abstract class AbstractQueueService<T extends Serializable> {

    //private final static Logger logger = LoggerFactory.getLogger(AbstractQueueService.class);
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
    public boolean send(T msg, int priority) throws MyException {
        Connection connection = null;
        Session session = null;

        if (!isValid()) return false;

        try {
            connection = getConnectionFactory().createConnection();
            session = connection.createSession(true, Session.SESSION_TRANSACTED);
            ObjectMessage message = session.createObjectMessage();
            message.setObject(msg);
            message.setJMSPriority(priority);
            session.createProducer(getQueue())
                    .send(message);
            return true;
        } catch (Exception e) {
            throw new MyException(e);
        } finally {
            releaseConnection(connection, session);
        }
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
    private void releaseConnection(Connection connection, Session session) throws MyException {
        try {
            if (session != null) session.close();
        } catch (JMSException e) {
            throw new MyException(e);
        }
        try {
            if (connection != null) connection.close();
        } catch (JMSException e) {
            throw new MyException(e);
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
