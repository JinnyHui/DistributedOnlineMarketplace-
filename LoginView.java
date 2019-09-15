// CSCI507 Assignment 2, Feb 21
// Honor Pledge:
//
// I pledge that I have neither given nor
// received any help on this assignment.
//
// hui


import java.io.Console;
import java.rmi.RemoteException;

/**
 * This class presents the view for users to login
 * use console to get user input
 */

public class LoginView {

    //to get the user input
    Console console;

    /**
     * get user name and password
     * @return Credential object
     * @throws RemoteException
     */

    public Credential loginMenu() throws RemoteException {

        console = System.console();
        System.out.println("******** Login Menu ********\n");
        Credential credential = new Credential();

        System.out.print("Enter UserName -> ");
        credential.set_userName(console.readLine());

        System.out.print("Enter Password -> ");
        credential.set_password(String.valueOf(console.readPassword()));

        // return this credential object
        return credential;
    }
}