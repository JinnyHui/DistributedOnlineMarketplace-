// CSCI507 Assignment 3, Mar 5
// Honor Pledge:
//
// I pledge that I have neither given nor
// received any help on this assignment.
//
// hui

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;

/**
 * This is the server controller class for Action
 * It calls remote methods from the ActionServerModel
 */

public class ActionServerController extends UnicastRemoteObject implements ActionInterface{

    /**
     * list of methods
     */

    private static final long serialVersionUID = 1L;

    //Call for server model class for method invocation
    ActionServerModel actionServerModel;

    //Constructor
    protected ActionServerController() throws RemoteException {
        super();
    }

    @Override
    public Response browseProduct(Session session) throws IOException, SQLException {
        actionServerModel = new ActionServerModel();
        return actionServerModel.browse();
    }

    @Override
    public Response browseShoppingCart(Session session) throws RemoteException, SQLException {
        actionServerModel = new ActionServerModel();
        return actionServerModel.browseCart(session);
    }


    @Override
    public Response addProductShoppingCart(Session session, ShoppingCart cart) throws RemoteException, SQLException {
        actionServerModel =  new ActionServerModel();
        return actionServerModel.addCart(session, cart);
    }

    @Override
    public Response removeProductShoppingCart(Session session, Product product) throws RemoteException, SQLException {
        actionServerModel =  new ActionServerModel();
        return actionServerModel.removeCart(session, product);

    }

    @Override
    public Response updateShoppingCart(Session session, Product product) throws RemoteException, SQLException {
        actionServerModel =  new ActionServerModel();
        return actionServerModel.updateCart(session, product);
    }

    @Override
    public Response addProductInventory(Session session, Product product) throws RemoteException, IOException, SQLException {
        actionServerModel =  new ActionServerModel();
        return actionServerModel.addInventory(product);
    }

    @Override
    public Response removeProductInventory(Session session, Product product) throws RemoteException, SQLException {
        actionServerModel =  new ActionServerModel();
        return actionServerModel.removeInventory(product);
    }

    @Override
    public Response purchase(Session session) throws RemoteException, SQLException{
        actionServerModel =  new ActionServerModel();
        return actionServerModel.purchase(session);
    }

    @Override
    public Response updateProductInventory(Session session, Product product) throws RemoteException, SQLException {
        actionServerModel =  new ActionServerModel();
        return actionServerModel.updateInventory(product);
    }

    @Override
    public Response checkAvailability(Product product) throws RemoteException, SQLException {
        actionServerModel =  new ActionServerModel();
        return actionServerModel.checkInventory(product);

    }


}