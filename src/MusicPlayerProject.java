/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.IOException;
import java.sql.Statement;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jlgui.basicplayer.BasicController;
import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;

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
        try {
            Database db = new Database();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    BasicPlayer musicPlayer = new BasicPlayer();
    String filepath = "C:\\Users\\lin\\Desktop\\musicFile.mp3";
    File file=new File(filepath);
        try {
            musicPlayer.open(file);
            musicPlayer.play();
        } catch (BasicPlayerException ex) {
            Logger.getLogger(MusicPlayerProject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
}
