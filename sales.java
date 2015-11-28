import java.awt.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
class sales extends JFrame implements ActionListener
{
	JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l;
	JTextField t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t;
	JButton b1,b2,b3;
	JComboBox j1,j2,j3,j4,j5,j6;
	int a=30;
	int te=0;
	String p2,p1;
	//private JFrame frame;
	//Connection con;
	//Statement st,st1;
	//ResultSet rs,rs1;
	//PreparedStatement ps,ps1;

	sales()
	{
        System.out.println("hi");
		call();
		setContentPane(new JLabel(new ImageIcon("bg-img.jpg")));
		

}
public void call()
{
	System.out.println("check");
	//frame = new JFrame("Main menu");
	setBounds(50,50,800,500);
		setVisible(true);
		//createConn();

		setLayout(null);

		j5 = new JComboBox();
    j5.addItem("Moniter");
	j5.addItem("Mouse");
	j5.addItem("Keyboard");
	
		j5.setBounds(350,40,100,a);
		add(j5);
		
       l10=new JLabel("Brand");
		l10.setBounds(70,110,100,a);
		add(l10);
		
		
		
		l2=new JLabel("Quntity");
		l2.setBounds(260,110,100,a);
		add(l2);
		
		l3=new JLabel("Price");
		l3.setBounds(380,110,100,a);
		add(l3);
		
        l4=new JLabel("Suppler name");
		l4.setBounds(500,110,100,a);
		add(l4);
		
		l5=new JLabel("Suppler Location");
		l5.setBounds(620,110,100,a);
		add(l5);
        
		t1=new JTextField();
		t1.setBounds(260,160,100,a);
		add(t1);
		
		t2=new JTextField();
		t2.setBounds(380,160,100,a);
		add(t2);
		
        j1=new JComboBox();
		j1.setBounds(500,160,100,a);
		j1.addActionListener(this);
		add(j1);
		
		j2=new JComboBox();
		j2.setBounds(620,160,100,a);
		add(j2);
		
		//t3=new JTextField();
		//t3.setBounds(260,210,100,a);
		//add(t3);
		
		//t4=new JTextField();
		//t4.setBounds(380,210,100,a);
		//add(t4);
		
        //j3=new JComboBox();
		//j3.setBounds(500,210,100,a);
		//add(j3);
		
		//j4=new JComboBox();
		//j4.setBounds(620,210,100,a);
		//add(j4);
		
		//t5=new JTextField();
		//t5.setBounds(260,260,100,a);
		//add(t5);
		
		//t6=new JTextField();
		//t6.setBounds(380,260,100,a);
		//add(t6);
		
        //j5=new JComboBox();
		//j5.setBounds(500,260,100,a);
		//add(j5);
		//
		j6=new JComboBox();
		j6.addItem("lenova");
	    j6.addItem("HP");
	    j6.addItem("Dell");
		j6.setBounds(70,160,100,a);
		j6.addActionListener(this);
		add(j6);
		//
		//l6=new JLabel("Lenova");
		//l6.setBounds(70,160,100,a);
		//add(l6);
		//l7=new JLabel("HP");
		//l7.setBounds(70,210,100,a);
		//add(l7);
		//l8=new JLabel("Dell");
		//l8.setBounds(70,260,100,a);
		//add(l8);
		b1=new JButton("Save");
		b1.setBounds(350,350,100,30);
		b1.addActionListener(this);
		add(b1);
		
		b2=new JButton("Finish Purchase");
		b2.setBounds(350,400,100,30);
		b2.addActionListener(this);
		add(b2);
		 //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   //frame.setPreferredSize(new Dimension(800, 600));
    //frame.pack();
    //frame.setLocationRelativeTo(null);
    //frame.setVisible(true);
}
public void actionPerformed(ActionEvent ae)
	{
	System.out.println("action2");
	System.out.println("action1");
		Object o=ae.getSource();
		if(o==j6)
		{
			System.out.println("action2");
			try
			{
				j1.removeAllItems();
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				Connection con=DriverManager.getConnection("jdbc:odbc:stock1");
			    Statement st=con.createStatement();
				System.out.println("Connection done");
				ResultSet rs=st.executeQuery("select * from b_name where brand_name='"+ j6.getSelectedItem() +"'");
				System.out.println("action3");
				if(rs!=null)
				{
					while(rs.next())
					{
						j1.addItem(rs.getString(2));
					}
					
				}
			}
			catch(Exception e)
			{
			System.out.println(e);	
			}
		}
		else if(o==j1)
		{
		System.out.println("action2");
			try
			{
				j2.removeAllItems();
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				Connection con=DriverManager.getConnection("jdbc:odbc:stock1");
			    Statement st=con.createStatement();
				System.out.println("Connection done");
				ResultSet rs=st.executeQuery("select * from b_name where suppler_name='"+ j1.getSelectedItem()+"'");
				System.out.println("action3");
				if(rs!=null)
				{
					while(rs.next())
					{
						j2.addItem(rs.getString(3));
					}
				}
			}
			catch(Exception e)
			{
			System.out.println(e);	
			}	
		}
		else if(o==b2)
		{
		   bill p = new bill();
	//super("purchase");
		p.setBounds(50,50,800,500);
		p.setVisible(true);
		p.setLayout(null);	
		}
		else if(o==b1)
		{
			p2=j5.getSelectedItem().toString();
			try
			{
				System.out.println("keyboard action action");
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				Connection con=DriverManager.getConnection("jdbc:odbc:stock1");
			   Statement st1=con.createStatement();
				System.out.println("Connection done");
				String qur="select price from "+ j5.getSelectedItem() +" where s_name='"+ j1.getSelectedItem() +"'";
				ResultSet rs=st1.executeQuery(qur);
				System.out.println("action3");
				if(rs!=null)
				{
					if(rs.next())
					{
						p1=rs.getString(1);
					}
				}
				int a,b,c;
				a=Integer.parseInt(p1);
				b=Integer.parseInt(t1.getText());
				c=a*b;
				t2.setText(Integer.toString(c));
                st1.executeUpdate("delete from temp");				
				String q="insert into temp values('"+j6.getSelectedItem()+"','"+j1.getSelectedItem()+"','"+j2.getSelectedItem()+"','"+t1.getText()+"','"+t2.getText()+ "')";
			//st.executeUpdate("insert into Moniter values('"+j6.getSelectedItem()+"','"+j1.getSelectedItem()+"','"+j2.getSelectedItem()+"','"+t1.getText()+"','"+t2.getText()+"')");
			    st1.executeUpdate(q);
				stock(p2);
				saless(p2);
                t1.setText("");
				t2.setText("");				
				//summary=i+" Records Inserted";
				//JOptionPane.showMessageDialog(null,summary,"Moniter",JOptionPane.INFORMATION_MESSAGE);
			}
			catch(Exception e){System.out.println(e);}	
		}
	}
	void stock(String i1)
	{
		
		if (i1=="Moniter")
		{
		try
		{
			
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				Connection con=DriverManager.getConnection("jdbc:odbc:stock1");
			    Statement st=con.createStatement();
				System.out.println("Connection done");
				ResultSet rs=st.executeQuery("select * from stocks where p_name='Moniter'");
				System.out.println("function1");
				
					if(rs.next())
                      {
						System.out.println("function2");
						te=Integer.parseInt(rs.getString(2));
						System.out.println("function3");
					  }
					
				
				System.out.println(te);
				int te1=Integer.parseInt(t1.getText());
				System.out.println(te1);
				te=te-te1;
				System.out.println("function4");
				System.out.println(te);
				st.executeUpdate("update Stocks set stock="+te+" where p_name='Moniter'");
				System.out.println("function5");
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	
	  }
	  else 
		  if (i1=="Mouse")
		  {
			 try
		   {
			
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				Connection con=DriverManager.getConnection("jdbc:odbc:stock1");
			    Statement st=con.createStatement();
				System.out.println("Connection done");
				ResultSet rs=st.executeQuery("select * from stocks where p_name='Mouse'");
				System.out.println("function1");
				
					if(rs.next())
                      {
						System.out.println("function2");
						te=Integer.parseInt(rs.getString(2));
						System.out.println("function3");
					  }
					
				
				System.out.println(te);
				int te1=Integer.parseInt(t1.getText());
				System.out.println(te1);
				te=te-te1;
				System.out.println("function4");
				System.out.println(te);
				st.executeUpdate("update Stocks set stock="+te+" where p_name='Mouse'");
				System.out.println("function5");
			
		    }
		catch(Exception e)
		{
			System.out.println(e);
		} 
	 }
	 else if(i1=="Keyboard")
		
	 {
		 try
		{
			
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				Connection con=DriverManager.getConnection("jdbc:odbc:stock1");
			    Statement st=con.createStatement();
				System.out.println("Connection done");
				ResultSet rs=st.executeQuery("select * from stocks where p_name='Keyboard'");
				System.out.println("function1");
				
					if(rs.next())
                      {
						System.out.println("function2");
						te=Integer.parseInt(rs.getString(2));
						System.out.println("function3");
					  }
					
				
				System.out.println(te);
				int te1=Integer.parseInt(t1.getText());
				System.out.println(te1);
				te=te-te1;
				System.out.println("function4");
				System.out.println(te);
				st.executeUpdate("update Stocks set stock="+te+" where p_name='Keyboard'");
				System.out.println("function5");
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	 }
	}
	void saless(String i1)
	{
		
		if (i1=="Moniter")
		{
		try
		{
			
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				Connection con=DriverManager.getConnection("jdbc:odbc:stock1");
			    Statement st=con.createStatement();
				System.out.println("Connection done");
				ResultSet rs=st.executeQuery("select * from s_stock where p_name='Moniter'");
				System.out.println("function1");
				
					if(rs.next())
                      {
						System.out.println("function2");
						te=Integer.parseInt(rs.getString(2));
						System.out.println("function3");
					  }
					
				
				System.out.println(te);
				int te1=Integer.parseInt(t1.getText());
				System.out.println(te1);
				te=te+te1;
				System.out.println("function4");
				System.out.println(te);
				st.executeUpdate("update s_stock set sal="+te+" where p_name='Moniter'");
				System.out.println("function5");
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	
	  }
	  else 
		  if (i1=="Mouse")
		  {
			 try
		   {
			
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				Connection con=DriverManager.getConnection("jdbc:odbc:stock1");
			    Statement st=con.createStatement();
				System.out.println("Connection done");
				ResultSet rs=st.executeQuery("select * from s_stock where p_name='Mouse'");
				System.out.println("function1");
				
					if(rs.next())
                      {
						System.out.println("function2");
						te=Integer.parseInt(rs.getString(2));
						System.out.println("function3");
					  }
					
				
				System.out.println(te);
				int te1=Integer.parseInt(t1.getText());
				System.out.println(te1);
				te=te+te1;
				System.out.println("function4");
				System.out.println(te);
				st.executeUpdate("update s_stock set sal="+te+" where p_name='Mouse'");
				System.out.println("function5");
			
		    }
		catch(Exception e)
		{
			System.out.println(e);
		} 
	 }
	 else if(i1=="Keyboard")
		
	 {
		 try
		{
			
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				Connection con=DriverManager.getConnection("jdbc:odbc:stock1");
			    Statement st=con.createStatement();
				System.out.println("Connection done");
				ResultSet rs=st.executeQuery("select * from s_stock where p_name='Keyboard'");
				System.out.println("function1");
				
					if(rs.next())
                      {
						System.out.println("function2");
						te=Integer.parseInt(rs.getString(2));
						System.out.println("function3");
					  }
					
				
				System.out.println(te);
				int te1=Integer.parseInt(t1.getText());
				System.out.println(te1);
				te=te+te1;
				System.out.println("function4");
				System.out.println(te);
				st.executeUpdate("update s_stock set sal="+te+" where p_name='Keyboard'");
				System.out.println("function5");
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	 }
	}
}
	