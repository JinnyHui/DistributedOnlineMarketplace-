// CSCI507 Assignment 3, Mar 5
// Honor Pledge:
//
// I pledge that I have neither given nor
// received any help on this assignment.
//
// hui

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;

/**
 * Model of Action
 * Updated in Assignment 5 for synchronization and usage of database
 */

public class ActionServerModel {

    Response response;
    //Inventory inventory;

    //Constructor
    public ActionServerModel() throws RemoteException{
        super();
    }

    // set the message in response object
    // return the response object for displaying the message
    public Response browse() throws IOException, SQLException{
        response =new Response();
        response.setProductList(MySQL.selectProduct());
        return response;
    }

    public Response updateCart(Session session, Product product) throws SQLException {
        response =new Response();
        MySQL.updateCart(session, product);
        response.setMessage("Your cart is updated.");
        return response;
    }

    public Response removeCart(Session session, Product product) throws SQLException{
        response = new Response();
        Boolean flag = MySQL.removeFromCart(session, product);
        if (flag){
            response.setMessage("This product has been removed from your shopping cart.");
            System.out.println(response.getMessage());
        } else {
            response.setMessage("This product does not exist in your shopping cart.");
            System.out.println(response.getMessage());
        }

        return response;
    }

    public synchronized Response addInventory(Product product) throws IOException, SQLException {
        System.out.println("Adding Products!");
        response = new Response();
        // if the item is not exist, return false, !false = true
        if (!MySQL.isProductExist(product)){
            MySQL.addtoInventory(product);
            response.setMessage("Product:"+product.getProductName()+" added into inventory!");
            //System.out.println(response.getMessage());
        } else{
            response.setMessage("Product:" + product.getProductID()+" already exists");

        }
        System.out.println(response.getMessage());
        return response;
    }


    public Response browseCart(Session session) throws SQLException{
        response = new Response();
        response.setProductList(MySQL.browseFromCart(session));
        response.setMessage("You are browsing your shopping cart.");
        return response;
    }

    public synchronized Response addCart(Session session, ShoppingCart cart) throws SQLException{
        System.out.println("Adding Product to shopping cart!");
        response  = new Response();
        MySQL.addtoCart(session,cart);
        response.setMessage("Your shopping cart is ready!");
        System.out.println(response.getMessage());
        return response;
    }

    public synchronized Response removeInventory(Product product) throws SQLException{
        System.out.println("Removing products from inventory!");
        response = new Response();
        // if the product does not exist, return false
        if (MySQL.isProductExist(product)){
            MySQL.removeFromInventory(product);
            response.setMessage("Product:"+product.getProductName()+" removed from inventory!");
            System.out.println(response.getMessage());
        } else{
            response.setMessage("Product:" + product.getProductName()+"does not exist");
            System.out.println(response.getMessage());
        }
        //response.setMessage("You removed a product from inventory!");
        return response;
    }

    public synchronized Response purchase(Session session) throws SQLException{
        response = new Response();
        response = MySQL.purchaseCart(session);
        return response;
    }

    public synchronized Response updateInventory(Product product) throws SQLException{
        System.out.println("Updating product information from inventory!");
        response = new Response();
        // if the product does not exist, return false
        if (MySQL.isProductExist(product)){
            MySQL.updateProductInventory(product);
            response.setMessage("Product:"+product.getProductName()+" updated inventory!");
            System.out.println(response.getMessage());
        } else{
            response.setMessage("Product:" + product.getProductName()+ "does not exist");
            System.out.println(response.getMessage());
        }
        response.setMessage("You updated the inventory!");
        return response;
    }

    //check with item id, quantity is available in the inventory
    public Response checkInventory(Product product) throws SQLException {
        System.out.println("Checking requested items from inventory!");
        response = MySQL.checkInventory(product);
        return response;
    }


}
