package MiniPlayer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lin
 */
//java stuff
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
//BasicPlayer stuff
import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;

/**
 * @author The mean old bastard
 */

class MiniPlayerGUI extends JFrame {
    JPanel  panel = new JPanel();
    JTextField songPath = new JTextField();
    JButton play = new JButton(">");
    JButton stop = new JButton("[]");
    JLabel label = new JLabel("Enter song path below");
    
    //share the one listener with both buttons
    PlayStopListener psl = new PlayStopListener();
    
    BasicPlayer player = new BasicPlayer();
    
    public MiniPlayerGUI(){
        this.setTitle("Mini Player 1.0 by Dr. H");
        this.setSize(300, 100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setLayout(new GridLayout(4,1));
        //add the gui components to the panel
        panel.add(label);
        panel.add(songPath);
        
        //add the actionListener
        play.addActionListener(psl);
        stop.addActionListener(psl);
        
        panel.add(play);
        panel.add(stop);
        //put the panel in the frame
        this.add(panel);     
    }
    
    public void go(){
        //this starts the gui
        setVisible(true);
    }
    
    class PlayStopListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String song = songPath.getText();
            if(song == null)
                return;
            if(e.getActionCommand().equals(">")){
                
                try {
                    //if already playing a song, stop it
                    if(player.getStatus()==BasicPlayer.PLAYING)
                        player.stop();
                    //open the song in the player
                    player.open(new URL("file:///" + song));  
                    //play the song in the player
                    player.play();
                    //set the loudness / volume /gain
                    player.setGain(1);//volume is from 0.00 to 1.0
                    
                    //trace message to output
                    System.out.println("Playing:" + song);
                } catch (BasicPlayerException | MalformedURLException ex) {
                    Logger.getLogger(MiniPlayerGUI.class.getName()).log(Level.SEVERE, null, ex);
                }                 
            }            
            else {
                //only two buttons so if it isn't play it must be stop
                try {
                    player.stop();
                    System.out.println("Stopping:" + songPath.getText() );
                    } catch (BasicPlayerException ex) {
                    Logger.getLogger(MiniPlayerGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        }      
    }
}