/**
 * @(#)Main.java
 *
 *
 * @author 
 * @version 1.00 2015/10/31
 */
 
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.UIManager;



public class Main {

   public static void main (String[] args) {
   	Login lg=new Login();
   	ImageIcon im=new ImageIcon(Toolkit.getDefaultToolkit().getImage("img/spl.jpg"));
   		JSplash splash = new JSplash(lg,im,3000); 
  	splash.setVisible(true); 
	splash.requestFocus(); 
	splash.block();	

        
        
       
        
        lg.setVisible(true);
   	lg=null;
}
    
}