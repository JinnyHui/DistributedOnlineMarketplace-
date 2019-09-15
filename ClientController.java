// CSCI507 Assignment 2, Feb 21
// Honor Pledge:
//
// I pledge that I have neither given nor
// received any help on this assignment.
//
// hui

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for client side
 */
public class ClientController {

    private Client client;
    //Response response;
    //private static ShoppingCart shoppingCart;

    /**
     * This method is used for authenticating user
     * @param credential
     * @throws RemoteException
     * @return session
     */
    public Session authenticateUser(Credential credential) throws RemoteException, SQLException {
        client = new Client();
        MarketplaceLogin loginInterface = client.loginInterface();
        return loginInterface.validateCredential(credential.get_userName(), credential.get_password());
    }

    /**
     * Update in Assignment 3
     * This method is used to process the response from menus,
     * remote method will be called to get response object
     * @param request user request from menu
     * @param session login session
     * @return response object
     * @throws RemoteException
     */

    public Response processRequest(Request request, Session session) throws RemoteException {
        Response  response= new Response();
        try{
            client  = new Client();
            Request request2 = new Request();
            ActionInterface  actionInterface = client.actionInterface();

			/**
			 * Invoke the remote method
             * passing the session and request objects as parameters
			 */

            /**
             *  Updates for Ass 4
             *  deal with comments for Ass3
             *  Updates for Ass 5
             *  implement all the functionality with MySQL
             */

            //Action 1: Browse Product
            if (Integer.parseInt(request.getAction()) == 1) {
                response = actionInterface.browseProduct(session);
                response.displayInventory(response.getProductList());
            }

            //Action 2: Add Product into Shopping Cart
            else if(Integer.parseInt(request.getAction()) == 2) {
                response = actionInterface.browseProduct(session);
                response.displayInventory(response.getProductList());
                List<Product> productList = new ArrayList<Product>();
                productList = response.getProductList();
                Boolean flag = true;
                ShoppingCart cart = new ShoppingCart();
                while (flag) {
                    Product product = new Product();
                    product = request.chooseProduct(productList);
                    response = actionInterface.checkAvailability(product);
                    if (response.getMessage().equalsIgnoreCase("y")) {
                        cart.addtoCart(product);
                        flag = request2.askContinue();
                    } else {
                        response.displayMessage();
                    }
                }
                response = actionInterface.addProductShoppingCart(session, cart);
            }

            //Action 3: Remove Product from Shopping Cart
            else if(Integer.parseInt(request.getAction()) == 3) {
                response = actionInterface.browseShoppingCart(session);
                response.displayInventory(response.getProductList());
                Product product_remove = new Product();
                product_remove = request.removeProduct();
                //System.out.println(product_remove.getProductID());
                response = actionInterface.removeProductShoppingCart(session, product_remove);
                response.displayMessage();
            }

            //Action 4: Browse Shopping Cart
            else if(Integer.parseInt(request.getAction()) == 4) {
                response = actionInterface.browseShoppingCart(session);
                response.displayInventory(response.getProductList());
            }

            //Action 5: Update Shopping Cart
            else if(Integer.parseInt(request.getAction()) == 5) {
                response = actionInterface.browseShoppingCart(session);
                //response.displayInventory(response.getProductList());
                Product product_updated = request.chooseProduct(response.getProductList());
                response = actionInterface.checkAvailability(product_updated);
                if (response.getMessage().equalsIgnoreCase("y")) {
                    response = actionInterface.updateShoppingCart(session, product_updated);
                }else{
                    response.displayMessage();
                }
            }

            //Action 6: Add Product into Inventory
            else if(Integer.parseInt(request.getAction()) == 6) {
                Product product = Request.inputProduct();
                response = actionInterface.addProductInventory(session, product);
                response.displayMessage();
            }

            //Action 7: Remove Product from Inventory
            else if(Integer.parseInt(request.getAction()) == 7) {
                Product product = Request.removeProduct();
                response = actionInterface.removeProductInventory(session, product);
                response.displayMessage();
            }

            //Action 8: Purchase Products in Shopping Cart
            else if(Integer.parseInt(request.getAction()) == 8) {
                response = new Response();
                System.out.println("********Your order has been placed!********\n");
                response = actionInterface.browseShoppingCart(session);
                response.displayInventory(response.getProductList());
                response = actionInterface.purchase(session);
                //response.displayMessage();
            }

            //Action 9: Log out
            else if(Integer.parseInt(request.getAction()) == 9) {
                System.exit(0);
            }

            /**
             * Action 10: Update Product
             * This option is newly added in ass 5
             * admin can modify product information,
             */
            else if(Integer.parseInt(request.getAction()) == 10) {
                Product product = Request.inputProduct();
                response = actionInterface.updateProductInventory(session, product);
            }

            //Invalid Input
            else {
               System.out.println("Invalid Choice! Choose again");
            }

        }catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return response;
    }
}
