// CSCI507 Assignment 2, Feb 21
// Honor Pledge:
//
// I pledge that I have neither given nor
// received any help on this assignment.
//
// hui


/**
 * This is a concrete factory implements AbstractFactory interface,
 * creates concrete product: menu view for Admin.
 */

public class AdminFactory implements AbstractFactory{

    // create an admin menu view
    public MenuView createMenu() {
        AdminMenu menuView= new AdminMenu();
        return menuView;
    }
}
