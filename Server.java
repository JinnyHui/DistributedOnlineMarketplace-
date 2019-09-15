// CSCI507 Assignment 3, Mar 5
// Honor Pledge:
//
// I pledge that I have neither given nor
// received any help on this assignment.
//
// hui

import java.lang.reflect.Proxy;
import java.rmi.Naming;

/**
 * This class contains the main class for server side
 * Use proxy and reflection patterns are used for login and action
 */

public class Server {
    public static void main(String[] args) {

        System.setSecurityManager(new SecurityManager());
        try{

            System.out.println("Creating Server... ");


//            if(args.length!=2){
//                System.out.println("Format: java -Djava.security.policy = policy Client [ip_address] [port]");
//                System.out.println("<---Please enter ip_address and port number--->");
//                System.exit(0);
//            }

            // server is fixed on machine 1, using port 2016
            String ip_address = "10.234.136.55";
            String port_number = "2016";

            //Location of Server
            String loginServer = "//"+ ip_address + ":" + port_number + "/LoginServer";
            String actionServer = "//" + ip_address + ":"+ port_number + "/ActionServer";

            System.out.println("Server: Binding it to name:"+ loginServer);

            // Use Reflection pattern, proxy to initiate the ServerLoginController
            MarketplaceLogin loginInterface = (MarketplaceLogin) Proxy.newProxyInstance(MarketplaceLogin.class.getClassLoader(),
                    new Class<?>[] {MarketplaceLogin.class},
                    new AuthorizationInvocationHandler(new ServerLoginController()));

            // Use Reflection pattern, proxy to initiate the ActionServerController
            ActionInterface actionInterface = (ActionInterface) Proxy.newProxyInstance(ActionInterface.class.getClassLoader(),
                    new Class<?>[] {ActionInterface.class},
                    new AuthorizationInvocationHandler(new ActionServerController()));

            //Binds the server to the RMI service for different uses: login or option
            Naming.rebind(loginServer, loginInterface);
            Naming.rebind(actionServer, actionInterface);


            System.out.println("Successful! Server is ready");

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
