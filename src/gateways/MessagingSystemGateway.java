package gateways;

import use_cases.MessagingSystem;

import java.io.*;

/**
 * The type Messaging system gateway.
 */
public class MessagingSystemGateway {
    private final String path= "MessagingSystemFile.ser";

    /**
     * Save to file.
     *
     * @param rm the rm
     * @throws IOException the io exception
     */
    public void saveToFile(MessagingSystem rm) throws IOException {
        OutputStream file = new FileOutputStream(path);
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutput output = new ObjectOutputStream(buffer);
        output.writeObject(rm);
        output.close();
    }

    /**
     * Read from file messaging system.
     *
     * @return the messaging system
     * @throws ClassNotFoundException the class not found exception
     */
    public MessagingSystem readFromFile() throws ClassNotFoundException{
        try{
            InputStream file = new FileInputStream(path);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);
            MessagingSystem rm = (MessagingSystem) input.readObject();
            input.close();
            return rm;
        }
        catch (IOException ex){
            return new MessagingSystem();
        }

    }
}
