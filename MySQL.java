// CSCI507 Assignment 5, Apr 15
// Honor Pledge:
//
// I pledge that I have neither given nor
// received any help on this assignment.
//
// hui

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.DatabaseMetaData;

public class MySQL {
    private static final String hostname ="localhost:3306/";
    private static final String dbName = "hui_db";

    private static final String url = "jdbc:mysql://" + hostname + dbName  ;
    private static final String username = "hui";
    private static final String password = "132052Hui";

    /**
     * establish connection to MySql database: hui_db
     */
    public static Connection getConnection() {

        System.out.println("Connecting database...");

        Connection connection = null;

        try {
            connection = (Connection) DriverManager.getConnection(url, username, password);
            System.out.println("Database connected!");
        } catch(SQLException e) {

            throw new IllegalStateException("Cannot connect the database!", e);
        }
        return connection;
    }

    /**
     * Check credential from database
     * @param username
     * @param password
     * @return
     * @throws SQLException
     */
    public static String checkCredential(String username, String password) throws SQLException {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String credential = null;
        String Select_Query ="SELECT * FROM hui_db.tbl_credential WHERE userName = \'" + username + "\'";
        System.out.println("user:"+username + "    pass:"+password);
        try{
            // select the user query
            connection = getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(Select_Query);
            while(resultSet.next()) {
                // return the username and password

                String user = resultSet.getString("userName");
                String pass = resultSet.getString("password");
                String role = resultSet.getString("role");

                if(username.equals(user) && password.equals(pass)) {
                    credential = user + "\t"+ pass + "\t" + role;
                    System.out.println("--->"+credential);
                } else{
                    System.out.println("Wrong password!");
                }
            }
        }catch (SQLException e){
            System.err.println("Unable execute query!");
            e.printStackTrace();
        } finally {

            resultSet.close();
            statement.close();

            if (connection != null) {
                connection.close();
            }
        }
        return credential;
    }

    /**
     * get product list from database tbl_product
     */
    public static List<Product> selectProduct() throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        List<Product> productList = new ArrayList<Product>();
        Product product;

        String Select_Product_Query = "SELECT * FROM hui_db.tbl_product";

        // Get product list from tbl_product
        try {
            connection = getConnection();
            statement = connection.createStatement();
            try{
                rs = statement.executeQuery(Select_Product_Query);
                while(rs.next()) {
                    product = new Product();
                    product.setProductID(rs.getInt("id"));
                    product.setProductName(rs.getString("name"));
                    product.setPrice(rs.getFloat("price"));
                    product.setQuantity(rs.getInt("quantity"));
                    productList.add(product);
                }
            } catch (SQLException e){
                System.err.println("Unable to get items!");
                e.printStackTrace();
            }

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        finally{
            if (statement != null){
                statement.close();
            }
            if (connection != null){
                connection.close();
            }
        }
        return productList;
    }

    /**
     * before removing, adding any product in inventory, check if certain product exists in inventory.
     * @param product
     * @return flag
     * @throws SQLException
     */
    public static boolean isProductExist(Product product) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        Boolean flag = false;
        String product_query = "SELECT * FROM hui_db.tbl_product WHERE id = " + product.getProductID();

        try {
            connection = getConnection();
            statement = connection.createStatement();
            try{
                rs = statement.executeQuery(product_query);
                //if product exist
                if(rs.next()) {
                    flag = true;
                }
            } catch (SQLException e){
                System.err.println("Unable to query database!");
                e.printStackTrace();
            }

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        finally{
            rs.close();
            statement.close();
            if (connection != null){
                connection.close();
            }
        }
        return flag;
    }

    /**
     * add new product into tbl_product
     * @param product
     * @throws SQLException
     */
    public static void addtoInventory(Product product) throws SQLException {
        Connection connection = null;
        PreparedStatement ps = null;

        String insertProduct = "INSERT INTO hui_db.tbl_product (id, name, price, quantity) VALUES (?,?,?,?)";

        try{
            connection = getConnection();
            // create the mysql insert preparedstatement
            ps = connection.prepareStatement(insertProduct);
            ps.setInt (1, product.getProductID());
            ps.setString (2, product.getProductName());
            ps.setFloat   (3, product.getPrice());
            ps.setInt(4, product.getQuantity());
            ps.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        } finally {
            if(ps !=null){
                ps.close();
            }
            if (connection !=null){
                connection.close();
            }
        }
        System.out.println("Product is inserted into inventory");
    }


    /**
     * remove a certain product from inventory
     * @param product
     * @throws SQLException
     */
    public static void removeFromInventory(Product product) throws SQLException {
        Connection connection = null;
        PreparedStatement ps = null;

        String deleteProduct = "DELETE FROM hui_db.tbl_product WHERE id=?";

        try{
            connection = getConnection();
            // create the mysql DELETE prepared statement
            ps = connection.prepareStatement(deleteProduct);
            ps.setInt (1, product.getProductID());
            ps.executeUpdate();
            System.out.println("Product is removed from inventory");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        } finally {
            if(ps !=null){
                ps.close();
            }
            if (connection !=null){
                connection.close();
            }
        }
    }

