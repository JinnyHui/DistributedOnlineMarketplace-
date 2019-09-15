// CSCI507 Assignment 2, Feb 21
// Honor Pledge:
//
// I pledge that I have neither given nor
// received any help on this assignment.
//
// hui

/**
 * This is the interface for creating menus based on different system users (Admin/Client),
 * which is the abstract layer.
 */

public interface AbstractFactory {
    public MenuView createMenu();
}
