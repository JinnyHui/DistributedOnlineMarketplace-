// CSCI507 Assignment 4, Mar 31
// Honor Pledge:
//
// I pledge that I have neither given nor
// received any help on this assignment.
//
// hui

import java.io.Serializable;

/**
 * This class constructs Product
 * Each product instance consists of productID, productName, price, and quantity
 */

public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    private int productID;
    private String productName;
    private Float price;
    private int quantity;

    //default constructor
    public Product(){
        super();
    }

    //constructor with parameters
    public Product(int ID, String name, Float price, int quantity){
        this.productID = ID;
        this.productName = name;
        this.price = price;
        this.quantity = quantity;
    }

    //methods to modify and get properties of product
    public void setProductID(int ID){
        this.productID = ID;
    }

    public int getProductID(){
        return productID;
    }

    public void setProductName(String name){
        this.productName = name;
    }

    public String getProductName(){
        return productName;
    }

    public void setPrice(Float price){
        this.price = price;
    }

    public Float getPrice(){
        return price;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public  int getQuantity(){
        return quantity;
    }

}
