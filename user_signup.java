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
public class user_signup extends JFrame implements ActionListener{
	JLabel id_lbl,name_lbl,address_lbl,pass_lbl,mobile_lbl,title;
	JTextField name_txt,address_txt,mobile_txt;
	JPasswordField pass;
	int id;
	Connection con;
	Statement st;
	ResultSet rs;
	JButton submit_btn,exit_btn;
    public user_signup() {
    	super("Register");
    	setLayout(null);
    	
    	title=new JLabel("Register");
    	title.setBounds(200,10,250,30);
    	title.setFont(new Font("Times New Roman",Font.BOLD,18));
    	add(title);
    	
    	id_lbl=new JLabel("ID");
    	id_lbl.setBounds(50,60,380,30);
    	id_lbl.setForeground(java.awt.Color.BLUE);
    	
    	try{
    			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				 con=DriverManager.getConnection("jdbc:odbc:stock1");
				 st=con.createStatement();
				 rs=st.executeQuery("select max(uid) from user_details");
            if(rs.next())
            {
            	id=rs.getInt(1)+1;
                id_lbl.setText("Your User ID : "+id);                   
            }
    	}catch(Exception e){
    		System.out.println ("Error "+e);
    	}
    	
    	add(id_lbl);
    	
    	name_lbl=new JLabel("Name ");
    	name_lbl.setBounds(50,120,80,30);
    	add(name_lbl);
    	
    	name_txt=new JTextField();
    	name_txt.setBounds(200,120,150,30);
    	add(name_txt);
    	
    	
    	pass_lbl=new JLabel("Password ");
    	pass_lbl.setBounds(50,180,80,30);
    	add(pass_lbl);
    	
    	pass=new JPasswordField();
    	pass.setBounds(200,180,150,30);
    	add(pass);
    	
    	
    	address_lbl=new JLabel("Address ");
    	address_lbl.setBounds(50,240,80,30);
    	add(address_lbl);
    	
    	address_txt=new JTextField();
    	address_txt.setBounds(200,240,150,30);
    	add(address_txt);
    	
    	mobile_lbl=new JLabel("Mobile ");
    	mobile_lbl.setBounds(50,300,80,30);
    	add(mobile_lbl);
    	
    	mobile_txt=new JTextField();
    	mobile_txt.setBounds(200,300,150,30);
    	add(mobile_txt);
    	
    	 submit_btn=new JButton("Reegister");
    	submit_btn.setBounds(80,350,120,30);
    	submit_btn.addActionListener(this);
    	add(submit_btn);
    	
 		 exit_btn=new JButton("Exit");
    	exit_btn.setBounds(210,350,120,30);
    	exit_btn.addActionListener(this);
    	add(exit_btn);
    	
    	setDefaultCloseOperation(2);
    	setSize(500,600);
    	setVisible(true);
    }
  
	public void actionPerformed(ActionEvent ae){
		
		if(ae.getSource().equals(exit_btn)){
			Login lo=new Login();
			dispose();
		}else if(ae.getSource().equals(submit_btn)){
			String pass1=new String(pass.getPassword());
			if(name_txt.getText().length()>0 && pass1.length()> 0 && address_txt.getText().length()>0 && mobile_txt.getText().length()>0){
				try{
					System.out.println ("insert into user_details values("+id+",'"+name_txt.getText()+"','"+address_txt.getText()+"','"+pass1+"',"+mobile_txt.getText()+")");
				st.execute("insert into user_details values("+id+",'"+name_txt.getText()+"','"+address_txt.getText()+"','"+pass1+"',"+mobile_txt.getText()+")");
				JOptionPane.showMessageDialog(this,"Registration Completed! ","info",JOptionPane.INFORMATION_MESSAGE);
					Login lo=new Login();
				dispose();
				}catch(Exception e){
					JOptionPane.showMessageDialog(this,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
				}
	
			}else{
				JOptionPane.showMessageDialog(this,"Please enter value for all fileds !","Error",JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}
    

}