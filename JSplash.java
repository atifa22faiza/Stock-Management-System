

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.*;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.border.*;
public class JSplash extends JWindow 
  implements KeyListener, MouseListener, ActionListener 
{
	ImageIcon image; 
JProgressBar pro;
int frameNumber=0;
Timer timer;
JLabel picture;
  public JSplash(JFrame parent,ImageIcon ic, int timeout) 
  { 
    super(parent); 
    image=ic;
  	int w = image.getIconWidth() + 5; 
    int h = image.getIconHeight() + 5; 

pro=new JProgressBar(1,100);
      pro.setValue(0);
        pro.setStringPainted(true);
 
         // pro.setIndeterminate(true);
        
         //pro.setForeground(new Color(179,216,139));

   timer = new Timer(30, this); 
   // timer.setRepeats(false); 
    timer.setInitialDelay(timeout); 




    Dimension screen = 
      Toolkit.getDefaultToolkit().getScreenSize(); 
    int x = (screen.width - w) / 2; 
    int y = (screen.height - h) / 2; 
    setBounds(x, y, w, h); 
  Container contentPane = getContentPane();
   
    //getContentPane().setLayout(new BorderLayout()); 
     picture = new JLabel(image); 
    	JLabel pic = new JLabel("Dinesh"); 
   getContentPane().add("Center", picture); 
   
    
   startAnimation();
    
   contentPane.add(pro,BorderLayout.SOUTH);
   //contentPane.add(pic,BorderLayout.PAGE_END);
    

    // Listen for key strokes 
    addKeyListener(this); 

    // Listen for mouse events from here and parent 
    addMouseListener(this); 
    parent.addMouseListener(this); 

    // Timeout after a while 
   
    //timer.start(); 
  } 
  	
  	
  	
  	public void startAnimation() {
        
 
            timer.start();
        
    }
  	
  	
  	

  public void block() 
  { 
    while(isVisible()) {} 
  } 

  // Dismiss the window on a key press 
  public void keyTyped(KeyEvent event) {} 
  public void keyReleased(KeyEvent event) {} 
  public void keyPressed(KeyEvent event) 
  { 
    setVisible(false); 
    dispose(); 
  } 

  // Dismiss the window on a mouse click 
  public void mousePressed(MouseEvent event) {} 
  public void mouseReleased(MouseEvent event) {} 
  public void mouseEntered(MouseEvent event1) 
  	{
  	} 
  public void mouseExited(MouseEvent event) {} 
  public void mouseClicked(MouseEvent event) 
  { 
    setVisible(false); 
    dispose(); 
  } 

  // Dismiss the window on a timeout 
  public void actionPerformed(ActionEvent e) 
    	{
        //Advance the animation frame.
         
        {
        
        if(frameNumber<101)
        {
        	frameNumber++;
        	 
         
        pro.setValue(frameNumber);
        
       if(frameNumber==100)
       {
       	pro.setIndeterminate(false);
        	pro.setValue(100);
        	 
        	timer.stop();
       	   setVisible(false); 
       }
                 }
        
         
    	}
    	
} 

}