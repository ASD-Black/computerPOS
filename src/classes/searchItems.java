/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.util.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class searchItems {
    Connection conn;
    
    public searchItems(){
        dbConnection con = new dbConnection();
        conn = con.Connect();
    }
    
    public ResultSet showItemDetails(){
        String SQl1 = "select itm_code as 'Code', itm_name as 'Item Name', warranty as 'Warranty', qty as 'QTY', r_price as 'r_price', date as 'Date', type as 'Category', supply as 'Supplier' from items WHERE qty!=-1 ORDER BY date ASC";
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQl1);
            return rs;
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Store is Empty", "Database Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    public ResultSet showUpdatedItemDetails(){
        String SQl1 = "select itm_code as 'Code', itm_name as 'Item Name', warranty as 'Warranty', qty as 'QTY', w_price as 'w_price', r_price as 'r_price', date as 'Date', type as 'Category', supply as 'Supplier' from items WHERE itm_name!='temp prod' ORDER BY itm_code DESC";
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQl1);
            return rs;
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Store is Empty", "Database Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    public ResultSet searchByItemName(String itemName){
       String item = null;
       item = itemName;
       String sql2 = "select itm_code as 'Code', itm_name as 'Item Name', warranty as 'Warranty', qty as 'QTY', r_price as 'r_price', type as 'Category' from items where itm_name like '%"+item+"%' AND itm_name!='temp prod'";
       
       try{
           Statement stmnt = conn.createStatement();
           ResultSet rs = stmnt.executeQuery(sql2);
           return rs;
       }
       catch(Exception e){
           return null;
       }
    }
    public ResultSet searchByItemCatogary(String itemCat){
       String item = null;
       item = itemCat;
       String sql2 = "select itm_code as 'Code', itm_name as 'Item Name', warranty as 'Warranty', qty as 'QTY', r_price as 'r_price', type as 'Category' from items where type like '%"+item+"%'";
       
       try{
           Statement stmnt = conn.createStatement();
           ResultSet rs = stmnt.executeQuery(sql2);
           return rs;
       }
       catch(Exception e){
           return null;
       }
    }
    
    public ResultSet searchByItemType(String type){
       String item = null;
       item = type;
       String sql2 = "select itm_code as 'Code', itm_name as 'Item Name', warranty as 'Warranty', qty as 'QTY', r_price as 'r_price', type as 'Category' from items where type like '%"+item+"%' AND itm_name!='temp prod'";
       
       try{
           Statement stmnt = conn.createStatement();
           ResultSet rs = stmnt.executeQuery(sql2);
           return rs;
       }
       catch(Exception e){
           return null;
       }
    }
    
    public ResultSet searchByProductCode(String code){
       String item = null;
       item = code;
       String sql2 = "select itm_code as 'Code', itm_name as 'Item Name', warranty as 'Warranty', qty as 'QTY', r_price as 'r_price', type as 'Category' from items where itm_code like '%"+item+"%'";
       
       try{
           Statement stmnt = conn.createStatement();
           ResultSet rs = stmnt.executeQuery(sql2);
           return rs;
       }
       catch(Exception e){
           return null;
       }
    }
    

    public ResultSet searchByItemCode(String itmCode){
       String item = null;
       item = itmCode;
       String sql3 = "select items.itm_code as 'Code', items.itm_name as 'Item Name', items.warranty as 'Warranty', items.qty as 'QTY', items.r_price as 'r_price', items.type as 'Category' from items items INNER JOIN sub_items sub_items ON items.itm_code = sub_items.itm_code where sub_items.sn like '%"+item+"%' AND itm_name!='temp prod'";
       
       try{
           Statement stmnt = conn.createStatement();
           ResultSet rs = stmnt.executeQuery(sql3);
           return rs;
       }
       catch(Exception e){
           return null;
       }
    }
    
    public ResultSet searchByItemCodeeeee(String itmCode){
       String item = null;
       item = itmCode;
       String sql3 = "select * from items INNER JOIN sub_items sub_items ON items.itm_code = sub_items.itm_code where sub_items.sn like '%"+item+"%'";
       
       try{
           Statement stmnt = conn.createStatement();
           ResultSet rs = stmnt.executeQuery(sql3);
           return rs;
       }
       catch(Exception e){
           return null;
       }
    }
    
    public String getItemCodeBySubItemCode(String sItemCode){
       String item = null;
       String itemCode = null;
       item = sItemCode;
       
       String sql34 = "select items.itm_code from items items INNER JOIN sub_items sub_items ON items.itm_code = sub_items.itm_code where sub_items.sn='"+item+"'";
       
       try{
           Statement stmnt = conn.createStatement();
           ResultSet rsa = stmnt.executeQuery(sql34);
           while(rsa.next()){
                itemCode = rsa.getString("itm_code");  
            }
           return itemCode;
       }
       catch(Exception e){
           return null;
       }
    }
    
    public String getBCodeLableIDByItemCode(String sItemCode){
       String item = null;
       String itemCode = null;
       item = sItemCode;
       
       String sql34 = "select sn from sub_items where itm_code='"+item+"'";
       
       try{
           Statement stmnt = conn.createStatement();
           ResultSet rsa = stmnt.executeQuery(sql34);
           while(rsa.next()){
                itemCode = rsa.getString("sn");  
            }
           return itemCode;
       }
       catch(Exception e){
           return null;
       }
    }
    
    public String getBBCodeByItemCode(String sItemCode){
       String item = null;
       String itemCode = null;
       item = sItemCode;
       
       String sql34 = "select barcodeID from items where itm_code='"+item+"'";
       
       try{
           Statement stmnt = conn.createStatement();
           ResultSet rsa = stmnt.executeQuery(sql34);
           while(rsa.next()){
                itemCode = rsa.getString("barcodeID");  
            }
           return itemCode;
       }
       catch(Exception e){
           return null;
       }
    }
    
    public List<String> getBBCodesByItemCode(String sItemCode){
       String item = null;
       String itemCode = null;
       item = sItemCode;
       List<String> barcodeList = new ArrayList<>();
       
       String sql34 = "select sn from sub_items where itm_code='"+item+"' and inStock='yes'";
       
       try{
           Statement stmnt = conn.createStatement();
           ResultSet rsa = stmnt.executeQuery(sql34);
           while(rsa.next()){
                barcodeList.add(rsa.getString("sn"));  
            }
           System.out.println(barcodeList + "  llllllllll");
           return barcodeList;
       }
       catch(Exception e){
           return null;
       }
    }
    
    public String getBarCodeTypeByItemCode(String sItemCode){
       String item = null;
       String itemCode = null;
       item = sItemCode;
       
       String sql34 = "select duplicate from items where itm_code='"+item+"'";
       
       try{
           Statement stmnt = conn.createStatement();
           ResultSet rsa = stmnt.executeQuery(sql34);
           while(rsa.next()){
                itemCode = rsa.getString("duplicate");  
            }
           return itemCode;
       }
       catch(Exception e){
           return null;
       }
    }
    
    public String getQtyByItemCode(String sItemCode){
       String item = null;
       String itemCode = null;
       item = sItemCode;
       
       String sql34 = "select qty from items where itm_code='"+item+"'";
       
       try{
           Statement stmnt = conn.createStatement();
           ResultSet rsa = stmnt.executeQuery(sql34);
           while(rsa.next()){
                itemCode = rsa.getString("qty");
            }
           return itemCode;
       }
       catch(Exception e){
           return null;
       }
    }
    
    public String getItemCodeBySubItemName(String sItemCode){
       String item = null;
       String itemCode = null;
       item = sItemCode;
       
       String sql34 = "select items.itm_code from items items INNER JOIN sub_items sub_items ON items.itm_code = sub_items.itm_code where sub_items.sn='"+item+"'";
       
       try{
           Statement stmnt = conn.createStatement();
           ResultSet rsa = stmnt.executeQuery(sql34);
           while(rsa.next()){
                itemCode = rsa.getString("itm_code");  
            }
           return itemCode;
       }
       catch(Exception e){
           return null;
       }
    }
    
    public boolean deleteItemCode(String itmCode){
        String sql = "delete from items where itm_code='"+itmCode+"'";
        
        try{
            Statement stmnt = conn.createStatement();
            stmnt.execute(sql);
            return true;
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Cannot Delete Item", "Database Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    public boolean deleteSN(String snID){
        String sql = "delete from sub_items where sID='"+snID+"'";
        
        try{
            Statement stmnt = conn.createStatement();
            stmnt.execute(sql);
            return true;
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Cannot Delete This SN", "Database Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    public ResultSet getItemDetails(String itemCode){
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
    
    public String getItemNameByItemCode(String name){
       String item = null;
       String itemName = null;
       item = name;
       
       String sql34 = "select itm_name from items where itm_code='"+item+"'";
       
       try{
           Statement stmnt = conn.createStatement();
           ResultSet rsa = stmnt.executeQuery(sql34);
           while(rsa.next()){
                itemName = rsa.getString("itm_name");  
            }
           return itemName;
       }
       catch(Exception e){
           return null;
       }
    }
    
}
