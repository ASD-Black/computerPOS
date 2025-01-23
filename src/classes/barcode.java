
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
public class barcode {
    Connection conn;
    
    public barcode(){
        dbConnection con = new dbConnection();
        conn = con.Connect();
    }
    
    public String addBarcodeItems1(String pName, Component comp, String type, Component comp1, Component comp3, String price, String status){
        try{
            String SQL= "insert into a(itmCode1, model,mb, rPrice,status,bb_id) values(?,?,?,?,?,?)";
            String itmcc = generateItemmBarCode(comp, type);
            String mb2 = getMainBarCode(comp1);
            String bb = getBarCodeBundalID(comp3);
            
            PreparedStatement pst6 = conn.prepareStatement(SQL);
            
            pst6.setString(1, itmcc);  
            pst6.setString(2, pName);
            pst6.setString(3, mb2);
            pst6.setString(4, price);
            pst6.setString(5, status);
            pst6.setString(6, bb);
            pst6.execute();
            
            increaseNoOfBarcodeItmsByOne();
            return itmcc;
        }
        catch(Exception e){
            //JOptionPane.showMessageDialog(comp, "Customer Added Failed","Owner Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    public String addBarcodeItems2(String pName, Component comp, String type, Component comp2, Component comp3, String price, String status){
        try{
            String SQLL= "insert into a(itmCode1, model, mb, rPrice, status,bb_id) values(?,?,?,?,?,?)";
            String itmcc = generateItemmBarCode(comp, type);
            String mb1 = getMainBarCode(comp2);
            String bb = getBarCodeBundalID(comp3);
            PreparedStatement pst7 = conn.prepareStatement(SQLL);

            pst7.setString(1, itmcc);  
            pst7.setString(2, pName);
            pst7.setString(3, mb1);
            pst7.setString(4, price);
            pst7.setString(5, status);
            pst7.setString(6, bb);
            pst7.execute();
            
            //increaseNoOfBarcodeItmsByOne();
            return itmcc;
        }
        catch(Exception e){
            //JOptionPane.showMessageDialog(comp, "Customer Added Failed","Owner Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    public String addBarcodeItems3(String itmcc, String pName, Component comp, String type, Component comp2, Component comp3, String price, String status){
        try{
            String SQLL= "insert into a(itmCode1, model, mb, rPrice, status,bb_id) values(?,?,?,?,?,?)";
            
            String mb1 = getMainBarCode(comp2);
            String bb = getBarCodeBundalID(comp3);
            PreparedStatement pst7 = conn.prepareStatement(SQLL);

            pst7.setString(1, itmcc);  
            pst7.setString(2, pName);
            pst7.setString(3, mb1);
            pst7.setString(4, price);
            pst7.setString(5, status);
            pst7.setString(6, bb);
            pst7.execute();
            
            //increaseNoOfBarcodeItmsByOne();
            return itmcc;
        }
        catch(Exception e){
            //JOptionPane.showMessageDialog(comp, "Customer Added Failed","Owner Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    public String addBundelBarcode(String bb_model, String bb_type, int bb_qty, String bb_price, String duplicate, Component comp){
        try{
            String SQL= "insert into barcode_bundel(bb_model, bb_type, mb, bb_price, bb_qty, duplicate, bb_id) values(?,?,?,?,?,?,?)";
            //
            String bb_id = generateBarCodeBundal(comp);
            String mainBB = getMainBarCode(comp);

            PreparedStatement pst9 = conn.prepareStatement(SQL);
            
            pst9.setString(1, bb_model);  
            pst9.setString(2, bb_type);

            pst9.setString(3, mainBB);
            pst9.setString(4, bb_price);
            pst9.setInt(5, bb_qty);
            pst9.setString(6, duplicate);
            pst9.setString(7, bb_id);
            pst9.execute();
            
            increaseNoOfBarcodeBunbelsByOne();
            return bb_id;
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(comp, "Barcode Added Failed","Barcode Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    public String getMainBarCode(Component comp){
        String type = "B";
        int noMovies = 0;
        String DiskID = null;
        String movieID = null;
        
        try{
            Statement stmnt = conn.createStatement();
            ResultSet Rs = stmnt.executeQuery("select main_barcode from count");
            while(Rs.next()){
                noMovies = Rs.getInt("main_barcode");
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(comp, "Cannot retrive data from count table","Database Error",JOptionPane.ERROR_MESSAGE);
        }
        
        if(noMovies<=9){
            movieID = type.concat("00000").concat(Integer.toString(noMovies));
            return movieID;
        }
        else if(noMovies<=99){
            movieID = type.concat("0000").concat(Integer.toString(noMovies));
            return movieID;
        }
        else if(noMovies<=999){
            movieID = type.concat("000").concat(Integer.toString(noMovies));
            return movieID;
        }
        else if(noMovies<=9999){
            movieID = type.concat("00").concat(Integer.toString(noMovies));
            return movieID;
        }
        else if(noMovies<=99999){
            movieID = type.concat("0").concat(Integer.toString(noMovies));
            return movieID;
        }
        else if(noMovies<=999999){
            movieID = type.concat(Integer.toString(noMovies));
            return movieID;
        }
        else{
            JOptionPane.showMessageDialog(comp, "Cannot GenerateID. Maximum No Of Items Reached", "Database Error", JOptionPane.ERROR_MESSAGE);
            return movieID;
        }
        
    }
    
    public String getBarCodeBundalID(Component comp){
        String type = "BB";
        int noMovies = 0;
        String DiskID = null;
        String movieID = null;
        
        try{
            Statement stmnt = conn.createStatement();
            ResultSet Rs = stmnt.executeQuery("select bb_id from count");
            while(Rs.next()){
                noMovies = Rs.getInt("bb_id");
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(comp, "Cannot retrive data from count table","Database Error",JOptionPane.ERROR_MESSAGE);
        }
        
        if(noMovies<=9){
            movieID = type.concat("00000").concat(Integer.toString(noMovies));
            return movieID;
        }
        else if(noMovies<=99){
            movieID = type.concat("0000").concat(Integer.toString(noMovies));
            return movieID;
        }
        else if(noMovies<=999){
            movieID = type.concat("000").concat(Integer.toString(noMovies));
            return movieID;
        }
        else if(noMovies<=9999){
            movieID = type.concat("00").concat(Integer.toString(noMovies));
            return movieID;
        }
        else if(noMovies<=99999){
            movieID = type.concat("0").concat(Integer.toString(noMovies));
            return movieID;
        }
        else if(noMovies<=999999){
            movieID = type.concat(Integer.toString(noMovies));
            return movieID;
        }
        else{
            JOptionPane.showMessageDialog(comp, "Cannot GenerateID. Maximum No Of Items Reached", "Database Error", JOptionPane.ERROR_MESSAGE);
            return movieID;
        }
        
    }
    
    public String getPreviousMainBarCode(Component comp){
        String type = "B";
        int noMovies = 0;
        String DiskID = null;
        String movieID = null;
        int r = 0;
        
        try{
            Statement stmnt = conn.createStatement();
            ResultSet Rs = stmnt.executeQuery("select main_barcode from count");
            while(Rs.next()){
                r = Rs.getInt("main_barcode");
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(comp, "Cannot retrive data from count table","Database Error",JOptionPane.ERROR_MESSAGE);
        }
        noMovies = r-1;
        if(noMovies<=9){
            movieID = type.concat("00000").concat(Integer.toString(noMovies));
            return movieID;
        }
        else if(noMovies<=99){
            movieID = type.concat("0000").concat(Integer.toString(noMovies));
            return movieID;
        }
        else if(noMovies<=999){
            movieID = type.concat("000").concat(Integer.toString(noMovies));
            return movieID;
        }
        else if(noMovies<=9999){
            movieID = type.concat("00").concat(Integer.toString(noMovies));
            return movieID;
        }
        else if(noMovies<=99999){
            movieID = type.concat("0").concat(Integer.toString(noMovies));
            return movieID;
        }
        else if(noMovies<=999999){
            movieID = type.concat(Integer.toString(noMovies));
            return movieID;
        }
        else{
            JOptionPane.showMessageDialog(comp, "Cannot GenerateID. Maximum No Of Items Reached", "Database Error", JOptionPane.ERROR_MESSAGE);
            return movieID;
        }
        
    }
    
    public String addMainBarcode(Component comp){
        try{
            String SQL= "insert into mainbarcode(code) values(?)";
            String mb = generateMainBarCode(comp);
            PreparedStatement pst8 = conn.prepareStatement(SQL);
            
            pst8.setString(1, mb);  
         
            pst8.execute();
            
            increaseNoOfMainBarcodeItmsByOne();
            return mb;
        }
        catch(Exception e){
            //JOptionPane.showMessageDialog(comp, "Customer Added Failed","Owner Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
  
    public String generateItemmBarCode(Component comp, String type){
        //type = "";
        int noMovies = 0;
        String movieID = null;
        
        try{
            Statement stmnt = conn.createStatement();
            ResultSet rs = stmnt.executeQuery("select barcode from count");
            while(rs.next()){
                noMovies = rs.getInt("barcode");
                
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(comp, "Cannot retrive data from count table","Database Error",JOptionPane.ERROR_MESSAGE);
        }
        if(noMovies<9){
            movieID = type.concat("00000").concat(Integer.toString(noMovies+1));
            return movieID;
        }
        else if(noMovies<99){
            movieID = type.concat("0000").concat(Integer.toString(noMovies+1));
            return movieID;
        }
        else if(noMovies<999){
            movieID = type.concat("000").concat(Integer.toString(noMovies+1));
            return movieID;
        }
        else if(noMovies<9999){
            movieID = type.concat("00").concat(Integer.toString(noMovies+1));
            return movieID;
        }
        else if(noMovies<99999){
            movieID = type.concat("0").concat(Integer.toString(noMovies+1));
            return movieID;
        }
        else if(noMovies<999999){
            movieID = type.concat(Integer.toString(noMovies+1));
            return movieID;
        }
        else{
            JOptionPane.showMessageDialog(comp, "Cannot GenerateID. Maximum No Of Items Reached", "Database Error", JOptionPane.ERROR_MESSAGE);
            return movieID;
        }
    }
    
    public String generateMainBarCode(Component comp){
        String type = "B";
        int noMovies = 0;
        String movieID = null;
        
        try{
            Statement stmnt = conn.createStatement();
            ResultSet rs = stmnt.executeQuery("select main_barcode from count");
            while(rs.next()){
                noMovies = rs.getInt("main_barcode");
                
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(comp, "Cannot retrive data from count table","Database Error",JOptionPane.ERROR_MESSAGE);
        }
        if(noMovies<9){
            movieID = type.concat("00000").concat(Integer.toString(noMovies+1));
            return movieID;
        }
        else if(noMovies<99){
            movieID = type.concat("0000").concat(Integer.toString(noMovies+1));
            return movieID;
        }
        else if(noMovies<999){
            movieID = type.concat("000").concat(Integer.toString(noMovies+1));
            return movieID;
        }
        else if(noMovies<9999){
            movieID = type.concat("00").concat(Integer.toString(noMovies+1));
            return movieID;
        }
        else if(noMovies<99999){
            movieID = type.concat("0").concat(Integer.toString(noMovies+1));
            return movieID;
        }
        else if(noMovies<999999){
            movieID = type.concat(Integer.toString(noMovies+1));
            return movieID;
        }
        else{
            JOptionPane.showMessageDialog(comp, "Cannot GenerateID. Maximum No Of Items Reached", "Database Error", JOptionPane.ERROR_MESSAGE);
            return movieID;
        }
    }
    
    public String generateBarCodeBundal(Component comp){
        String type = "BB";
        int noMovies = 0;
        String movieID = null;
        
        try{
            Statement stmnt = conn.createStatement();
            ResultSet rs = stmnt.executeQuery("select bb_id from count");
            while(rs.next()){
                noMovies = rs.getInt("bb_id");
                
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(comp, "Cannot retrive data from count table","Database Error",JOptionPane.ERROR_MESSAGE);
        }
        if(noMovies<9){
            movieID = type.concat("00000").concat(Integer.toString(noMovies+1));
            return movieID;
        }
        else if(noMovies<99){
            movieID = type.concat("0000").concat(Integer.toString(noMovies+1));
            return movieID;
        }
        else if(noMovies<999){
            movieID = type.concat("000").concat(Integer.toString(noMovies+1));
            return movieID;
        }
        else if(noMovies<9999){
            movieID = type.concat("00").concat(Integer.toString(noMovies+1));
            return movieID;
        }
        else if(noMovies<99999){
            movieID = type.concat("0").concat(Integer.toString(noMovies+1));
            return movieID;
        }
        else if(noMovies<999999){
            movieID = type.concat(Integer.toString(noMovies+1));
            return movieID;
        }
        else{
            JOptionPane.showMessageDialog(comp, "Cannot GenerateID. Maximum No Of Items Reached", "Database Error", JOptionPane.ERROR_MESSAGE);
            return movieID;
        }
    }
    
    public void increaseNoOfBarcodeItmsByOne(){
        try{
            int noMovies=0;
            Statement stmnt = conn.createStatement();
            ResultSet rs = stmnt.executeQuery("select barcode from count");
            while(rs.next()){
                noMovies = rs.getInt("barcode");
            }
            try{
                String SQL = "update count set barcode="+(noMovies+1)+" where barcode="+noMovies;
                Statement stmnt2 = conn.createStatement();
                stmnt2.executeUpdate(SQL);
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void increaseNoOfBarcodeBunbelsByOne(){
        try{
            int noMovies=0;
            Statement stmnt = conn.createStatement();
            ResultSet rs = stmnt.executeQuery("select bb_id from count");
            while(rs.next()){
                noMovies = rs.getInt("bb_id");
            }
            try{
                String SQL = "update count set bb_id="+(noMovies+1)+" where bb_id="+noMovies;
                Statement stmnt2 = conn.createStatement();
                stmnt2.executeUpdate(SQL);
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void increaseNoOfMainBarcodeItmsByOne(){
        try{
            int noMovies=0;
            Statement stmnt = conn.createStatement();
            ResultSet rs = stmnt.executeQuery("select main_barcode from count");
            while(rs.next()){
                noMovies = rs.getInt("main_barcode");
            }
            try{
                String SQL = "update count set main_barcode="+(noMovies+1)+" where main_barcode="+noMovies;
                Statement stmnt2 = conn.createStatement();
                stmnt2.executeUpdate(SQL);
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public ResultSet ShowBundelBarcodes(){
       String sql3 = "select bb_id as 'Barcode ID', bb_model as 'Item Name', bb_type as 'String With', bb_price as 'Retail Price', bb_qty as 'Barcode QTY', duplicate as 'Duplicate' from barcode_bundel ORDER BY bb_id DESC";
       
       try{
           Statement stmnt = conn.createStatement();
           ResultSet rs = stmnt.executeQuery(sql3);
           return rs;
       }
       catch(Exception e){
           return null;
       }
    }
      
    public boolean checkBarcodeDataAvailability(Component comp){
        boolean available = false;
        ResultSet result = null;
        int st=0;
        
        try{
            String nextBarcode_id = getMainBarCode(comp);
            System.out.println(nextBarcode_id);
            Statement stmnt3 = conn.createStatement();
            result = stmnt3.executeQuery("select bb_qty from barcode_bundel where mb='"+nextBarcode_id+"'");
            while(result.next()){
                st = result.getInt("bb_qty");
                
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        if(st > 0){
            available = true;
            
        }
        else{
            available = false;
        }   
        return available;
        
    }
}
