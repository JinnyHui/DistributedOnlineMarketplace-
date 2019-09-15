// CSCI507 Assignment 2, Feb 21
// Honor Pledge:
//
// I pledge that I have neither given nor
// received any help on this assignment.
//
// hui

/**
 * render the view of customer menu,
 * the options here are listed as examples not implemented.
 * In assignment 2, customer menu and admin menu have different options
 * In assignment 3, the view of customer and admin menus are the same but with RBAC
 */

public class CustomerMenu extends MenuView {

    public void getMenu() {
        System.out.println("-------------------------\n" +
                "Customer, welcome to the Market Place!\n" +
                "Please choose from the following options.\n");

        System.out.println("1. Browse Product");
        System.out.println("2. Add Product into Shopping Cart");
        System.out.println("3. Remove Product from Shopping Cart");
        System.out.println("4. Browse Shopping Cart");
        System.out.println("5. Update Shopping Cart");
        System.out.println("6. Add Product into Inventory (need admin role)");
        System.out.println("7. Remove Product from Inventory (need admin role)");
        System.out.println("8. Purchase");
        System.out.println("9. Logout");
        System.out.println("10. Update Product (need admin role)");
    }

}