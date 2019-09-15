// CSCI507 Assignment 2, Feb 21
// Honor Pledge:
//
// I pledge that I have neither given nor
// received any help on this assignment.
//
// hui

/**
 * render the view of admin menu
 */

public class AdminMenu extends MenuView{

    public void getMenu() {
        System.out.println("-------------------------\n" +
                "Administrator, welcome to the Market Place!\n" +
                " Please choose from the following options.\n");
        System.out.println("1. Browse Product");
        System.out.println("2. Add Product into Shopping Cart (need customer role)");
		System.out.println("3. Remove Product from Shopping Cart (need customer role)");
        System.out.println("4. Browse Shopping Cart (need customer role)");
        System.out.println("5. Update Shopping Cart (need customer role)");
        System.out.println("6. Add Product into Inventory");
		System.out.println("7. Remove Product from Inventory");
		System.out.println("8. Purchase (need customer role)");
		System.out.println("9. Logout");
        System.out.println("10. Update Product");
    }

}
