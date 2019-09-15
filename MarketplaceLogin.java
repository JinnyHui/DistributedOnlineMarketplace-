// CSCI507 Assignment 2, Feb 21
// Honor Pledge:
//
// I pledge that I have neither given nor
// received any help on this assignment.
//
// hui


/**
 * This is the interface that enables communication between server and client.
 * In the second assignment, the login function is implemented.
 * Based on the role of user, system will validate user's username and password.
 * If the username and password match,
 * the system will show user different views depending on user's role stored in validation file.
 */
import java.sql.SQLException;

public interface MarketplaceLogin extends java.rmi.Remote {
    /**
     * Get the number of users login and throw exception if there is any.
     * @param userName_input user name input from console
     * @param password_input the corresponding password used to login the system
     * @return userID the user ID number
     * @throws java.rmi.RemoteException
     */
    Session validateCredential(String userName_input, String password_input) throws java.rmi.RemoteException, SQLException;

}