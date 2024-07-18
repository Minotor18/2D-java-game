

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import static java.lang.Math.random;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Game extends JFrame{
 ArrayList<Integer> EnemeyXs = new ArrayList<>();
    ArrayList<Integer> EnemeyYs = new ArrayList<>();
 ArrayList<Integer> FriendXs = new ArrayList<>();
    ArrayList<Integer> FriendYs = new ArrayList<>();
    ArrayList<InGame.Ates> ateslerSaga = new ArrayList<>();
        ArrayList<InGame.Ates> ateslerSola = new ArrayList<>();
  ArrayList<InGame.Ates> DusmanatesSag = new ArrayList<>();
  ArrayList<InGame.Ates> DusmanatesSol = new ArrayList<>();
  ArrayList<InGame.Ates> DostatesSag = new ArrayList<>();
  ArrayList<InGame.Ates> DostatesSol = new ArrayList<>();
    static final int SCREEN_WITDH=500;
    static final int SCREEN_HEIGHT=500;
     static final int UNIT_SIZE=10;
     static final int GAME_UNITS= (SCREEN_HEIGHT*SCREEN_WITDH)/UNIT_SIZE;
       Random random=new Random();
     int nAircraftX=250;
     int nAircraftY=250;
     char direction=' ';
    public Game() throws HeadlessException {
        InGame game = new InGame();
        this.add(game);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
         this.pack();
         this.setLocationRelativeTo(null);
    }
     public class AirCraft extends Thread{
      
          
          int AircraftX;
          int AircraftY;

        public int getAircraftX() {
            return AircraftX;
        }

        public void setAircraftX(int AircraftX) {
            this.AircraftX = AircraftX;
        }

        public int getAircraftY() {
            return AircraftY;
        }

        public void setAircraftY(int AircraftY) {
            this.AircraftY = AircraftY;
        }


    @Override
    public void run() {
         this.AircraftX=250;
             this.AircraftY=250;
             nAircraftX=AircraftX;
              nAircraftY=AircraftY;
    }
    }
  public  class Enemy extends Thread{
  public int EnemyX;
    public  int EnemyY;

    @Override
    public void run() {
          this.EnemyX = random.nextInt((int)SCREEN_WITDH/UNIT_SIZE)*UNIT_SIZE;
            this.EnemyY = random.nextInt((int)SCREEN_HEIGHT/UNIT_SIZE)*UNIT_SIZE;
            synchronized(EnemeyXs) {
            EnemeyXs.add(EnemyX);
        }
        synchronized(EnemeyYs) {
            EnemeyYs.add(EnemyY);
        }
    }


}
  
    public class Friend extends Thread{
    public  int FriendX;
    public  int FriendY;
    @Override
    public void run() {
        this.FriendX = random.nextInt((int)SCREEN_WITDH/UNIT_SIZE)*UNIT_SIZE;
            this.FriendY = random.nextInt((int)SCREEN_WITDH/UNIT_SIZE)*UNIT_SIZE;
             synchronized(FriendXs) {
            FriendXs.add(FriendX);
        }
            synchronized(FriendYs) {
            FriendYs.add(FriendY);
        }
    }
}


public class InGame extends JPanel implements KeyListener,MouseListener{
   
    
    
    
            Timer KarakterYon = new Timer(500, (e) -> {
        EnemyFriendMove();
        
    });
         
    
 Timer AirCraftAtes = new Timer(100, (e) -> {
              for (Ates ates:ateslerSaga) {
            ates.setX(ates.getX()+UNIT_SIZE);
              }
               for (Ates ates:ateslerSola) {
            ates.setX(ates.getX()-UNIT_SIZE);
            
        }
       
            
        
 });
         Timer DostAtesOlustur = new Timer(1000, (e) -> {
             for (int i = 0; i < FriendXs.size(); i++) {
                 DostatesSag.add(new Ates(FriendXs.get(i),FriendYs.get(i)));
                  DostatesSol.add(new Ates(FriendXs.get(i),FriendYs.get(i)));
                 
             }
});
         Timer DostAtesleyici = new Timer(100, (e) -> {
              for (Ates ates:DostatesSag) {
            ates.setX(ates.getX()+UNIT_SIZE);
              }
               for (Ates ates:DostatesSol) {
            ates.setX(ates.getX()-UNIT_SIZE);
            
        }
         });
              Timer DusmanAtesOlustur = new Timer(1000, (e) -> {
             for (int i = 0; i < EnemeyXs.size(); i++) {
                 DusmanatesSag.add(new Ates(EnemeyXs.get(i),EnemeyYs.get(i)));
                  DusmanatesSol.add(new Ates(EnemeyXs.get(i),EnemeyYs.get(i)));
                 
             }
});
         Timer DusmanAtesleyici = new Timer(100, (e) -> {
              for (Ates ates:DusmanatesSag) {
            ates.setX(ates.getX()+UNIT_SIZE);
              }
               for (Ates ates:DusmanatesSol) {
            ates.setX(ates.getX()-UNIT_SIZE);
            
        }
         });
   
             
             
             
        
      public InGame() {
        this.setPreferredSize(new Dimension(SCREEN_WITDH, SCREEN_HEIGHT));
        this.setFocusable(true);
        this.addKeyListener(this);
        this.addMouseListener(this);
     
        
      
             
         
        KarakterYon.start();
        AirCraftAtes.start();
        DostAtesOlustur.start();
        DostAtesleyici.start();
        DusmanAtesOlustur.start();
        DusmanAtesleyici.start();
    }
    
    
    public class Ates{
        int x,y;

         public Ates(int x, int y) {
             this.x = x;
             this.y = y;
         }

         public int getX() {
             return x;
         }

         public void setX(int x) {
             this.x = x;
         }

         public int getY() {
             return y;
         }

         public void setY(int y) {
             this.y = y;
         }
    
    
    }
    
           
   public void paintComponent(Graphics g) {
    super.paintComponent(g);

    if (EnemeyXs.size() == 0) {
        KarakterYon.stop();
        AirCraftAtes.stop();
        DostAtesOlustur.stop();
     
        DusmanAtesOlustur.stop();
        dispose();
        String m = "Oyunu Kazandiniz";
       JOptionPane.showMessageDialog(this, m);
        System.exit(0);

        
    }

    drawFriendEnemy(g);
    drawAircraft(g);
    AirCraftAtesFirlat(g);
    FriendAtes(g);
    EnemyAtes(g);
    VurdumMu();
    DostVurduMu();
    DusmanVurduMu();
    BiriCarpistiMi();

    if (OyunBittiMi()) {
        KarakterYon.stop();
        AirCraftAtes.stop();
        DostAtesOlustur.stop();
        DusmanAtesOlustur.stop();
        dispose();
        String m = "Oyunu Kaybettiniz";
        JOptionPane.showMessageDialog(this, m);
        System.exit(0);
    }
}

   public void VurdumMu(){
      for(int j=0; j<ateslerSaga.size();j++){
      int atesX = ateslerSaga.get(j).getX();
      int atesY = ateslerSaga.get(j).getY();
      for(int i=0; i<EnemeyXs.size();i++){
          if (new Rectangle(atesX, atesY, 5, 5).intersects(new Rectangle(EnemeyXs.get(i), EnemeyYs.get(i), 10, 10))) {
              EnemeyXs.remove(i);
              EnemeyYs.remove(i);
              ateslerSaga.remove(j);
              break;
              
          }
      
      
      }
      
      
      }
      for(int j=0; j<ateslerSola.size();j++){
      int atesX = ateslerSola.get(j).getX();
      int atesY = ateslerSola.get(j).getY();
      for(int i=0; i<EnemeyXs.size();i++){
          if (new Rectangle(atesX, atesY, 5, 5).intersects(new Rectangle(EnemeyXs.get(i), EnemeyYs.get(i), 10, 10))) {
              EnemeyXs.remove(i);
              EnemeyYs.remove(i);
              ateslerSola.remove(j);
              break;
              
          }
      
      
      }
      
      
      }
      
       repaint();
        
   
   
   }
     public void DostVurduMu(){
      for(int j=0; j<DostatesSag.size();j++){
      int atesX = DostatesSag.get(j).getX();
      int atesY = DostatesSag.get(j).getY();
      for(int i=0; i<EnemeyXs.size();i++){
          if (new Rectangle(atesX, atesY, 5, 5).intersects(new Rectangle(EnemeyXs.get(i), EnemeyYs.get(i), 10, 10))) {
              EnemeyXs.remove(i);
              EnemeyYs.remove(i);
              DostatesSag.remove(j);
              break;
              
          }
      
      
      }
      
      
      }
      for(int j=0; j<DostatesSol.size();j++){
      int atesX = DostatesSol.get(j).getX();
      int atesY = DostatesSol.get(j).getY();
      for(int i=0; i<EnemeyXs.size();i++){
          if (new Rectangle(atesX, atesY, 5, 5).intersects(new Rectangle(EnemeyXs.get(i), EnemeyYs.get(i), 10, 10))) {
              EnemeyXs.remove(i);
              EnemeyYs.remove(i);
              DostatesSol.remove(j);
              break;
              
          }
      
      
      }
      
      
      }
      
       repaint();
        
   
   
   }
      public void DusmanVurduMu(){
      for(int j=0; j<DusmanatesSag.size();j++){
      int atesX = DusmanatesSag.get(j).getX();
      int atesY = DusmanatesSag.get(j).getY();
      for(int i=0; i<FriendXs.size();i++){
          if (new Rectangle(atesX, atesY, 5, 5).intersects(new Rectangle(FriendXs.get(i), FriendYs.get(i), 10, 10))) {
              FriendXs.remove(i);
              FriendYs.remove(i);
              DusmanatesSag.remove(j);
              
              
          }
      
      
      }
      
      
      }
      for(int j=0; j<DusmanatesSol.size();j++){
      int atesX = DusmanatesSol.get(j).getX();
      int atesY = DusmanatesSol.get(j).getY();
      for(int i=0; i<FriendXs.size();i++){
          if (new Rectangle(atesX, atesY, 5, 5).intersects(new Rectangle(FriendXs.get(i), FriendYs.get(i), 10, 10))) {
              FriendXs.remove(i);
              FriendYs.remove(i);
              DusmanatesSol.remove(j);
             
              
          }
      
      
      }
      
      
      }
   
      
       repaint();
   
   }
      public void BiriCarpistiMi(){
        for(int j=0; j<EnemeyXs.size();j++){
      int enemX = EnemeyXs.get(j);
      int enemY = EnemeyYs.get(j);
      for(int i=0; i<FriendXs.size();i++){
          if (new Rectangle(enemX, enemY, 10, 10).intersects(new Rectangle(FriendXs.get(i), FriendYs.get(i), 10, 10))) {
              FriendXs.remove(i);
              FriendYs.remove(i);
              EnemeyXs.remove(i);
              EnemeyYs.remove(i);
             
              
          }
      }
        }
         for(int j=0; j<FriendXs.size();j++){
      int friX = FriendXs.get(j);
      int friY = FriendYs.get(j);
      for(int i=0; i<EnemeyXs.size();i++){
          if (new Rectangle(friX, friY, 10, 10).intersects(new Rectangle(EnemeyXs.get(i), EnemeyYs.get(i), 10, 10))) {
              FriendXs.remove(i);
              FriendYs.remove(i);
              EnemeyXs.remove(i);
              EnemeyYs.remove(i);
             
              
          }
      
      
      }
      
      
      }
      
      
      
      }
   
    
    public boolean OyunBittiMi(){
       for(int j=0; j<DusmanatesSol.size();j++){
      int atesX = DusmanatesSol.get(j).getX();
      int atesY = DusmanatesSol.get(j).getY();
          if (new Rectangle(atesX, atesY, 5, 5).intersects(new Rectangle(nAircraftX, nAircraftY, 10, 10))) {
              return true;
              
          }
         

      }
      for(int j=0; j<DusmanatesSag.size();j++){
      int atesX = DusmanatesSag.get(j).getX();
      int atesY = DusmanatesSag.get(j).getY();
          if (new Rectangle(atesX, atesY, 5, 5).intersects(new Rectangle(nAircraftX, nAircraftY, 10, 10))) {
             return true;
          }
         
      }
        for (int i = 0; i < EnemeyXs.size(); i++) {
              int enemx = EnemeyXs.get(i);
              int enemy = EnemeyYs.get(i);
              if (new Rectangle(enemx, enemy, 10, 10).intersects(new Rectangle(nAircraftX, nAircraftY, 10, 10))) {
                  return true;
                  
              }
              
          }
    
    return false;
    
    }
    
    public void AirCraftAtesFirlat(Graphics g){
        
            g.setColor(Color.ORANGE);
              for(Ates ates:ateslerSaga){
                g.fillRect(ates.getX()-2, ates.getY()+3, 5, 5);
            
            }
                 for(Ates ates:ateslerSola){
                g.fillRect(ates.getX()-2, ates.getY()+3, 5, 5);
            
            }
         

    }
    public void FriendAtes(Graphics g){
                g.setColor(Color.MAGENTA);
              for(Ates ates:DostatesSag){
                g.fillRect(ates.getX()-2, ates.getY()+3, 5, 5);
            
            }
                 for(Ates ates:DostatesSol){
                g.fillRect(ates.getX()-2, ates.getY()+3, 5, 5);
            
            }
    
    
    }
    public void EnemyAtes(Graphics g){
                g.setColor(Color.BLUE);
              for(Ates ates:DusmanatesSag){
                g.fillRect(ates.getX()-2, ates.getY()+3, 5, 5);
            
            }
                 for(Ates ates:DusmanatesSol){
                g.fillRect(ates.getX()-2, ates.getY()+3, 5, 5);
            
            }
    
    
    }
    
    

 public void EnemyFriendMove(){
        for (int i = 0; i < EnemeyXs.size(); i++) {
             ArrayList<Integer> WhichWay = random.nextBoolean() ? EnemeyXs : EnemeyYs;
            int operation = random.nextBoolean() ? 10 : -10;
            if ((WhichWay.get(i) + operation)>=0&&(WhichWay.get(i) + operation)<=490) {
                 WhichWay.set(i, WhichWay.get(i) + operation);
            }
            else{
            WhichWay.set(i, WhichWay.get(i) - operation);
            }
           
            
        }
            for (int i = 0; i < FriendXs.size(); i++) {
        ArrayList<Integer> WhichWay = random.nextBoolean() ? FriendXs : FriendYs;
        int operation = random.nextBoolean() ? 10 : -10;
        if ((WhichWay.get(i) + operation)>=0&&(WhichWay.get(i) + operation)<=490) {
                 WhichWay.set(i, WhichWay.get(i) + operation);
            }
            else{
            WhichWay.set(i, WhichWay.get(i) - operation);
            }
    }
           
    }



    
   
   
   
    public void drawFriendEnemy(Graphics g){
      
        for (int i = 0; i < SCREEN_HEIGHT/UNIT_SIZE; i++) {
            g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT);
               g.drawLine(0, i*UNIT_SIZE, SCREEN_WITDH, i*UNIT_SIZE);
        }
         for (int i = 0; i < FriendXs.size(); i++) {
              g.setColor(Color.GREEN);
        g.fillRect(FriendXs.get(i), FriendYs.get(i), UNIT_SIZE, UNIT_SIZE);
          
        }
       
          for (int i = 0; i < EnemeyXs.size(); i++) {
            g.setColor(Color.BLACK);
             g.fillRect(EnemeyXs.get(i), EnemeyYs.get(i), UNIT_SIZE, UNIT_SIZE);
            
        }
        
        
           
       
        
    }
    public void drawAircraft(Graphics g){
    
    
        g.setColor(Color.red);
        g.fillRect(nAircraftX,nAircraftY,UNIT_SIZE,UNIT_SIZE);
        repaint();
    
    }
 

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int c = e.getKeyCode();
           if (c == KeyEvent.VK_A) {
               if(nAircraftX > 0) { 
                nAircraftX -= UNIT_SIZE;
              
            }
            
            
        }
           else if(c == KeyEvent.VK_D){
               if(nAircraftX < 490) { 
                nAircraftX += UNIT_SIZE;
               
            }
           
               
           }
            else if(c == KeyEvent.VK_W){
           if(nAircraftY > 0) { 
                nAircraftY -= UNIT_SIZE;
                
            }
               
           }       
           else if(c == KeyEvent.VK_S){
           if(nAircraftY < 490) { 
                nAircraftY += UNIT_SIZE;
            
            }
              
           }       
      
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    

        @Override
        public void mouseClicked(MouseEvent e) {}

        @Override
        public void mousePressed(MouseEvent e) {
            ateslerSaga.add(new Ates(nAircraftX,nAircraftY));
            ateslerSola.add(new Ates(nAircraftX,nAircraftY));
            
        }

        @Override
        public void mouseReleased(MouseEvent e){}

        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) {}


   
    
}

    


    
    
}
