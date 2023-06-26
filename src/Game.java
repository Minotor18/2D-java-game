
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;



public class Game extends JFrame{

 public static InGame  oyun ;
    public Game() throws HeadlessException {
              oyun = new InGame();
                setResizable(false);
                setFocusable(false);
                 setPreferredSize(new Dimension(500, 500));
                 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                Graphics g = oyun.getGraphics();
                oyun.requestFocus();
                oyun.addKeyListener(oyun);
                 oyun.setFocusable(true);
                oyun.setFocusTraversalKeysEnabled(false);
                add(oyun);
                pack();
                setVisible(true);
    }
    
    
    
    
    
public class Friend extends Thread {
        Random random = new Random();
        private int x = random.nextInt(51) * 10;
        private int y =random.nextInt(51) * 10;;

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        @Override
        public void run() {
             Graphics g = oyun.getGraphics();
            g.setColor(Color.GREEN);
            g.fillRect(x, y, 10, 10);
            
        }
        
       

    }

    
    public class Enemy extends Thread {
        Random random = new Random();

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
        private int x = random.nextInt(51) * 10;;
        private int y = random.nextInt(51) * 10;;

        @Override
        public void run() {
            Graphics g = oyun.getGraphics();
            g.setColor(Color.BLACK);
            g.fillRect(x, y, 10, 10);
        }
        
       
      
    }
    
    public class AirCraft extends Thread {

        public int getKarakterX() {
            return KarakterX;
        }

        public void setKarakterX(int KarakterX) {
            this.KarakterX = KarakterX;
        }

        public int getKarakterY() {
            return KarakterY;
        }

        public void setKarakterY(int KarakterY) {
            this.KarakterY = KarakterY;
        }
        private int KarakterX = 250;
        private int KarakterY = 250;

        @Override
        public void run() {
             Graphics g = oyun.getGraphics();
             oyun.paint(g);
            g.setColor(Color.RED);
            g.fillRect(KarakterX, KarakterY, 10, 10);
            
        }
        
        
    
    }


    
  
    
    
    public class InGame extends JPanel implements KeyListener,ActionListener{

        @Override
        public void paint(Graphics g) {
            super.paint(g); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        }
    
     
   
    
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
    
}
   
}
