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
public class update_product extends JInternalFrame implements ActionListener{
	JLabel loc_lbl,qty_lbl,price_lbl,time_lbl,ipath_lbl,title,id_lbl;
	JTextField loc_txt,qty_txt,price_txt,time_txt,ipath_txt;

	String id,sname,product;
	Connection con;
	Statement st;
	ResultSet rs;
	JButton submit_btn,exit_btn,del_btn;
    public update_product(String id,String sname,String product) {
    	super("Update Profile");
    	this.id=id;
    	this.sname=sname;
    	this.product=product;
    	setLayout(null);
    	
    	title=new JLabel("Update Product");
    	title.setBounds(200,10,250,30);
    	title.setFont(new Font("Times New Roman",Font.BOLD,18));
    	add(title);
    	
    	id_lbl=new JLabel("ID : "+id);
    	id_lbl.setBounds(50,60,380,30);
    	id_lbl.setForeground(java.awt.Color.BLUE);
    
    	
    	add(id_lbl);
    	
    	loc_lbl=new JLabel("Location ");
    	loc_lbl.setBounds(50,120,80,30);
    	add(loc_lbl);
    	
    	loc_txt=new JTextField();
    	loc_txt.setBounds(200,120,150,30);
    	add(loc_txt);
    	
    	
    	qty_lbl=new JLabel("Qut ");
    	qty_lbl.setBounds(50,180,80,30);
    	add(qty_lbl);
    	
    	qty_txt=new JTextField();
    	qty_txt.setBounds(200,180,150,30);
    	add(qty_txt);
    	
    	
    	price_lbl=new JLabel("Price ");
    	price_lbl.setBounds(50,240,80,30);
    	add(price_lbl);
    	
    	price_txt=new JTextField();
    	price_txt.setBounds(200,240,150,30);
    	add(price_txt);
    	
    	time_lbl=new JLabel("Time ");
    	time_lbl.setBounds(50,300,80,30);
    	add(time_lbl);
    	
    	time_txt=new JTextField();
    	time_txt.setBounds(200,300,150,30);
    	add(time_txt);
    	
    	ipath_lbl=new JLabel("Image filename");
    	ipath_lbl.setBounds(50,350,150,30);
    	add(ipath_lbl);
    	
    	ipath_txt=new JTextField();
    	ipath_txt.setBounds(200,350,180,30);
    	add(ipath_txt);
    	
		submit_btn=new JButton("Update");
		submit_btn.setBounds(80,400,120,30);
		submit_btn.addActionListener(this);
		add(submit_btn);
		
		del_btn=new JButton("Delete");
		del_btn.setBounds(250,400,120,30);
		del_btn.addActionListener(this);
		add(del_btn);

    	
    		
    	try{
    			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				 con=DriverManager.getConnection("jdbc:odbc:stock1");
				 st=con.createStatement();
				 rs=st.executeQuery("select s_location,quntity,price,dtime,ipath from "+product+" where b_name='"+id+"' and s_name='"+sname+"'");
				 if(rs.next()){
				 	loc_txt.setText(rs.getString(1));
				 	qty_txt.setText(rs.getString(2));
				 	price_txt.setText(rs.getString(3));
				 	time_txt.setText(""+rs.getInt(4));
				 	ipath_txt.setText(rs.getString(5));
				 }else{
				 	JOptionPane.showMessageDialog(this,"Not a valid brand name or supplier name","Error",JOptionPane.ERROR_MESSAGE);
				 	dispose();
				 }
    	}catch(Exception e){
    		System.out.println ("Error "+e);
    	}
    	
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
		
			if(loc_txt.getText().length()>0 && qty_txt.getText().length() > 0 && price_txt.getText().length()>0 && time_txt.getText().length()> 0 && ipath_txt.getText().length() > 0){
				try{
				//	System.out.println ("select s_location,quntity,price,time,ipath from ")");
				System.out.println ("UPDATE "+product+" SET s_location = '"+loc_txt.getText()+"', quntity = '"+qty_txt.getText()+"', price='"+price_txt.getText()+"', dtime = "+time_txt.getText()+",ipath='"+ipath_txt.getText()+"' where b_name='"+id+"' and s_name='"+sname+"'");
				st.execute("UPDATE "+product+" SET s_location = '"+loc_txt.getText()+"', quntity = '"+qty_txt.getText()+"', price='"+price_txt.getText()+"', dtime = "+time_txt.getText()+",ipath='"+ipath_txt.getText()+"' where b_name='"+id+"' and s_name='"+sname+"'");
				JOptionPane.showMessageDialog(this,"Product Updated! ","info",JOptionPane.INFORMATION_MESSAGE);
				dispose();
				}catch(Exception e){
					JOptionPane.showMessageDialog(this,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
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
    
    public static void main (String[] args) {
    	new user_signup();
}
}