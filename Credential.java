// CSCI507 Assignment 2, Feb 21
// Honor Pledge:
//
// I pledge that I have neither given nor
// received any help on this assignment.
//
// hui

//import javax.security.auth.login.CredentialException;

/**
 * Credential is a class contains user name and its corresponding password
 */

public class Credential {
    private String userName;
    private String password;

    /**
     * constructs a credential with user name and password
     * @param userName_input
     * @param password_input

    public Credential(String userName_input, String password_input){
    userName = userName_input;
    password = password_input;
    //role = role_input;
    }
     */


    /**
     * set the user name
     */
    public void set_userName(String userName_input) { this.userName = userName_input; }

    /**
     * set the user password
     */
    public void set_password(String password_input) { this.password = password_input; }

    /**
     * get the user name
     * @return userName of the credential
     */
    public String get_userName(){
        return userName;
    }

    /**
     * get the corresponding password
     * @return password
     */
    public String get_password(){
        return password;
    }




}