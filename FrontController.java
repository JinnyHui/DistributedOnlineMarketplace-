// CSCI507 Assignment 2, Feb 21
// Honor Pledge:
//
// I pledge that I have neither given nor
// received any help on this assignment.
//
// hui


import java.rmi.RemoteException;
import java.sql.SQLException;

/**
 * get parameters (userName and password) from loginView
 * pass parameters to client controller
 * and invoke the method in the server model to perform authentication
 */

public class FrontController {

    private Dispatcher dispatcher;
    private LoginView loginView;
    private ClientController clientController;
    private Credential credential;


    /**
     * Front Controller Constructor
     * @throws RemoteException
     */
    public FrontController() throws RemoteException{
        dispatcher = new Dispatcher();
        loginView = new LoginView();
        clientController = new ClientController();
    }

    /**
     * Attempt to authentic user login.
     * @return boolean value
     * */

    public void validate() throws RemoteException, SQLException{
        // get username and password from LoginView
        credential = loginView.loginMenu();

        Session session = clientController.authenticateUser(credential);
        if (session.isTruth() && (session.get_role()!=null)){
            System.out.println("Welcome "+ session.get_userName());

            // since user authenticated, request dispatch
            dispatchRequest(session);

        } else if(!session.isTruth() && (session.get_role() !=null)){
            System.out.println("Wrong password!");
        }
        else {
            System.out.println("You need to register!\n");
        }
    }


    /**
     * Responsible for dispatching the request to the Dispatcher.
     * get the parameter from the returned sessions
     * */

    public void dispatchRequest(Session session) throws RemoteException {
        //dispatch request
        dispatcher.dispatch(session);
    }
}