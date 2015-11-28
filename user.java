import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class user implements Runnable, ActionListener
{
  private JFrame frame;
  private JMenuBar menuBar;
  private JMenu fileMenu;
  private JMenuItem openMenuItem;
  private JMenuItem cutMenuItem;
  private JMenuItem copyMenuItem;
  private JMenuItem view;
  public static void main(String[] args)
  {
	  
    System.setProperty("apple.laf.useScreenMenuBar", "true");
    SwingUtilities.invokeLater(new user());
  }
 
  public void run()
  {
    
	frame = new JFrame("Main menu");
    menuBar = new JMenuBar();
    
    
    fileMenu = new JMenu("Main");
    openMenuItem = new JMenuItem("Monitor");
    cutMenuItem = new JMenuItem("Mouse");
    copyMenuItem = new JMenuItem("Keyboard");
	view = new JMenuItem("view");
    openMenuItem.addActionListener(this);
	 cutMenuItem.addActionListener(this);
	copyMenuItem.addActionListener(this);
	view.addActionListener(this);
    fileMenu.add(openMenuItem);
    fileMenu.add(cutMenuItem);
    fileMenu.add(copyMenuItem);
	fileMenu.add(view);
    menuBar.add(fileMenu);
    frame.setJMenuBar(menuBar);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setPreferredSize(new Dimension(800, 600));
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
  public void actionPerformed(ActionEvent ev)
  {
	  System.out.println("action");
	   Object source = ev.getSource();
	    System.out.println(source);
	   if(source == openMenuItem)
	   {
		   System.out.println("action1");
		    sales p = new sales();
	//super("purchase");
		p.setBounds(50,50,800,500);
		p.setVisible(true);
		p.setLayout(null);
		//createConn();
	   }
	   else if(source == cutMenuItem)
		   
	   {
		   System.out.println("action2");
		   sales p = new sales();
	//super("purchase");
		p.setBounds(50,50,800,500);
		p.setVisible(true);
		p.setLayout(null);
	   }
	   else if(source == copyMenuItem)
	   {
		   System.out.println("action2");
		   sales p = new sales();
	//super("purchase");
		p.setBounds(50,50,800,500);
		p.setVisible(true);
		p.setLayout(null);
	   }
	   else if(source == view)
	   {
		   //System.out.println("action2");
		  // GraphPanel p = new GraphPanel();
	//super("purchase");
		//p.setBounds(50,50,800,500);
		//p.setVisible(true);
		//p.setLayout(null);
	   }
	  
   



		//JTabbedPane jtp = new JTabbedPane();
		//add(jtp);
		//jtp.addTab("Purchase Info", new PurchaseInfo());
		//jtp.addTab("Purchase", new Purchase());



		//setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    //p.setModal(true);
    //p.setVisible(true);
  }
}

  
  