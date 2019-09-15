// CSCI507 Assignment 3, Mar 5
// Honor Pledge:
//
// I pledge that I have neither given nor
// received any help on this assignment.
//
// hui

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Interface for processing the actions from menu based on different rolls
 * calls remote method on the server
 * Pass the session and request objects as parameters, return response object
 * Component of the authorization pattern
 */

public interface ActionInterface extends Remote{

    /**
     *  Any role can: browse product
     *                browse shopping cart
     * @param session
     * @return response
     * @throws RemoteException
     */

    public Response browseProduct(Session session) throws RemoteException, IOException,SQLException;

    public Response checkAvailability(Product product) throws RemoteException,SQLException;


    /**
     * updates in Ass 5
     * valid actions for Customer only
     * @param session
     * @return response
     * @throws RemoteException
     */

    @RequiresRole("customer")
    public Response browseShoppingCart (Session session) throws RemoteException, SQLException;

    @RequiresRole ("customer")
    public Response addProductShoppingCart (Session session, ShoppingCart cart)throws RemoteException, SQLException;

    @RequiresRole ("customer")
    public Response removeProductShoppingCart (Session session, Product product)throws RemoteException, SQLException;

    @RequiresRole ("customer")
    public Response updateShoppingCart (Session session, Product product)throws RemoteException, SQLException;

    @RequiresRole ("customer")
    public Response purchase(Session session)throws RemoteException, IOException, SQLException;


    /**
     * updates in Ass 5
     * valid actions for Admin only
     * @param session
     * @return response
     * @throws RemoteException
     */
    @RequiresRole ("admin")
    public Response addProductInventory (Session session, Product product)throws IOException, RemoteException, SQLException;

    @RequiresRole ("admin")
    public Response removeProductInventory (Session session, Product product)throws IOException, RemoteException, SQLException;

    @RequiresRole ("admin")
    public Response updateProductInventory(Session session, Product product) throws RemoteException, SQLException;

}
