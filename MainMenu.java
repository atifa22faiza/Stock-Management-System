import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.util.*;
import javax.swing.*;
import java.sql.*;
import java.awt.image.BufferedImage;
import javax.swing.UIManager;
import java.net.URL;
public class MainMenu extends JFrame implements  ActionListener
{
	public static int b;
  private JFrame frame;
  private JMenuBar menuBar;
  private JMenu fileMenu;
  private JMenuItem report;
  private JMenuItem openMenuItem;
  private JMenuItem cutMenuItem;
  private JMenuItem copyMenuItem;
  private JMenuItem pasteMenuItem,exit_menu,order_menu,user_menu,logout,view_menu;
  JMenuItem mosue_up,key_up,moni_up,mouse_rep,keyboard_rep,moniter_rep,rep_bydate,win_ui,metal_ui,motif_ui,add_key,add_mouse,add_mon;
  JMenu prod_menu,update_menu,ui_menu,add_menu;
  BufferedImage img;JDesktopPane desktop;
  int id;
  	Connection con;
	Statement st;
	ResultSetMetaData rsd;
	ResultSet rs;

  public MainMenu(String mode,int id)
 {
	super("Main menu");
	setLayout(null);
	// setContentPane(new JLabel(new ImageIcon("bg-img.jpg")));
	menuBar = new JMenuBar();
	this.id=id;
    
	fileMenu = new JMenu("Main");
	if(mode=="customer"){
		order_menu=new JMenuItem("Order");
		order_menu.addActionListener(this);
		fileMenu.add(order_menu);
		
		user_menu=new JMenuItem("Edit Profile");
		user_menu.addActionListener(this);
		fileMenu.add(user_menu);
		
		view_menu=new JMenuItem("View My orders");
		view_menu.addActionListener(this);
		fileMenu.add(view_menu);
		
	}else{
		update_menu=new JMenu("Update/Delete Product");
		fileMenu.add(update_menu);
		
		key_up=new JMenuItem("Keyboard");
		key_up.addActionListener(this);
		update_menu.add(key_up);
		
		moni_up=new JMenuItem("Moniter");
		moni_up.addActionListener(this);
		update_menu.add(moni_up);
		
		mosue_up=new JMenuItem("Mouse");
		mosue_up.addActionListener(this);
		update_menu.add(mosue_up);
		
		
		add_menu=new JMenu("Add new Product");
		fileMenu.add(add_menu);
		
		add_key=new JMenuItem("Keyboard");
		add_key.addActionListener(this);
		add_menu.add(add_key);
		
		add_mon=new JMenuItem("Moniter");
		add_mon.addActionListener(this);
		add_menu.add(add_mon);
		
		add_mouse=new JMenuItem("Mouse");
		add_mouse.addActionListener(this);
		add_menu.add(add_mouse);
		
		
		
		
		
		prod_menu=new JMenu("Product Report");
		fileMenu.add(prod_menu);
		
		mouse_rep=new JMenuItem("Mouse");
		mouse_rep.addActionListener(this);
		prod_menu.add(mouse_rep);
		
		moniter_rep=new JMenuItem("Moniter");
		moniter_rep.addActionListener(this);
		prod_menu.add(moniter_rep);
		
		keyboard_rep=new JMenuItem("Keyboard");
		keyboard_rep.addActionListener(this);
		prod_menu.add(keyboard_rep);
		
		rep_bydate=new JMenuItem("Report by date");
		rep_bydate.addActionListener(this);
		fileMenu.add(rep_bydate);
		
		
		
		/*openMenuItem = new JMenuItem("Monitor");
		cutMenuItem = new JMenuItem("Mouse");
		copyMenuItem = new JMenuItem("Keyboard");
		pasteMenuItem = new JMenuItem("Overall");
		report = new JMenuItem("Report");
	
		openMenuItem.addActionListener(this);
		cutMenuItem.addActionListener(this);
		copyMenuItem.addActionListener(this);
		pasteMenuItem.addActionListener(this);
		report.addActionListener(this);
		
		fileMenu.add(openMenuItem);
		fileMenu.add(cutMenuItem);
		fileMenu.add(copyMenuItem);
		fileMenu.add(pasteMenuItem);
		fileMenu.add(pasteMenuItem);
		fileMenu.add(report);*/
	}
		try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con=DriverManager.getConnection("jdbc:odbc:stock1");
			st=con.createStatement();
			
    	}catch(Exception ex){
    		System.out.println ("Error in connection "+ex);
    	}
    	
    	
    	
    	
    	ui_menu=new JMenu("Set Look and feel");
    	
