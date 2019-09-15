// CSCI507 Assignment 3, Mar 5
// Honor Pledge:
//
// I pledge that I have neither given nor
// received any help on this assignment.
//
// hui

/**
 * Create a new class of custom exceptions---AuthorizationException
 * Triggered when
 */


public class AuthorizationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     *  Default Constructor
     */

    public AuthorizationException(){
        super("Invalid Authorization - Access Denied!");
    }

    /**
     * Constructor with parameter
     * @param methodName the name of the method being called by unauthorized roll
     */

    public AuthorizationException(String methodName) {
        super("Invalid Authorization - Access Denied to " + methodName + "() function!");
    }
}
