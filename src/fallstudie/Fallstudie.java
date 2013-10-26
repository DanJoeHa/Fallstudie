package fallstudie;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import fallstudie.controller.impl.HauptController;
 
/**
 * Splash Screen wird erzeugt und befüllt und danach das Programm gestartet.
 *
 */
public class Fallstudie extends JFrame {
 
    private Image img;
    private int x, y;
 
    private Timer timer;
    /**
     * Position und Größe wird festgelegt und falls das Bild nicht gefunden wurde Consolen Ausgabe erzeugt
     * @param pic
     * @param x
     * @param y
     * @param millis
     */
    public Fallstudie(InputStream pic, int x, int y, long millis) {
 
        this.x = x;
        this.y = y;
        setSize(x, y);
 
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
 
        setLocation(d.width / 2 - x / 2, d.height / 2 - y / 2);
 
        this.setUndecorated(true);
 
        MediaTracker mt = new MediaTracker(this);
        try {
   
            img = ImageIO.read(pic).getScaledInstance(x, y, Image.SCALE_SMOOTH);
 
        } catch (IOException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
            System.err.println("Konnte Datei nicht öffnen!");
        }
        setVisible(true);
 
        if (img == null)
            img = this.createImage(x, y);
 
        
        mt.addImage(img, 0);
 
        try {
            mt.waitForAll();
        } catch (InterruptedException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        
        timer = new Timer();
        timer.schedule(new ExitSplashTask(this), millis);
        
    }
 /**
  * zeigt mir das Bild in der Größe 240, 180 und 2 Sekunden an
  * @param args
 * @throws URISyntaxException 
 * @throws UnsupportedEncodingException 
  */
    public static void main(String[] args) throws URISyntaxException {
    
    	InputStream url = Fallstudie.class.getResourceAsStream("images/StipplerSplash.jpg");
    	Fallstudie screen =
            new Fallstudie(url, 640, 480, 2000);
    }
 /**
  * falls das Bild nicht gefunden wird kommt ein beiges Quadrat
  */
    public void paint(Graphics g) {
        super.paint(g);
        if (img != null)
            g.drawImage(img, 0, 0, this);
    }
 
    class ExitSplashTask extends TimerTask {
 
        private JFrame frm;
 
        public ExitSplashTask(JFrame frm) {
            this.frm = frm;
        }
       /**
        * Programm wird gestartet
        */
        public void run() {
            frm.setVisible(false);
            frm.dispose();
            
        		new HauptController();
        	
        }
 
    }
}

