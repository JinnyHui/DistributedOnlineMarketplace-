// CSCI507 Assignment 3, Mar 5
// Honor Pledge:
//
// I pledge that I have neither given nor
// received any help on this assignment.
//
// hui


import java.rmi.RemoteException;
import java.sql.SQLException;

/**
 * Update in assignment 5
 * This is the model class for login at server side
 * Only one method is included: isAuthenticated(),
 * which validates the username and password with database
 */

public class ServerLoginModel {

    public ServerLoginModel() throws RemoteException{
        super();
    }

    // Authenticate the user by input username and password, and return session object
    public Session isAuthenticated(String userName, String password) throws SQLException{

        String[] token;
        Session session = null;
        String role = null;
        boolean truth = false;

        System.out.println("Now validating user ->" + userName);


        /**
		 * Use MySQL to search the username, check the password and get the role
		 * Return the authentication result (T/F)
		 */

        String credential = MySQL.checkCredential(userName, password);

        if(credential !=null){
            // Use the username as key for checking
            token = credential.split("\t");
            role = token[2];
            truth = true;
            session = new Session(userName, role, truth);
            System.out.println("Successful Authentication!");
        }
        // If user name is right, but the password is not right
        else{
            role = "none";
            session  = new Session (userName, role, false);
            System.out.println("Failed Authentication!");
        }
        // if authenticated successful, return session object
        return session;
    }
}
