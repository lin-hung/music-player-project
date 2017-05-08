/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package musicplayerproject;

import java.io.IOException;
import java.sql.Statement;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author lin
 */
public class MusicPlayerProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {       
//
try{
        Database db = new Database();
    }catch(ClassNotFoundException e){
        e.printStackTrace();
    }catch(Exception e){
        e.printStackTrace();
    }
    
    }  
}
