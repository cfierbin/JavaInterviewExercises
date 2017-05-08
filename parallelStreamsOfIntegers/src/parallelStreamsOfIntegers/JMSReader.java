package parallelStreamsOfIntegers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;

public class JMSReader extends Thread implements Reader{
	
	private static final Logger log = Logger.getLogger(JMSReader.class.getName());
	private String userName;
	private String password;
	private Destination destination;
	private int count;
	private ConnectionFactory connectionFactory;
	
	private List<Integer> list;
	
    private static final String DEFAULT_CONNECTION_FACTORY = "jms/RemoteConnectionFactory";
    private static final String DEFAULT_MESSAGE_COUNT = "10";
    private static final String DEFAULT_USERNAME = "quickstartUser";
    private static final String DEFAULT_PASSWORD = "quickstartPwd1!";
    private static final String INITIAL_CONTEXT_FACTORY = "org.jboss.naming.remote.client.InitialContextFactory";
    private static final String PROVIDER_URL = "http-remoting://127.0.0.1:8080";
	
	JMSReader(String queueName){
		list = new ArrayList<Integer>();
		
		Context namingContext = null;

        try {
            userName = System.getProperty("username", DEFAULT_USERNAME);
            password = System.getProperty("password", DEFAULT_PASSWORD);

            // Set up the namingContext for the JNDI lookup
            final Properties env = new Properties();
            env.put(Context.INITIAL_CONTEXT_FACTORY, INITIAL_CONTEXT_FACTORY);
         //   env.put(Context.INITIAL_CONTEXT_FACTORY, org.jboss.naming.remote.client.InitialContextFactory.class.getName());
            env.put(Context.PROVIDER_URL, System.getProperty(Context.PROVIDER_URL, PROVIDER_URL));
            env.put(Context.SECURITY_PRINCIPAL, userName);
            env.put(Context.SECURITY_CREDENTIALS, password);
            namingContext = new InitialContext(env);

            // Perform the JNDI lookups
            String connectionFactoryString = System.getProperty("connection.factory", DEFAULT_CONNECTION_FACTORY);
            log.info("Attempting to acquire connection factory \"" + connectionFactoryString + "\"");
            connectionFactory = (ConnectionFactory) namingContext.lookup(connectionFactoryString);
            log.info("Found connection factory \"" + connectionFactoryString + "\" in JNDI");

            String destinationString = System.getProperty("destination", queueName);
            log.info("Attempting to acquire destination \"" + destinationString + "\"");
            destination = (Destination) namingContext.lookup(destinationString);
            log.info("Found destination \"" + destinationString + "\" in JNDI");

            count = Integer.parseInt(System.getProperty("message.count", DEFAULT_MESSAGE_COUNT));
        } catch (NamingException e) {
            log.severe(e.getMessage());
        } finally {
            if (namingContext != null) {
                try {
                    namingContext.close();
                } catch (NamingException e) {
                    log.severe(e.getMessage());
                }
            }
        }
	}
	
	@Override
	public void run(){
		read();
	}
	
	@Override
	public List<Integer> read() {
		
		try (JMSContext context = connectionFactory.createContext(userName, password)) {
		// Create the JMS consumer
        JMSConsumer consumer = context.createConsumer(destination);
        // Then receive the same number of messages that were sent
        for (int i = 0; i < count; i++) {
            String text = consumer.receiveBody(String.class, 5000);
            log.info("Received message with content " + text);
            list.add(Integer.parseInt(text));
        }
		} 
		
		return list;
	}	

}
