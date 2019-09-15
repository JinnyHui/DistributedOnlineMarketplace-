// CSCI507 Assignment 2, Feb 21
// Honor Pledge:
//
// I pledge that I have neither given nor
// received any help on this assignment.
//
// hui

//import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;

/**
 * handle validation method
 */

public class ServerLoginController extends UnicastRemoteObject implements MarketplaceLogin{

    private static final long serialVersionUID = 1L;

    protected ServerLoginController() throws RemoteException { super(); }


    /**
     * Initiate the serverloginmodel class for remote method call
     * @param userName
     * @param password
     * @return session object from server by calling the isAuthenticated method in model
     * @throws RemoteException
     */

    public Session validateCredential(String userName, String password) throws RemoteException, SQLException  {

        // call remote method
        ServerLoginModel loginModel = new ServerLoginModel();
        return loginModel.isAuthenticated(userName, password);
    }
}