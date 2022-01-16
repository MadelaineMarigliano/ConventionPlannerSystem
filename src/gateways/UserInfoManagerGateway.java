package gateways;

import use_cases.UserInfoManager;

import java.io.*;

/**
 * A UserInfoManagerGateway provides a method that can save a UserInfoManager to a file.
 * A UserInfoManagerGateway provides a method that can read a UserInfoManager from a file.
 */
public class UserInfoManagerGateway {
    private final String path= "UserInfoManagerFile.ser";

    /**
     * Saves the specified UserInfoManager to the file "UserInfoManagerFile.ser"
     *
     * @param userInfoManager the UserInfoManager that is required to be saved
     * @throws IOException if cannot find the file "UserInfoManagerFile.ser"
     */
    public void saveToFile(UserInfoManager userInfoManager) throws IOException {
        OutputStream file = new FileOutputStream(path);
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutput output = new ObjectOutputStream(buffer);
        output.writeObject(userInfoManager);
        output.close();
    }

    /**
     * Returns the UserInfoManager that is read from the file "UserInfoManagerFile.ser"
     *
     * @return the UserInfoManager that is read from the file "UserInfoManagerFile.ser"
     * @throws ClassNotFoundException if cannot find the class UserInfoManager
     */
    public UserInfoManager readFromFile() throws ClassNotFoundException{
        try{
            InputStream file = new FileInputStream(path);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);
            UserInfoManager userInfoManager = (UserInfoManager) input.readObject();
            input.close();
            return userInfoManager;
        }
        catch (IOException ex){
            return new UserInfoManager();
        }

    }
}

