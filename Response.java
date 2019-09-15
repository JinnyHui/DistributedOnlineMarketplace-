// CSCI507 Assignment 3, Mar 5
// Honor Pledge:
//
// I pledge that I have neither given nor
// received any help on this assignment.
//
// hui

/**
 * return the message from remote server
 * message content is set according to different options
 */

import java.io.Serializable;
import java.io.Console;
import java.util.List;

public class Response implements Serializable {

    private static final long serialVersionUID = 1L;
    private String message;
    private Product product;
    private List<Product> productList;


    /**
     * updates in Ass5, passing the whole object as parameter
     * Set different message for different actions
     */

    public String getMessage() {
        return message;
    }


    public void setMessage(String message) {
        this.message = message;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


    public void displayInventory(List<Product> productList2) {
        System.out.println("******** Product List *******\n");
        for(Product product : productList2){
            System.out.println("ID:"+ product.getProductID() + "\n" + "Name:"+ product.getProductName()+"\n"+
                    "Price:"+ product.getPrice()+"\n" + "Quantity:"+ product.getQuantity());
        }
    }

    public void displayMessage() {
        System.out.println(message);
    }

}