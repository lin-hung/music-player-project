/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MusicPlayerProject;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;

/**
 *
 * @author lin
 */
public class MusicPlayer {

    Database db;
    Connection conn;
    Statement statement;
    BasicPlayer basicPlayer;

    public MusicPlayer() {
        System.out.println("MusicPlayer constructor");
        try {
            db = new Database();
            conn = db.conn;
            statement = conn.createStatement();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MusicPlayer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MusicPlayer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(MusicPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        basicPlayer = new BasicPlayer();
    }

    public void playNew(String selected) {
        System.out.println("Playing" + selected);
        String sql = "SELECT * FROM TestSongs where NAME='"
                + selected + "'";
        System.out.println(sql);
        try {
            ResultSet rs = statement.executeQuery(sql);
            rs.next();
            System.out.println(rs.getString("FILEPATH"));
            File file = new File(rs.getString("FILEPATH"));
            basicPlayer.open(file);
            basicPlayer.play();
        } catch (SQLException ex) {
            Logger.getLogger(MusicPlayer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BasicPlayerException ex) {
            ex.printStackTrace();
        }
    }

    public void resume() {
        try {
            basicPlayer.resume();
        } catch (BasicPlayerException ex) {
            Logger.getLogger(MusicPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String[] getSongNames() {
        ArrayList<String> names = new ArrayList();
        String filepath;
        Mp3File mp3file;

        try {/*sql stuff*/
            Statement statement = conn.createStatement();
            String sql = "SELECT * FROM TESTSONGS";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {//getting filepath
                filepath = rs.getString("FILEPATH");
                System.out.println(filepath);

                try {//creates new Mp3File from filepath, finds title from tags if avaliable, adds to array
                    mp3file = new Mp3File(filepath);

                    String title = filepath;
                    if (mp3file.hasId3v1Tag()) {
                        title = mp3file.getId3v1Tag().getTitle();
                    } else if (mp3file.hasId3v2Tag()) {
                        title = mp3file.getId3v2Tag().getTitle();
                    }
                    names.add(title);
                } catch (Exception ex) {
                    Logger.getLogger(MusicPlayer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            for (String str : names) {
                System.out.println(str);
            }
            return (names.toArray(new String[names.size()]));

        } catch (SQLException sQLException) {
            String[] ret = {"error"};
            return ret;
        }
    }

    public void addSong(String path) {
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO TestSongs(name, filepath) VALUES (?,?)");

            Mp3File mp3file = new Mp3File(path);
            String title = mp3file.getFilename();
            if (mp3file.hasId3v1Tag()) {
                title = mp3file.getId3v1Tag().getTitle();
            } else if (mp3file.hasId3v2Tag()) {
                title = mp3file.getId3v2Tag().getTitle();
            }
            stmt.setString(1, title);
            stmt.setString(2, path);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MusicPlayer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void removeSong(String selected) {
        System.out.println("Removing " + selected);
        String sql = "DELETE FROM TestSongs where NAME='"
                + selected + "'";
        System.out.println(sql);
        try {
            statement.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(MusicPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void pause() {
        try {
            basicPlayer.pause();
        } catch (BasicPlayerException ex) {
            Logger.getLogger(MusicPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void stop() {
        System.out.println("player stopped");
        try {
            basicPlayer.pause();
        } catch (BasicPlayerException ex) {
            Logger.getLogger(MusicPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        basicPlayer=new BasicPlayer();
    }
}
