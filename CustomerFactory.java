// CSCI507 Assignment 2, Feb 21
// Honor Pledge:
//
// I pledge that I have neither given nor
// received any help on this assignment.
//
// hui


/**
 * This is a concrete factory implements AbstractFactory interface,
 * creates concrete product: menu view for Customer.
 */

public class CustomerFactory implements AbstractFactory {

    //create a customer menu view()
    public MenuView createMenu(){
        CustomerMenu menuView = new CustomerMenu();
        return menuView;
    }
}