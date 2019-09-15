// CSCI507 Assignment 4, Mar 31
// Honor Pledge:
//
// I pledge that I have neither given nor
// received any help on this assignment.
//
// hui

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart implements Serializable{

    /**
     * Default serialVersionID
     */
    private static final long serialVersionUID = 1L;
    private List<Product>cart;
    //private String message;

    // default constructor
    public ShoppingCart(){
        super();
        setCart(new ArrayList<Product>());
    }

    //return the products in cart
    public List<Product> getCart() {
        return cart;
    }

    //set the cart
    public void setCart(List<Product> cart) {
        this.cart = cart;
    }

    // add product to the array list
    public void addtoCart(Product product){
        cart.add(product);
    }


}
