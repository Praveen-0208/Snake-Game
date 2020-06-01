package snakegame;

import java.awt.Color;
import static java.awt.Color.black;
import static java.awt.Color.green;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SnakeGame{    
    
    public static void main(String[] args) {
       JFrame board = new JFrame();

       board.setTitle("Snake game");
       board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       board.getContentPane().add(new snakemove());
       board.pack();
       board.setResizable(false);
       board.setVisible(true);       
    }
}
class snakemove extends JPanel implements ActionListener,KeyListener
{
    Timer time = new Timer(50, this);
    int fx=400,fy=80; //initial positions for the food
    
   
    public snakemove()
    {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        setPreferredSize(new Dimension(600, 600));
        //initial location of the snake
        snakex[2]=20;
        snakex[1]=30;
        snakex[0]=40;
            
        snakey[2]=50;
        snakey[1]=50;
        snakey[0]=50;
        
        
    }
    //length and width of a single block
    int snklen=10,snkwth=10;
    
    int move=1;
    
    private int[] snakex=new int[750];
    private int[] snakey=new int[750];
    

    Random random = new Random();

    //length of the snake
    int parts=3;
    
    boolean up=false;
    boolean down=false;
    boolean right = false;
    boolean left = false;
    
    boolean flag = true;
    
    boolean flag1=false,flag2=false;

    @Override
     public void paintComponent(Graphics g)
    {
        this.setBackground(black);
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g2);
        g2.setColor(green);
        g2.drawRect(10, 40, 570, 500);   //for border 

        time.start();
        
        for(int i=0;i<parts;i++)
        {
          if(i==0)
            {
                g2.setColor(Color.magenta);
            }
            else
            {
            g2.setColor(green);
            }
            g2.fillRect(snakex[i], snakey[i], snklen, snkwth);
            g2.setColor(Color.MAGENTA);
            g2.drawRect(snakex[i], snakey[i], snklen, snkwth);
            
        }    
       }
    @Override
     public void paint(Graphics g)
     {
         super.paint(g);
         flag1=Arrays.asList(snakex).contains(fx);
         flag2=Arrays.asList(snakey).contains(fy);
         if(!flag1 && !flag2)//to avoid overlapping of the food and the body of the snake
         {
            Graphics2D g2 = (Graphics2D)g;
            g2.setColor(Color.red);
            g2.fillRect(fx,fy, 10, 10); 
         }
         else 
         {
            fx=(random.nextInt(57)+1)*10;
            fy=(random.nextInt(50)+4)*10;   
         }
               
        if(snakex[0]==fx && snakey[0]==fy)//check if the snake collides with the food
        {
            parts++;
            fx=(random.nextInt(57)+1)*10;
            fy=(random.nextInt(50)+4)*10;
        }

     }
    @Override
    public void actionPerformed(ActionEvent e) {
      for(int i = parts;i<750;i++)
        {
            snakex[i]=0;snakey[i]=0;
        }
       if(up)
       {
           for(int i = parts-1;i>=0;i--)
           {
               snakex[i+1]=snakex[i];
           }
           for(int i = parts;i>=0;i--)
           {
               if(i==0)
               {
                   snakey[i]=snakey[i]-10;
               }
               else
               {
                   snakey[i]=snakey[i-1];
               }
               if(snakey[i]<40)
               {
                   snakey[i]=530;
               }
           
           }
           for(int i = 1;i<parts;i++)
           {
               if(snakex[0] == snakex[i] && snakey[0] == snakey[i])
           {
               JOptionPane.showMessageDialog(this, "GAME OVER...TRY AGAIN", "GAME OVER", JOptionPane.ERROR_MESSAGE);
               System.exit(0);
           }
           }
           revalidate();
           repaint();  
           flag = true;
       }
       if(down)
       {
           for(int i = parts-1;i>=0;i--)
           {
               snakex[i+1]=snakex[i];
           }
           for(int i = parts;i>=0;i--)
           {
               if(i==0)
               {
                   snakey[i]=snakey[i]+10;
               }
               else
               {
                   snakey[i]=snakey[i-1];
               }
               if(snakey[i]>530)
               {
                   snakey[i]=40;
               }
           }
           for(int i = 1;i<parts;i++)
           {
               if(snakex[0] == snakex[i] && snakey[0] == snakey[i])
           {
               JOptionPane.showMessageDialog(this, "GAME OVER...TRY AGAIN", "GAME OVER", JOptionPane.ERROR_MESSAGE);
               System.exit(0);
           }
           }
           revalidate();
           repaint();
           flag = true;
       }
       if(left)
       {
           for(int i = parts-1;i>=0;i--)
           {
               snakey[i+1]=snakey[i];
           }
           for(int i = parts;i>=0;i--)
           {
               if(i==0)
               {
                   snakex[i]=snakex[i]-10;
               }
               else
               {
                   snakex[i]=snakex[i-1];
               }
               if(snakex[i]<10)
               {
                   snakex[i]=570;
               }
           }
           for(int i = 1;i<parts;i++)
           {
               if(snakex[0] == snakex[i] && snakey[0] == snakey[i])
           {
               JOptionPane.showMessageDialog(this, "GAME OVER...TRY AGAIN", "GAME OVER", JOptionPane.ERROR_MESSAGE);
               System.exit(0);
           }
           }
           revalidate();
           repaint();   
           flag = true;
       }
       if(right)
       {
           for(int i = parts-1;i>=0;i--)
           {
               snakey[i+1]=snakey[i];
           }
           for(int i = parts;i>=0;i--)
           {
                 if(i==0)
               {
                   snakex[i]=snakex[i]+10;
               }
               else
               {
                   snakex[i]=snakex[i-1];
               }
               if(snakex[i]>570)
               {
                   snakex[i]=10;
               }  
           }   
           for(int i = 1;i<parts;i++)
           {
               if(snakex[0] == snakex[i] && snakey[0] == snakey[i])
           {
               JOptionPane.showMessageDialog(this, "GAME OVER...TRY AGAIN", "GAME OVER", JOptionPane.ERROR_MESSAGE);
               System.exit(0);
           }
           }           
           revalidate();
           repaint();
           flag = true;
       }
    }
    
    ///since implementing an interface,all the methods have to be overridden.
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int keycode = e.getKeyCode();
        if(flag)//to make sure that every keypress event happens one after another to avoid the snake colliding with itself
        {
        switch(keycode)
        {
            case KeyEvent.VK_UP://up
            {
                up=true;
                if(!down)
                {
                    up=true;
                }
                else
                {
                    up=false;
                    down=true;
                }
                left=right=false;
                flag = false;
                break;
            }
            case KeyEvent.VK_DOWN://down
            {
                down=true;
                if(!up)
                {
                    down=true;
                }
                else
                {
                    down=false;
                    up=true;
                }
                left=right=false;
                flag = false;
                break;
            }
            case KeyEvent.VK_LEFT://left
            {
                left=true;
                if(!right)
                {
                    left=true;
                }
                else
                {
                    left=false;
                    right=true;
                }
                up=down=false;
                flag = false;
                break;
            }
            case KeyEvent.VK_RIGHT://right
            {
                right=true;
                if(!left)
                {
                    right=true;
                }
                else
                {
                    left=true;
                    right=false;
                }
                up=down=false;
                flag = false;
                break;
            }
            case 32:
                System.exit(0);
                flag = false;
                break;
        }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
    
}
