package gateways;

import use_cases.RoomManager;

import java.io.*;

/**
 * The type Room manager gateway.
 */
public class RoomManagerGateway {
    private final String path= "RoomManagerFile.ser";

    /**
     * Save to file.
     *
     * @param rm the rm
     * @throws IOException the io exception
     */
    public void saveToFile(RoomManager rm) throws IOException {
        OutputStream file = new FileOutputStream(path);
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutput output = new ObjectOutputStream(buffer);
        output.writeObject(rm);
        output.close();
    }

    /**
     * Read from file room manager.
     *
     * @return the room manager
     * @throws ClassNotFoundException the class not found exception
     */
    public RoomManager readFromFile() throws ClassNotFoundException{
        try{
            InputStream file = new FileInputStream(path);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);
            RoomManager rm = (RoomManager) input.readObject();
            input.close();
            return rm;
        }
        catch (IOException ex){
            return new RoomManager();
        }

    }
}
