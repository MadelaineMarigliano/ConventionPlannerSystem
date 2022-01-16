package use_cases;

import entities.User;

import java.io.Serializable;
import java.security.InvalidParameterException;
import java.util.ArrayList;

/**
 * A ContactManager provides a method that can get the contact list of a user.
 * A ContactManager provides a method that enforce the business rules of adding a username
 * to the personal contact list.
 * A ContactManager provides a method that enforce the business rules of removing a username
 * from the personal contact list.
 */
public class ContactManager implements Serializable{

    /**
     * Returns the personal contact list of the specified user.
     *
     * @param user a user
     * @return the personal contact list of the specified user
     */
    public ArrayList<String> getPersonalContact(User user){
        return user.getPersonalContact();
    }

    /**
     * Adds the username of userAdd to the personal contact list of user.
     * Adds the username of user to the be-added-to list of userAdd.
     *
     * @param user    the user who requires to add a username to its personal contact list
     * @param userAdd the user whose username is required to be added
     * @throws InvalidParameterException if the username of userAdd is already in user's personal contact list
     */
    public void addPersonalContact(User user, User userAdd) throws InvalidParameterException{
        String username = user.getUsername();
        String usernameAdd = userAdd.getUsername();
        if(user.getPersonalContact().contains(usernameAdd)){
            throw new InvalidParameterException("This user is already in your personal contact.");
        }else{
            user.addPersonalContact(usernameAdd);
            userAdd.addBeAddedTo(username);
        }
    }

    /**
     * Deletes the username of userDelete from the personal contact list of user.
     * Deletes the username of user from the be-added-to list of userDelete.
     *
     * @param user       the user who requires to delete a username from its personal contact list
     * @param userDelete the user whose username is required to be deleted
     * @throws InvalidParameterException if the username of userDelete is not in user's personal contact list
     */
    public void deletePersonalContact(User user, User userDelete) throws InvalidParameterException {
        String username = user.getUsername();
        String usernameDelete = userDelete.getUsername();

        if(!user.getPersonalContact().contains(usernameDelete)){
            throw new InvalidParameterException("This user is not in your personal contact.");
        }else{
            user.deletePersonalContact(usernameDelete);
            userDelete.deleteBeAddedTo(username);
        }

    }

    /**
     * Get be added to array list.
     *
     * @param user the user
     * @return the array list
     */
    public ArrayList<String> getBeAddedTo(User user){
        return user.getBeAddedTo();
    }


}
