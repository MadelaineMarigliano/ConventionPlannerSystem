package gateways;

import use_cases.ContactManager;

import java.io.*;

/**
 * A ContactManagerGateway provides a method that can save a ContactManager to a file.
 * A ContactManagerGateway provides a method that can read a ContactManager from a file.
 */
public class ContactManagerGateway {
    private final String path= "ContactManagerFile.ser";

    /**
     * Saves the specified ContactManager to the file "ContactManagerFile.ser"
     *
     * @param cm the ContactManager that is required to be saved
     * @throws IOException if cannot find the file "ContactManagerFile.ser"
     */
    public void saveToFile(ContactManager cm) throws IOException {
        OutputStream file = new FileOutputStream(path);
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutput output = new ObjectOutputStream(buffer);
        output.writeObject(cm);
        output.close();
    }

    /**
     * Returns the ContactManager that is read from the file "ContactManagerFile.ser".
     *
     * @return the ContactManager that is read from the file "ContactManagerFile.ser"
     * @throws ClassNotFoundException if cannot find the class ContactManager
     */
    public ContactManager readFromFile() throws ClassNotFoundException{
        try{
            InputStream file = new FileInputStream(path);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);
            ContactManager cm = (ContactManager) input.readObject();
            input.close();
            return cm;
        }
        catch (IOException ex){
            return new ContactManager();
        }

    }
}
