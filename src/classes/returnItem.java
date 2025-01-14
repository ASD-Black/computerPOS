
package classes;

import java.awt.Component;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;
import javax.swing.*;

public class returnItem {
    
    Connection conn;
    
    public returnItem(){
        dbConnection con = new dbConnection();
        conn = con.Connect();
    }
    
    public ResultSet getInDetails(String itemCode){
       String SQL = "select * from items where itm_code='"+itemCode+"'";
       
       try{
           Statement stmnt = conn.createStatement();
           ResultSet rs = stmnt.executeQuery(SQL);
           return rs;
       }
       catch(Exception e){
           return null;
       }
   }
    
    public boolean deleteReturnItems(String retID){
        String sql = "delete from returneditmlist where returnCode='"+retID+"'";
        
        try{
            Statement stmnt = conn.createStatement();
            stmnt.execute(sql);
            return true;
        }
        catch(Exception e){  
            return false;
        }
    }
    
    public String getBillItemName(String bullItemID){
       String des = "";
       String sql3456 = "select item_name from bill_items where bill_item_id='"+bullItemID+"'";
       
       try{
           Statement stmnt = conn.createStatement();
           ResultSet rsagg = stmnt.executeQuery(sql3456);
           while(rsagg.next()){
                des = rsagg.getString("item_name");  
            }
           return des;
       }
       catch(Exception e){
           return null;
       }
    }
    
    public String getBillItemIDBySN(String sn){
       String des = "";
       String sql3456 = "select bill_item_id from bill_sub_items where snn='"+sn+"'";
       
       try{
           Statement stmnt = conn.createStatement();
           ResultSet rsagg = stmnt.executeQuery(sql3456);
           while(rsagg.next()){
                des = rsagg.getString("bill_item_id");  
            }
           return des;
       }
       catch(Exception e){
           return null;
       }
    }
}