    /**
     * update product information in database
     * @param product
     * @throws SQLException
     */
    public static void updateProductInventory(Product product) throws SQLException {
        Connection connection = null;
        PreparedStatement ps = null;

        String updateItem = "UPDATE hui_db.tbl_product SET name =?, price =?, quantity = ? WHERE id=?";

        try{
            connection = getConnection();
            // create the mysql update preparedstatement
            ps = connection.prepareStatement(updateItem);
            ps.setString (1, product.getProductName());
            ps.setFloat(2, product.getPrice());
            ps.setInt(3, product.getQuantity());
            ps.setInt(4, product.getProductID());
            ps.executeUpdate();

            System.out.println("Product is updated in inventory");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        } finally {
            if(ps !=null){
                ps.close();
            }
            if (connection !=null){
                connection.close();
            }
        }
    }

    /**
     * before removing, adding any product in shopping cart, check if certain product exists in shopping cart.
     * @param session
     * @param product
     * @return flag
     * @throws SQLException
     */
    public static boolean isProductExistCart(Session session, Product product) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        Boolean flag_cart = false;
        String name = session.get_userName();
        int id = product.getProductID();
        String product_cart_query = "SELECT * FROM tbl_shoppingcart WHERE userName = \'" + name + " \' AND product_id = " + id;

        try {
            connection = getConnection();
            statement = connection.createStatement();
            try{
                rs = statement.executeQuery(product_cart_query);
                //if product exist
                if(rs.next()) {
                    flag_cart = true;
                }
            } catch (SQLException e){
                System.err.println("Unable to query database!");
                e.printStackTrace();
            }

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        finally{
            rs.close();
            statement.close();
            if (connection != null){
                connection.close();
            }
        }
        return flag_cart;
    }

    /**
     * add product into shopping cart
     * tbl_shoppingcart, PK is: userName and product_id
     * @param session
     * @param cart
     * @throws SQLException
     */
    public static void addtoCart(Session session, ShoppingCart cart) throws SQLException {
        System.out.println("adding to cart");
        Connection connection = null;
        PreparedStatement ps = null;

        String insertProductToCart = "INSERT INTO hui_db.tbl_shoppingcart (product_id, userName, quantity) VALUES (?,?,?)";
        //String update_quantity = "UPDATE hui_db.shoppingcart SET quantity =? WHERE product_id = ? AND userName = ?";

        try {
            connection = getConnection();
            for (Product product: cart.getCart()){
                ps = connection.prepareStatement(insertProductToCart);
                ps.setInt(1, product.getProductID());
                ps.setString(2, session.get_userName());
                ps.setInt(3, product.getQuantity());
                ps.executeUpdate();
            }
            System.out.println("Products added to your cart!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if(ps !=null){
                ps.close();
            }
            if (connection !=null){
                connection.close();
            }
        }
    }

