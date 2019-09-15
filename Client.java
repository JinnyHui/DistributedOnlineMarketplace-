// CSCI507 Assignment 2, Feb 21
// Honor Pledge:
//
// I pledge that I have neither given nor
// received any help on this assignment.
//
// hui

import java.rmi.Naming;
import java.sql.SQLException;

public class Client {

    private static String ip_address;
    private static String port;

    public static void main(String[] args) throws java.rmi.RemoteException, SQLException {

        /**
         * updates in Ass5
         * take 2 arguments
         * gives error messages and exit if input is not correct
         */
        if(args.length!=2){
            System.out.println("Format: java -Djava.security.policy = policy Client [ip_address] [port_#]");
            System.out.println("Please enter ip_address and port number from: 10.234.136.55-60");
            System.exit(0);
        }

        /**
         * updates in Ass4
         * First argument: ip_address;
         * Second argument: port;
         */
        Client.setIp_address(args[0]);
        Client.setPort(args[1]);

        //Create new instance of a Front Controller
        FrontController frontController = new FrontController();

        //calling the remote validate function
        frontController.validate();
    }

    /**
     * updates in Ass4
     * define methods for setting up IP address and port number
     */
    public static void setIp_address(String ip_address){
        Client.ip_address = ip_address;
    }

    public static void setPort(String port){
        Client.port = port;
    }


    /**
     * updates in Ass4
     * define method for getting IP address and port number
     * @return client ip address, port number
     */
    public static String getIp_address() {
        return ip_address;
    }

    public static String getPort() {
        return port;
    }


    public MarketplaceLogin loginInterface() {

        MarketplaceLogin loginInterface = null;

        System.setSecurityManager(new SecurityManager());

        //Try-Catch for remote exceptions
        try {
			/**
             * updates in Ass4
			 * This is the hostname where our RMI server is running. 
			 * server can be specified by user
			 */
            String server = "//" + Client.getIp_address() + ":" + Client.getPort() + "/LoginServer";

            loginInterface = (MarketplaceLogin) Naming.lookup(server);

        } catch (Exception e) {
            System.err.println("Client Exception:" + e.getMessage());
            e.printStackTrace();
        }
        return loginInterface;
    }


    /**
     * Update in Assignment 3
     * This is for ActionInterface
     */
    public ActionInterface actionInterface(){
        ActionInterface actionInterface = null;

        System.setSecurityManager(new SecurityManager());

        //Try-Catch for remote exceptions
        try {
            // server name is different for action interface
            String serverAction = "//" + Client.getIp_address() + ":" + Client.getPort() + "/ActionServer";
            actionInterface = (ActionInterface) Naming.lookup(serverAction);

        } catch (Exception e) {
            System.err.println("Action Exception:"+ e.getMessage());
            e.printStackTrace();
        }
        return actionInterface;
    }

}