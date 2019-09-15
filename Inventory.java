// CSCI507 Assignment 4, Mar 31
// Honor Pledge:
//
// I pledge that I have neither given nor
// received any help on this assignment.
//
// hui

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.io.Serializable;
import java.util.List;

/**
 * This class deals with reading and writing actions with Inventory.txt
 */
public class Inventory implements Serializable{

    private static final long serialVersionUID = 1L;

    BufferedReader bufferedReader;
    BufferedWriter bufferWriter;
    FileReader fileReader;
    FileWriter fileWriter;

    private static final String Filename = "Inventory.txt";
    File file = new File(Filename);

    //Read from inventory, and add it to the product list
    public List<Product> readFromInventory() throws IOException, SQLException{
        return MySQL.selectProduct();
    }

    //add product to inventory, write it to file
    public void addProductToInventory(Product product, Boolean append) throws IOException{
        try{
            // if file doesn't exists, then create the file
            if(!file.exists()){
                file.createNewFile();
            }
            fileWriter = new FileWriter(file,append);
            bufferWriter = new BufferedWriter(fileWriter);
            bufferWriter.write(product.getProductID()+"\t"+product.getProductName()+
                    "\t"+ product.getPrice()+"\t"+ product.getQuantity()+"\n");
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            bufferWriter.close();
        }
    }
}
