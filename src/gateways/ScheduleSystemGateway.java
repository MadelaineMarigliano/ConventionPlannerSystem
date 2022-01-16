package gateways;

import use_cases.ScheduleSystem;

import java.io.*;

/**
 * The type Schedule system gateway.
 */
public class ScheduleSystemGateway {
    private final String path= "ScheduleSystemFile.ser";

    /**
     * Save to file.
     *
     * @param rm the rm
     * @throws IOException the io exception
     */
    public void saveToFile(ScheduleSystem rm) throws IOException {
        OutputStream file = new FileOutputStream(path);
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutput output = new ObjectOutputStream(buffer);
        output.writeObject(rm);
        output.close();
    }

    /**
     * Read from file schedule system.
     *
     * @return the schedule system
     * @throws ClassNotFoundException the class not found exception
     */
    public ScheduleSystem readFromFile() throws ClassNotFoundException{
        try{
            InputStream file = new FileInputStream(path);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);
            ScheduleSystem rm = (ScheduleSystem) input.readObject();
            input.close();
            return rm;
        }
        catch (IOException ex){
            return new ScheduleSystem();
        }

    }
}
