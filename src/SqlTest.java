
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
     public static void main(String[] args) {
         try {
             Database db=new Database();
             Connection conn=db.conn;
             Statement statement=conn.createStatement();
             String sql="SELECT * FROM TEST"; 
             ResultSet rs=statement.executeQuery(sql);
             while(rs.next()){
                 System.out.println(rs.getString("NUMBER"));
             }
             
         } catch (SQLException ex) {
             Logger.getLogger(SqlTest.class.getName()).log(Level.SEVERE, null, ex);
         } catch (Exception ex) {
             Logger.getLogger(SqlTest.class.getName()).log(Level.SEVERE, null, ex);
         }
    
     }
}