		win_ui=new JMenuItem("Windows UI");
		win_ui.addActionListener(this);
		ui_menu.add(win_ui);
		
		metal_ui=new JMenuItem("Metal UI");
		metal_ui.addActionListener(this);
		ui_menu.add(metal_ui);
		
		motif_ui=new JMenuItem("Motif UI");
		motif_ui.addActionListener(this);
		ui_menu.add(motif_ui);
	
    	fileMenu.add(ui_menu);
	
		exit_menu = new JMenuItem("Exit");
		exit_menu.addActionListener(this);
		
		
		logout=new JMenuItem("Logout");
		logout.addActionListener(this);
	
		fileMenu.addSeparator();

		fileMenu.add(logout);
		fileMenu.add(exit_menu);
		menuBar.add(fileMenu);
	
	


	setJMenuBar(menuBar);

try{
	img = ImageIO.read(new File("bg-img.jpg"));
}
catch(Exception e){
	System.out.println(e);
}
 desktop = new JDesktopPane(){
	 @Override
            protected void paintComponent(Graphics grphcs) {
                super.paintComponent(grphcs);
                grphcs.drawImage(img, 0, 0, null);
            }

        };
   desktop.setVisible(true);

	Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	desktop.setSize(dimension.width-50,650);
	add(desktop);
   	setSize(dimension.width, dimension.height-50);
	setVisible(true);
	setDefaultCloseOperation(2);
	 
 }

 
  public void actionPerformed(ActionEvent ev)
  {
	  System.out.println("action");
	   Object source = ev.getSource();
	if(source==exit_menu){
	   	System.exit(0);
	   }
	   else if(ev.getSource().equals(order_menu)){
	   	order_form order_obj=new order_form(id);
	   	desktop.add(order_obj);
	   	order_obj.requestFocus();
	   }else if(ev.getSource().equals(user_menu)){
	   		update_profile user=new update_profile(id);
	   	desktop.add(user);
	   	user.requestFocus();
	   }else if(ev.getSource().equals(view_menu)){
	   		try{
	   			getexecute("select * from bill where uid="+id);
	   			
	   		}catch(Exception ex){
	   			System.out.println ("Error "+ex);
	   		}
	   }
	   else if(ev.getSource().equals(logout)){
	   		dispose();
	   		Login lg=new Login();
		   }else if(ev.getSource().equals(moniter_rep)){
		   		product_rep obj=new product_rep("Moniter");
		   		desktop.add(obj);
		   		obj.requestFocus();
		   }
		   else if(ev.getSource().equals(mouse_rep)){
		   		product_rep obj=new product_rep("Mouse");
		   		desktop.add(obj);
		   		obj.requestFocus();
		   }
		   else if(ev.getSource().equals(keyboard_rep)){
		   		product_rep obj=new product_rep("Keyboard");
		   		desktop.add(obj);
		   		obj.requestFocus();
		   } else if(ev.getSource().equals(rep_bydate)){
		   		String inp=JOptionPane.showInputDialog(this,"Enter the dates in format mm/dd/yyyy , mm/dd/yyyy (Ex 11/01/2015,11/05/2015)","Enter Date",JOptionPane.INFORMATION_MESSAGE);
		   		String dates[]=inp.split(",");
		   		System.out.println ("fro "+dates[0]+" to "+dates[1]);
		   		date_rep dobj=new date_rep(dates[0],dates[1]);
		   		desktop.add(dobj);
		   		dobj.requestFocus();
		   }else if(ev.getSource().equals(key_up)){
		   		String inp=JOptionPane.showInputDialog(this,"Enter the brand id,supplier name (Keyboard Ex :HP 580,SIMOS)","Enter details",JOptionPane.INFORMATION_MESSAGE);
		   		String pro[]=inp.split(",");
		   		//System.out.println ("fro "+dates[0]+" to "+dates[1]);
		   		update_product dobj=new update_product(pro[0],pro[1],"Keyboard");
		   		desktop.add(dobj);
		   		dobj.requestFocus();
		   }
		   else if(ev.getSource().equals(mosue_up)){
		   		String inp=JOptionPane.showInputDialog(this,"Enter the brand id,supplier name (Keyboard Ex :HP 580,SIMOS)","Enter details",JOptionPane.INFORMATION_MESSAGE);
		   		String pro[]=inp.split(",");
		   		//System.out.println ("fro "+dates[0]+" to "+dates[1]);
		   		update_product dobj=new update_product(pro[0],pro[1],"Mouse");
		   		desktop.add(dobj);
		   		dobj.requestFocus();
		   }else if(ev.getSource().equals(moni_up)){
		   	String inp=JOptionPane.showInputDialog(this,"Enter the brand id,supplier name (Keyboard Ex :HP 580,SIMOS)","Enter details",JOptionPane.INFORMATION_MESSAGE);
		   		String pro[]=inp.split(",");
		   		//System.out.println ("fro "+dates[0]+" to "+dates[1]);
		   		update_product dobj=new update_product(pro[0],pro[1],"Moniter");
		   		desktop.add(dobj);
		   		dobj.requestFocus();
		   }else if(ev.getSource().equals(win_ui)){
		   	       try {
    						UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			SwingUtilities.updateComponentTreeUI(this);
						}
						catch (Exception ex) {
						}
		   }
		   else if(ev.getSource().equals(metal_ui)){
		   	       try {
    						UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
			SwingUtilities.updateComponentTreeUI(this);
						}
						catch (Exception ex) {
						}
		   }
		   else if(ev.getSource().equals(motif_ui)){
		   	       try {
    						UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
			SwingUtilities.updateComponentTreeUI(this);
						}
						catch (Exception ex) {
						}
		   }
		   else if(ev.getSource().equals(add_key)){
				add_product aobj=new add_product("Keyboard");
				desktop.add(aobj);
				aobj.requestFocus();
		   }
		    else if(ev.getSource().equals(add_mouse)){
				add_product aobj=new add_product("Mouse");
				desktop.add(aobj);
				aobj.requestFocus();
		   }
		    else if(ev.getSource().equals(add_mon)){
				add_product aobj=new add_product("Moniter");
				desktop.add(aobj);
				aobj.requestFocus();
		   }
		//JTabbedPane jtp = new JTabbedPane();
		//add(jtp);
		//jtp.addTab("Purchase Info", new PurchaseInfo());
		//jtp.addTab("Purchase", new Purchase());
		//setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    //p.setModal(true);
    //p.setVisible(true);
  }
  public static void main (String[] args) {
  	MainMenu obj=new MainMenu("admin",2);
  	obj=null;
}
public void getexecute(String str) throws Exception
	{
		 Vector<Vector<Object>> sel_tab_record=new Vector<Vector<Object>>();
		  Vector<Object> sel_col=null;
		
			rs=st.executeQuery(str);
    		rsd=rs.getMetaData();
    		//System.out.println (rsd.getColumnCount());
    		while(rs.next())
    		{
    		Vector<Object>	sel_tab=new Vector<Object>();
    		sel_col=new Vector<Object>();	
    		for(int i=1;i<=rsd.getColumnCount();i++)
    		{
    			
    			sel_tab.add(rs.getObject(i));
    		}
    			sel_tab_record.add(sel_tab);

    		}
    		for(int j=1;j<=rsd.getColumnCount();j++)
    		{
    			sel_col.add(rsd.getColumnName(j));
    		}
    		JDialog dia=new JDialog(this,true);
    		dia.setTitle("Orders list");
			JTable JAcc_JTab=new JTable();
			DefaultTableModel tm=new javax.swing.table.DefaultTableModel(
            5,2
        ) {
            
            @Override
            public boolean isCellEditable(int row, int col) {
            //Note that the data/cell address is constant,
            //no matter where the cell appears onscreen.
            
                return false;
            
            }
            
        };
			JAcc_JTab.setModel(tm);
			JAcc_JTab.setVisible(true);
			JAcc_JTab.doLayout();
			JAcc_JTab.setGridColor(Color.red);
			JAcc_JTab.setForeground(Color.blue);
			JAcc_JTab.setSelectionForeground(new Color(0,0,255));
			JAcc_JTab.setSelectionBackground(new Color(204,153,204));
			tm.setDataVector(sel_tab_record,sel_col);
			JScrollPane jsd=new JScrollPane(JAcc_JTab,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			jsd.setVisible(true);
			Dimension scr=Toolkit.getDefaultToolkit().getScreenSize();
			jsd.setBounds(0,0,600,500);
			dia.add(jsd);
			dia.setBounds(50,50,620,620);   		
			dia.setVisible(true);
			//return sel_tab_record;
		
	}
	

}

  
  