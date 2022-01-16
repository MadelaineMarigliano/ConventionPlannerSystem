package gateways;

import use_cases.EventSystem;

import java.io.*;

/**
 * The type Event system gateway.
 */
public class EventSystemGateway {
    private final String path= "EventSystemFile.ser";

    /**
     * Save to file.
     *
     * @param rm the rm
     * @throws IOException the io exception
     */
    public void saveToFile(EventSystem rm) throws IOException {
        OutputStream file = new FileOutputStream(path);
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutput output = new ObjectOutputStream(buffer);
        output.writeObject(rm);
        output.close();
    }

    /**
     * Read from file event system.
     *
     * @return the event system
     * @throws ClassNotFoundException the class not found exception
     */
    public EventSystem readFromFile() throws ClassNotFoundException{
        try{
            InputStream file = new FileInputStream(path);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);
            EventSystem rm = (EventSystem) input.readObject();
            input.close();
            return rm;
        }
        catch (IOException ex){
            return new EventSystem();
        }

    }
}
