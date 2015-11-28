/**
 * @(#)user_signup.java
 *
 *
 * @author 
 * @version 1.00 2015/10/31
 */

import javax.swing.*;
import java.awt.Font;
import java.awt.event.*;
import java.sql.*;
public class add_product extends JInternalFrame implements ActionListener{
	JLabel loc_lbl,qty_lbl,price_lbl,time_lbl,ipath_lbl,title,id_lbl,supp_lbl;
	JTextField id_txt,supp_txt,loc_txt,qty_txt,price_txt,time_txt,ipath_txt;

	String id,sname,product;
	Connection con;
	Statement st;
	ResultSet rs;
	JButton submit_btn,exit_btn,del_btn;
    public add_product(String product) {
    	super("Add Profile");
    	this.product=product;
    	setLayout(null);
    	try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con=DriverManager.getConnection("jdbc:odbc:stock1");
			st=con.createStatement();    	
    	}catch(Exception ex){
    		System.out.println (ex);
    	}
    	title=new JLabel("Add Product : "+product.toUpperCase());
    	title.setBounds(200,10,250,30);
    	title.setFont(new Font("Times New Roman",Font.BOLD,18));
    	add(title);
    	
    	id_lbl=new JLabel("ID : ");
    	id_lbl.setBounds(50,60,380,30);
    	add(id_lbl);
    	
    	id_txt=new JTextField();
    	id_txt.setBounds(200,60,120,30);
    	add(id_txt);
    	
    	
    	supp_lbl=new JLabel("Supplier ");
    	supp_lbl.setBounds(50,120,80,30);
    	add(supp_lbl);
    	
    	supp_txt=new JTextField();
    	supp_txt.setBounds(200,120,120,30);
    	add(supp_txt);
    	
    	loc_lbl=new JLabel("Location ");
    	loc_lbl.setBounds(50,180,80,30);
    	add(loc_lbl);
    	
    	loc_txt=new JTextField();
    	loc_txt.setBounds(200,180,150,30);
    	add(loc_txt);
    	
    	
    	qty_lbl=new JLabel("Qut ");
    	qty_lbl.setBounds(50,240,80,30);
    	add(qty_lbl);
    	
    	qty_txt=new JTextField();
    	qty_txt.setBounds(200,240,150,30);
    	add(qty_txt);
    	
    	
    	price_lbl=new JLabel("Price ");
    	price_lbl.setBounds(50,300,80,30);
    	add(price_lbl);
    	
    	price_txt=new JTextField();
    	price_txt.setBounds(200,300,150,30);
    	add(price_txt);
    	
    	time_lbl=new JLabel("Time ");
    	time_lbl.setBounds(50,370,120,30);
    	add(time_lbl);
    	
    	time_txt=new JTextField();
    	time_txt.setBounds(200,370,150,30);
    	add(time_txt);
    	
    	ipath_lbl=new JLabel("Image filename");
    	ipath_lbl.setBounds(50,450,150,30);
    	add(ipath_lbl);
    	
    	ipath_txt=new JTextField();
    	ipath_txt.setBounds(200,450,180,30);
    	add(ipath_txt);
    	
		submit_btn=new JButton("Add");
		submit_btn.setBounds(80,520,120,30);
		submit_btn.addActionListener(this);
		add(submit_btn);

    	
 		setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setSize(1020,650);
        setVisible(true);
    }
  
	public void actionPerformed(ActionEvent ae){
		
		if(ae.getSource().equals(exit_btn)){
			dispose();
		}else if(ae.getSource().equals(submit_btn)){
		
			if(supp_txt.getText().length()>0&&id_txt.getText().length()>0&&loc_txt.getText().length()>0 && qty_txt.getText().length() > 0 && price_txt.getText().length()>0 && time_txt.getText().length()> 0 && ipath_txt.getText().length() > 0){
				try{
					System.out.println ("select * from "+product+" where b_name='"+id_txt.getText()+"' and s_name='"+supp_txt.getText()+"'");
					rs=st.executeQuery("select * from "+product+" where b_name='"+id_txt.getText()+"' and s_name='"+supp_txt.getText()+"'");
					rs.next();
					if(rs.getString(1).length()<0){
						add_new();
				//
					}else{
						JOptionPane.showMessageDialog(this,"Product is already existing!");
					}
				//	System.out.println ("select s_location,quntity,price,time,ipath from ")");
			
			//	dispose();
				}catch(Exception e){
					if(e.getMessage().equals("[Microsoft][ODBC Driver Manager] Invalid cursor state"))
          				{
              			  add_new();
         				 }
         				 else{
         				 		JOptionPane.showMessageDialog(this,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
         				 }
				
				}
	
			}else{
				JOptionPane.showMessageDialog(this,"Please enter value for all fileds !","Error",JOptionPane.ERROR_MESSAGE);
			}
		}else if(ae.getSource().equals(del_btn)){
			try{
				System.out.println ("delete * from "+product+" where b_name='"+id+"' and s_name='"+sname+"'");
				st.execute("delete * from "+product+" where b_name='"+id+"' and s_name='"+sname+"'");
				JOptionPane.showMessageDialog(this,id+" deleted","info",JOptionPane.INFORMATION_MESSAGE);
				dispose();
			}catch(Exception ex){
				System.out.println (ex);
			}
		}
		
	}
	public void add_new(){
		try{
			System.out.println ("insert into "+product+" values ('"+id_txt.getText()+"','"+supp_txt.getText()+"','"+loc_txt.getText()+"','"+qty_txt.getText()+"','"+price_txt.getText()+"', "+time_txt.getText()+",'"+ipath_txt.getText()+"')");
		st.execute("insert into "+product+" values ('"+id_txt.getText()+"','"+supp_txt.getText()+"','"+loc_txt.getText()+"','"+qty_txt.getText()+"','"+price_txt.getText()+"', "+time_txt.getText()+",'"+ipath_txt.getText()+"')");
		JOptionPane.showMessageDialog(this,"Product Added! ","info",JOptionPane.INFORMATION_MESSAGE);
		cls();
		}
		catch(Exception ex){
			System.out.println (ex);
		}
		
	}
	public void cls(){
		id_txt.setText("");
		supp_txt.setText("");
		loc_txt.setText("");
		qty_txt.setText("");
		price_txt.setText("");
		time_txt.setText("");
		ipath_txt.setText("");
	}
    
    public static void main (String[] args) {
    	new user_signup();
}
}