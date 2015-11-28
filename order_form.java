/**
 * @(#)order_form.java
 *
 *
 * @author 
 * @version 1.00 2015/10/31
 */

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import java.io.*; 
import org.jfree.chart.ChartFactory; 
import org.jfree.chart.JFreeChart; 
import org.jfree.chart.ChartPanel;
import org.jfree.chart.plot.PlotOrientation; 
import org.jfree.data.category.DefaultCategoryDataset; 
import org.jfree.chart.ChartUtilities; 
import java.util.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.imageio.ImageIO;
import java.util.Calendar;

public class order_form extends JInternalFrame implements ItemListener,ActionListener {
	
	Connection con;
	Statement st;
	ResultSet rs;
	JLabel item_lbl,brand_lbl,supplier_lbl,qty_lbl,price_lbl,amount_lbl,prev_img;
	JTextField qty_txt,cost_txt;
	JComboBox brand_list,item_list,supperlier_list;
	DefaultCategoryDataset dataset ; 
	boolean flag=true;
	JList cart;
	DefaultListModel listModel;
	int amount,uid;
	JButton add_but,remove_but,submit_but,complete_btn;
	Vector<String> order=new Vector<String>();
	ImageIcon img;
    public order_form(int uid) {
    	super("Order Items");
    	
    	this.uid=uid;
    	setLayout(null);
    	item_lbl=new JLabel("Product ");
    	item_lbl.setBounds(80,30,150,30);
    	add(item_lbl);
    	 dataset = new DefaultCategoryDataset( ); 
    	
    	brand_list=new JComboBox();
    	brand_list.addItem("Select the Product");
    	brand_list.addItem("Moniter");
    	brand_list.addItem("Mouse");
    	brand_list.addItem("Keyboard");
    	brand_list.setBounds(200,30,150,30);
    	brand_list.addItemListener(this);
    	add(brand_list);
    	
    	brand_lbl=new JLabel("Brand ");
    	brand_lbl.setBounds(150,120,80,30);
    	add(brand_lbl);
    	
    	item_list=new JComboBox();
    	item_list.addItem("");
    	item_list.setBounds(150,150,150,30);
    	add(item_list);
    	
    	supplier_lbl=new JLabel("Supplier");
    	supplier_lbl.setBounds(320,120,80,30);
    	add(supplier_lbl);
    	
    	supperlier_list=new JComboBox();
    	item_list.addItem("");
    	supperlier_list.setBounds(320,150,150,30);
    	add(supperlier_list);
    	
    	try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con=DriverManager.getConnection("jdbc:odbc:stock1");
			st=con.createStatement();
			
    	}catch(Exception ex){
    		System.out.println ("Error in order "+ex);
    	}
    	
    	qty_lbl=new JLabel("quntity");
    	qty_lbl.setBounds(500,120,80,30);
    	add(qty_lbl);
    	
    	qty_txt=new JTextField();
    	qty_txt.setBounds(500,150,120,30);
    	add(qty_txt);
    	
    	price_lbl=new JLabel("Price ");
    	price_lbl.setBounds(650,120,180,30);
    	add(price_lbl);
    	
    	add_but=new JButton("Add to cart");
    	add_but.setBounds(650,150,120,30);
    	add_but.addActionListener(this);
    	add(add_but);
    	
    	 JFreeChart barChart = ChartFactory.createBarChart3D(
         "Supplier Review",             
         "Category",             
         "Score",             
         dataset,            
         PlotOrientation.VERTICAL,             
         true, true, false);
         
         ChartPanel pane=new ChartPanel(barChart);
         pane.setSize(600,600);
         JPanel chart_pane=new JPanel();
         chart_pane.setBounds(100,180,650,650);
         chart_pane.add(pane);
    	add(chart_pane);
    	
    	 listModel = new DefaultListModel();
    	cart=new JList(listModel);
    	cart.setBounds(800,180,250,200);
    	add(cart);
    	
    	amount_lbl=new JLabel();
    	amount_lbl.setBounds(830,500,250,30);
    	add(amount_lbl);
    	
    	remove_but=new JButton("Remove from cart");
    	remove_but.setBounds(800,450,250,30);
    	remove_but.addActionListener(this);
    	add(remove_but);
    	
    	complete_btn=new JButton("Complete Order");
    	complete_btn.setBounds(830,150,180,30);
    	complete_btn.addActionListener(this);
    	add(complete_btn);
    	amount=0;
    	
    	prev_img=new JLabel(); 
    	prev_img.setBounds(650,5,400,150);
    	add(prev_img);
    	
