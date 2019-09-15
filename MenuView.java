// CSCI507 Assignment 2, Feb 21
// Honor Pledge:
//
// I pledge that I have neither given nor
// received any help on this assignment.
//
// hui

/**
 * Abstract class for menu product
 * This class also handles the action that chosen by user from menu
 * User choice is saved in request object
 */

import java.io.Console;

abstract class MenuView {

    Request request;

    public MenuView(){
        super();
        request = new Request();
    }

    //Get the menu context
    abstract void getMenu();

    //get user choice form console, store it with request
    public Request request(){
        Console console = System.console();
        System.out.println("Input your choice: ");
        request.SetAction (console.readLine());
        return request;
    }

    /**
     * update in Ass 4
     * used to maintain user in our application until user choose to log out
     */

    public boolean continueshopping(){
        boolean continueShopping = false;
        Console console = System.console();
        System.out.println("Continue Shopping? y/n");
        if(console.readLine().equalsIgnoreCase("y")){
            continueShopping = true;
        }
        return continueShopping;
    }

}