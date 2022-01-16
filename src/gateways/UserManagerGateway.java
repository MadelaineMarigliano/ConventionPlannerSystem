package gateways;

import use_cases.UserManager;

import java.io.*;

/**
 * A UserManagerGateway provides a method that can save a UserManager to a file.
 * A UserManagerGateway provides a method that can read a UserManager from a file.
 */
public class UserManagerGateway {
    private final String path= "UserManagerFile.ser";

    /**
     * Saves the specified UserManager to the file "UserManagerFile.ser"
     *
     * @param um the UserManager that is required to be saved
     * @throws IOException if cannot find the file "UserManagerFile.ser"
     */
    public void saveToFile(UserManager um) throws IOException {
        OutputStream file = new FileOutputStream(path);
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutput output = new ObjectOutputStream(buffer);
        output.writeObject(um);
        output.close();
    }

    /**
     * Returns the UserManager that is read from the file "UserManagerFile.ser"
     *
     * @return the UserManager that is read from the file "UserManagerFile.ser"
     * @throws ClassNotFoundException if cannot find the class UserManager
     */
    public UserManager readFromFile() throws ClassNotFoundException{
        try{
            InputStream file = new FileInputStream(path);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);
            UserManager um = (UserManager) input.readObject();
            input.close();
            return um;
        }
        catch (IOException ex){
            System.out.println("Failure to read");
            return new UserManager();
        }

    }
}