    	setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setSize(1020,650);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
    	if(ae.getSource().equals(add_but)){
    		if(brand_list.getSelectedItem().toString().length()>0 && qty_txt.getText().length()>0){
    				try{
    					int qt=Integer.parseInt(qty_lbl.getText().substring(qty_lbl.getText().indexOf(":")+1,qty_lbl.getText().length()));
    					int qty1=Integer.parseInt(qty_txt.getText());
    					System.out.println ("Qu "+qt+" qyy "+qty1);
    					if(qt<qty1){
    						JOptionPane.showMessageDialog(this,"Sorry stock is not available","error",JOptionPane.ERROR_MESSAGE);
    					}else{
							int price=0;
							int qty=Integer.parseInt(qty_txt.getText());
							rs=st.executeQuery("select price from "+brand_list.getSelectedItem().toString()+" where s_name='"+supperlier_list.getSelectedItem().toString()+"'");
							while(rs.next()){
							price=Integer.parseInt(rs.getString(1));
							}
							order.add(item_list.getSelectedItem().toString()+","+supperlier_list.getSelectedItem().toString()+","+qty_txt.getText()+","+brand_list.getSelectedItem().toString());
							listModel.addElement(item_list.getSelectedItem().toString()+" - "+brand_list.getSelectedItem().toString()+" * "+qty_txt.getText()+"= "+(price*qty)*1000);
							JOptionPane.showMessageDialog(this,"Item Added to the cart RS: "+price*qty,"Info",JOptionPane.INFORMATION_MESSAGE);
							amount=amount+((price*100)*qty);
							amount_lbl.setText("Amount = "+amount);
							qty_lbl.setText("Available : "+(qt-qty1));
    					}
    				
          }
          catch(Exception e){
          	System.out.println ("Error "+e);
          	e.printStackTrace();
          }
    		}else{
    			JOptionPane.showMessageDialog(this,"Please enter all fields values","Error ",JOptionPane.ERROR_MESSAGE);
    		}
    	}else if(ae.getSource().equals(remove_but)){
    		if(cart.getSelectedIndex()>=0){
    			String ams=listModel.getElementAt(cart.getSelectedIndex()).toString();
    			ams=ams.substring(ams.indexOf("=")+2,ams.length());
    			int am=Integer.parseInt(ams);
    			amount=amount-am;
    			amount_lbl.setText("Amount = "+amount);
    			order.remove(cart.getSelectedIndex());
    			listModel.removeElementAt(cart.getSelectedIndex());
    		}else{
    			JOptionPane.showMessageDialog(this,"Please select item to remove","Info",JOptionPane.WARNING_MESSAGE);
    		}
    	}else if(ae.getSource().equals(complete_btn)){
    		try{
    			int bid=0;
    		 rs=st.executeQuery("select max(bid) from bill");
	            if(rs.next())
	            {
	            	bid=rs.getInt(1)+1;                  
	            }
				DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
				//get current date time with Date()
				Date date = new Date();
				System.out.println(dateFormat.format(date));
	            for(int i=0;i<order.size();i++){
	            	String arr[]=order.get(i).toString().split(",");
	            	//System.out.println ("insert into bill values("+bid+","+uid+",'"+arr[0]+"','"+arr[1]+"',"+arr[2]+",'"+dateFormat.format(date)+"','"+arr[3]+"')");
	            	st.execute("insert into bill values("+bid+","+uid+",'"+arr[0]+"','"+arr[1]+"',"+arr[2]+",'"+dateFormat.format(date)+"','"+arr[3]+"')");
	            	//st.execute("update "+brand_list.getSelectedItem().toString()+" set ")
	            }
	            JOptionPane.showMessageDialog(this,"Order placed Sucessfully..!","Info",JOptionPane.INFORMATION_MESSAGE);
	            	dispose();
    		}catch(Exception ex){
    			System.out.println ("Exception "+ex);
    		}
    	}
    }
     public void itemStateChanged(ItemEvent event) {
       if (event.getSource().equals(brand_list)&&event.getStateChange() == ItemEvent.SELECTED) {
          //Object item = event.getItem();
            if(flag){
          	brand_list.removeItemAt(0);
          	flag=false;
          }
          try{
          	 rs=st.executeQuery("select distinct(b_name) from "+brand_list.getSelectedItem().toString());
          	 item_list.removeAllItems();
          	 item_list.removeItemListener(this);
          	 
          while(rs.next()){
          	item_list.addItem(rs.getString(1));
   
          }
          	item_list.addItemListener(this);
          	item_list.setSelectedIndex(1);
          	item_list.setSelectedIndex(0);
          }
          
          catch(Exception e){
          	System.out.println ("Error "+e);
          }
          
       }
       else if(event.getSource().equals(item_list)&&event.getStateChange() == ItemEvent.SELECTED){
    	try{
    		supperlier_list.removeAllItems();
    		supperlier_list.removeItemListener(this);
    		dataset.clear();
          	 rs=st.executeQuery("select dtime,s_name,price,ipath from "+brand_list.getSelectedItem().toString()+" where b_name='"+item_list.getSelectedItem().toString()+"'");
	          while(rs.next()){
	          	int time=rs.getInt(1);
	          	String sname=rs.getString(2);
	          	int price=Integer.parseInt(rs.getString(3));
	          	String pa=rs.getString(4);
	          	
	          	img=new ImageIcon(System.getProperty("user.dir")+"\\img\\"+pa);
	          	prev_img.setIcon(img);
	          	dataset.addValue(time, sname , "Time" );
	          	dataset.addValue(price, sname , "Price" );
	          	supperlier_list.addItem(sname);
	          }
	          rs=st.executeQuery("select quntity,price from "+brand_list.getSelectedItem().toString()+" where b_name='"+item_list.getSelectedItem().toString()+"' and s_name='"+supperlier_list.getSelectedItem().toString()+"'");
       		  rs.next();
       		   qty_lbl.setText("Available :"+rs.getString(1));
       		   price_lbl.setText("Price "+(Integer.parseInt(rs.getString(2)))*1000);
			supperlier_list.addItemListener(this);

          }
          catch(Exception e){
          	System.out.println ("Error "+e);
          }
       }else if(event.getSource().equals(supperlier_list)&&event.getStateChange()==ItemEvent.SELECTED){
       		try{
       			 rs=st.executeQuery("select quntity,price from "+brand_list.getSelectedItem().toString()+" where b_name='"+item_list.getSelectedItem().toString()+"' and s_name='"+supperlier_list.getSelectedItem().toString()+"'");
       			 rs.next();
       			 qty_lbl.setText("Available :"+rs.getString(1));
       			 price_lbl.setText("Price "+(Integer.parseInt(rs.getString(2)))*1000);
       		}catch(Exception ex){
       			System.out.println ("Error"+ex);
       		}
       }
    }    

}