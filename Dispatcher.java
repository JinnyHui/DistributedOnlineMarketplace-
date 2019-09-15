// CSCI507 Assignment 2, Feb 21
// Honor Pledge:
//
// I pledge that I have neither given nor
// received any help on this assignment.
//
// hui

import java.rmi.RemoteException;

/**
 * Dispatcher Class
 */
public class Dispatcher {

    ClientController clientController;
    Response response ;
    /**
     * Dispatcher Constructor
     */
    public Dispatcher() {
        clientController = new ClientController();
    }

    /**
     * Dispatch the view based on the the parameter.
     * @param session
     */

    public void dispatch(Session session) throws RemoteException {

        AbstractFactory abstractFactory = null;
        response = new Response();

        if(session.get_role().equalsIgnoreCase("customer")){
            abstractFactory = new CustomerFactory() ;
        } else {
            abstractFactory = new AdminFactory();
        }
        MenuView menuView = abstractFactory.createMenu();
        menuView.getMenu();
        response = clientController.processRequest(menuView.request(), session);
        //System.out.println(response.getMessage());

        /**
         * Updates in Ass 4
         * use while loop to let user stay in our application
         */
        while(menuView.continueshopping()){
            menuView.getMenu();
            clientController.processRequest(menuView.request(), session);
        }
    }
}