// CSCI507 Assignment 3, Mar 5
// Honor Pledge:
//
// I pledge that I have neither given nor
// received any help on this assignment.
//
// hui


import java.io.Serializable;

/**
 * Session class for validation
 * session variables: userName, role, and truth
 */

public class Session implements Serializable {

    /**
     * Session object with serializable number
     */
    private static final long serialVersionUID = 1L;

    private String userName;
    private String role;
    private boolean truth;

    //default constructor
    public Session(){
        super();
    }

    //constructor with parameters
    public Session(String userName, String role, boolean truth){
        this.set_userName(userName);
        this.set_role(role);
        this.set_truth (truth);
    }

    /**
     * @return userName
    */
    public String get_userName() {
        return userName;
    }

    public void set_userName(String userName) {
        this.userName = userName;
    }

    /**
     * @return role
    */
    public String get_role() {
        return role;
    }

    public void set_role(String role) {
        this.role = role;
    }

    /**
     * @return the boolean value,
     * whether the user is authenticated or not
    */
    public boolean isTruth() {
        return truth;
    }
    public void set_truth(boolean truth) {
        this.truth = truth;
    }


}