    /**
     * check availability of a product
     * @param product
     * @return
     * @throws SQLException
     */
    public static Response checkInventory(Product product) throws SQLException{
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        Response response = new Response();
        //Boolean flag = false;
        String Product_query = "SELECT * FROM hui_db.tbl_product WHERE id = " + product.getProductID();
        // Get item list from the Inventory
        try {
            System.out.println("Checking product inventory in MySQL");
            connection = getConnection();
            statement = connection.createStatement();
            try{
                rs = statement.executeQuery(Product_query);
                //in this case, means at least there is one result
                if(rs.next()) {
                    //once product exist, check quantity
                    int quantity_request = product.getQuantity();
                    int quantity_available = rs.getInt("quantity");
                    if(quantity_available>=quantity_request){
                        response.setMessage("y");// for return the available

                    }else{
                        response.setMessage("You requested too much quantity, exceeding the limit!");
                    }
                }else{
                    response.setMessage("product does not exist!");
                }
            } catch (SQLException e){
                System.err.println("Unable to query database!");
                e.printStackTrace();
            }

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        finally{
            rs.close();
            statement.close();
            if (connection != null){
                connection.close();
            }
        }

        return response;
    }

    /**
     * browse all the products in user's shopping cart
     * @param session
     * @return
     * @throws SQLException
     */
    public static List<Product> browseFromCart(Session session) throws SQLException{
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        String name = session.get_userName();
        List<Product> productList = new ArrayList<Product>();
        Product product;

        String Select_Product_Query = "SELECT tbl_product.name, tbl_product.id, tbl_product.price, tbl_shoppingcart.quantity " +
                "FROM tbl_product " +
                "INNER JOIN tbl_shoppingcart " +
                "ON tbl_shoppingcart.product_id = tbl_product.id " +
                "WHERE tbl_shoppingcart.userName = \'" + name + "\'";

        // Get item list from the Inventory
        try {
            connection = getConnection();
            statement = connection.createStatement();
            try{
                System.out.println("Browse products from shoppingcart!");
                rs = statement.executeQuery(Select_Product_Query);
                while(rs.next()) {
                    product = new Product();
                    product.setProductName(rs.getString("name"));
                    product.setProductID(rs.getInt("id"));
                    product.setPrice(rs.getFloat("price"));
                    product.setQuantity(rs.getInt("quantity"));
                    productList.add(product);
                }
            } catch (SQLException e){
                System.err.println("Unable to get products!");
                e.printStackTrace();
            }

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        finally{
            if (statement != null){
                statement.close();
            }
            if (connection != null){
                connection.close();
            }
        }

        return productList;
    }


    /**
     * remove certain product out of shopping cart by input product id
     * @param session
     * @param product
     * @throws SQLException
     */
    public static Boolean removeFromCart(Session session, Product product) throws SQLException{
        Connection connection = null;
        PreparedStatement ps = null;
        Statement statement = null;
        ResultSet rs = null;
        // if product dose not exist, return false;
        Boolean flag = false;

        int id = product.getProductID();
        String name = session.get_userName();
        String select_product = "SELECT * FROM hui_db.tbl_shoppingcart WHERE product_id= " + id + " AND userName = \'" + name + "\'";
        String deleteItem = "DELETE FROM hui_db.tbl_shoppingcart WHERE product_id=? and userName = ?";

        try{
            connection = getConnection();
            statement = connection.createStatement();
            try {
                rs = statement.executeQuery(select_product);
                if (rs.next()) {
                    ps = connection.prepareStatement(deleteItem);
                    ps.setInt(1, id);
                    ps.setString(2, name);
                    ps.executeUpdate();
                    System.out.println("Product removed from your shopping cart");
                    flag = true;
                } else {
                    System.out.println("Product does not exist in your shopping cart!");
                }
            }catch (SQLException e){
                System.err.println("Unable to query database!");
                e.printStackTrace();
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return flag;
    }

    /**
     * update product quantity in shoppingcart
     * @param session
     * @param product
     * @throws SQLException
     */
    public static void updateCart(Session session, Product product) throws SQLException {
        Connection connection = null;
        PreparedStatement ps = null;

        String updateItem = "UPDATE hui_db.tbl_shoppingcart SET quantity = ? WHERE product_id=? AND userName = \'" + session.get_userName() + "\'";

        try{
            connection = getConnection();
            // create the mysql update prepared statement
            ps = connection.prepareStatement(updateItem);
            ps.setInt(1, product.getQuantity());
            ps.setInt(2, product.getProductID());
            ps.executeUpdate();

            System.out.println("Product quantity is updated in cart");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        } finally {
            if(ps !=null){
                ps.close();
            }
            if (connection !=null){
                connection.close();
            }
        }

    }


    /**
     * make purchase of all the products in shopping cart
     * @param session
     * @return
     * @throws SQLException
     */
    public static Response purchaseCart(Session session) throws SQLException{
        System.out.println("enter MySQL.purchaseCart");
        Connection connection = null;
        Statement statement = null;
        ResultSet rs1 =null, rs2=null;
        PreparedStatement ps = null;
        String username = session.get_userName();
        Response response = new Response();

        String Select_Product_Query = "SELECT tbl_product.name, tbl_product.id, tbl_shoppingcart.quantity " +
                "FROM tbl_product " +
                "INNER JOIN tbl_shoppingcart " +
                "ON tbl_shoppingcart.product_id = tbl_product.id " +
                "WHERE tbl_shoppingcart.userName = \'" + username + "\'";

        try {
            connection = getConnection();
            statement = connection.createStatement();
            rs1 = statement.executeQuery(Select_Product_Query);
            //deduct each product in shopping cart from inventory
            while(rs1.next()){
                //get each product id
                int id = rs1.getInt("id");
                int request_quantity = rs1.getInt("quantity");
                String name = rs1.getString("name");

                //get the respective product quantity in inventory
                Statement statement2 = connection.createStatement();
                rs2 = statement2.executeQuery("SELECT * FROM hui_db.tbl_product WHERE id = " + id);
                rs2.next();
                int origin_quantity = rs2.getInt("quantity");

                //update the remaining product inventory
                int updated_quantity = origin_quantity - request_quantity;
                if (updated_quantity > 0){
                    statement2.executeUpdate("UPDATE hui_db.tbl_product SET quantity = " + updated_quantity + " WHERE id=" + id);
                    System.out.println("Inventory is updated!");
                    //remove everything in shopping cart under this username
                    statement2.executeUpdate("DELETE FROM hui_db.tbl_shoppingcart WHERE tbl_shoppingcart.product_id= " + id + " AND userName = \'" + session.get_userName() + "\'");
                    System.out.println("Product: " + name + " has been purchased! Inventory updated.");
                    response.setMessage("Product: " + name + " has been purchased! Inventory updated.");

                }else {
                    System.out.println("Product: " + name + " out of stock! please update product quantity.");
                    response.setMessage("Product: " + name + " out of stock! please update product quantity.");
                }

            }

        } catch (SQLException e){

            System.out.println(e.getMessage());
            throw e;
        }
        finally{
            rs1.close();
            rs2.close();
            statement.close();
            ps.close();
            if (connection != null){
                connection.close();
            }
        }
        return response;
    }



}


