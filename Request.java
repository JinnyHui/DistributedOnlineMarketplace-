// CSCI507 Assignment 2, Feb 21
// Honor Pledge:
//
// I pledge that I have neither given nor
// received any help on this assignment.
//
// hui

import java.io.Console;
import java.io.Serializable;
import java.util.List;


/**
 * updated in Ass 5
 * User request class,
 * Save user choice from menu in String action.
 */

public class Request implements Serializable{

    private static final long serialVersionUID = 1L;
    private String action;

    //default constructor
    public Request(){
        super();
    }

    /**
     * use String action to store user's choice
     * @param action
     */
    public void SetAction(String action) {
        this.action = action;
    }


    /**
     * get back the action
     * @return
     */
    public String getAction() {
        return action;
    }


    public static Product inputProduct(){
        Product product = new Product();
        Console console  = System.console();
        System.out.println("Product ID:");
        product.setProductID(Integer.parseInt(console.readLine()));
        System.out.println("Product Name:");
        product.setProductName(console.readLine());
        System.out.println("Product Price:");
        product.setPrice(Float.valueOf(console.readLine()));
        System.out.println("Product Quantity:");
        product.setQuantity(Integer.parseInt(console.readLine()));
        return product;
    }

    public static Product removeProduct() {
        Product product = new Product();
        Console console = System.console();
        //get product id that need to be removed
        System.out.println("Product ID:");
        product.setProductID(Integer.parseInt(console.readLine()));
        return product;
    }

    // to add item into shopping cart, get the item id and item quantity, return item object
    public Product chooseProduct(List<Product> itemlist2) {
        Product product = new Product();
        System.out.println("Please input the product ID: ");
        Console console = System.console();

        int product_id = Integer.parseInt(console.readLine());
        int index = -1;
        for(int i = 0; i < itemlist2.size(); i++) {
            if (itemlist2.get(i).getProductID() == product_id) {
                index = i;
                break;
            }
        }

        // get the chosen item details
        System.out.println("How many do you want to put into Shopping Cart?");
        console = System.console();
        int quantity = Integer.parseInt(console.readLine());
        product.setProductID(product_id);
        product.setQuantity(quantity);
        product.setProductName(itemlist2.get(index).getProductName());
        product.setPrice(itemlist2.get(index).getPrice());

        return product;
    }

    public Boolean askContinue() {
        Boolean flag = false;
        Console console = System.console();
        System.out.println("Do you want to add more? y/n");
        String key = console.readLine();
        if(key.equalsIgnoreCase("y")){
            flag = true;
        }
        return flag;
    }

}