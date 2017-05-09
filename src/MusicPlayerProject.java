/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import java.io.File;
import java.io.IOException;
import java.sql.Statement;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
String filepath="";
        try {
            Database db = new Database();
            Connection connection=db.conn;
            Statement statement=connection.createStatement();
            String sql="SELECT * FROM SONGS";
            ResultSet rs=statement.executeQuery(sql);
            rs.next();
            String songName=rs.getString("NAME");
            System.out.println(songName);
            filepath=rs.getString("FILEPATH");
            System.out.println(filepath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    BasicPlayer musicPlayer = new BasicPlayer();
    File file=new File(filepath);
        try {
            musicPlayer.open(file);
            musicPlayer.play();
        } catch (BasicPlayerException ex) {
            Logger.getLogger(MusicPlayerProject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
}
