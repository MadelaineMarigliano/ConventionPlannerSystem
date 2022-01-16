package gateways;

import use_cases.StatsManager;

import java.io.*;

/**
 * The type Stats manager gateway.
 */
public class StatsManagerGateway {
    private final String path= "StatsManagerFile.ser";

    /**
     * Save to file.
     *
     * @param statsManager the stats manager
     * @throws IOException the io exception
     */
    public void saveToFile(StatsManager statsManager) throws IOException {
        OutputStream file = new FileOutputStream(path);
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutput output = new ObjectOutputStream(buffer);
        output.writeObject(statsManager);
        output.close();
    }

    /**
     * Read from file stats manager.
     *
     * @return the stats manager
     * @throws ClassNotFoundException the class not found exception
     */
    public StatsManager readFromFile() throws ClassNotFoundException{
        try{
            InputStream file = new FileInputStream(path);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);
            StatsManager statsManager = (StatsManager) input.readObject();
            input.close();
            return statsManager;
        }
        catch (IOException ex){
            return new StatsManager();
        }
    }
}
