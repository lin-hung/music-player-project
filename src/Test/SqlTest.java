package Test;


import MusicPlayerProject.Database;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lin
 */
public class SqlTest {
    Database db;
    Connection conn;
    
    public SqlTest(){
        System.out.println("#######sqltest init");
        try {
            db=new Database();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SqlTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SqlTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(SqlTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        conn=db.conn;
    }
    String getName(){   
        try {
            Statement statement=conn.createStatement();
            String sql="SELECT * FROM TEST"; 
            ResultSet rs=statement.executeQuery(sql);
            rs.next();
            return rs.getString("NAME");
        } catch (SQLException ex) {
            Logger.getLogger(SqlTest.class.getName()).log(Level.SEVERE, null, ex);
        } 
           return "error";
    }
    String[] getNames(){
        ArrayList<String> names=new ArrayList();
         try {
            Statement statement=conn.createStatement();
            String sql="SELECT * FROM TEST"; 
            ResultSet rs=statement.executeQuery(sql);
            while(rs.next()){
                names.add(rs.getString("NAME"));
            }
            return (names.toArray(new String[names.size()]));
        } catch (SQLException ex) {
            Logger.getLogger(SqlTest.class.getName()).log(Level.SEVERE, null, ex);
        } 
         String[] ret={"no"};
         return ret;
    }
    
     public static void main(String[] args) {         
         try {
             Database db=new Database();
             Connection conn=db.conn;
             Statement statement=conn.createStatement();
             String sql="SELECT * FROM TEST"; 
             ResultSet rs=statement.executeQuery(sql);
             while(rs.next()){
                 System.out.println(rs.getString("NAME"));
             }
             if(!rs.next()){
                 System.out.println("######");
             }
             
         } catch (SQLException ex) {
             Logger.getLogger(SqlTest.class.getName()).log(Level.SEVERE, null, ex);
         } catch (Exception ex) {
             Logger.getLogger(SqlTest.class.getName()).log(Level.SEVERE, null, ex);
         }
                     System.out.println("######");

     }
     
}
