import java.awt.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
class report extends JFrame implements ActionListener
{
	JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l;
	JTextField t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t;
	JButton b1,b2,b3;
	String[] a1 = new String [3];
	int a=30;
	//private JFrame frame;
	//Connection con;
	//Statement st,st1;
	//ResultSet rs,rs1;
	//PreparedStatement ps,ps1;

	report()
	{
        System.out.println("hi");
		call();
		

}
public void call()
{
	System.out.println("Report");
	
		setLayout(null);
		l1=new JLabel("Sales report");
		l1.setBounds(350,40,100,a);
		add(l1);
		
		l2=new JLabel("Monitor");
		l2.setBounds(260,110,100,a);
		add(l2);
		
		l3=new JLabel("Mouse");
		l3.setBounds(380,110,100,a);
		add(l3);
		
        l4=new JLabel("Keyboard");
		l4.setBounds(500,110,100,a);
		add(l4);
		   
		t1=new JTextField();
		t1.setBounds(260,160,100,a);
		add(t1);
		
		t2=new JTextField();
		t2.setBounds(380,160,100,a);
		add(t2);
		
        t7=new JTextField();
		t7.setBounds(500,160,100,a);
		add(t7);
		
		//t3=new JTextField();
		//t3.setBounds(260,210,100,a);
		//add(t3);
		
		//t4=new JTextField();
		//t4.setBounds(380,210,100,a);
		//add(t4);
		
       // t8=new JTextField();
		//t8.setBounds(500,210,100,a);
		//add(t8);
		
		
		
		//t5=new JTextField();
		//t5.setBounds(260,260,100,a);
		//add(t5);
		
		//t6=new JTextField();
		//t6.setBounds(380,260,100,a);
		//add(t6);
		
       // t9=new JTextField();
		//t9.setBounds(500,260,100,a);
		//add(t9);
		
		
		
		//l6=new JLabel("Lenova");
		//l6.setBounds(70,160,100,a);
		//add(l6);
		//l7=new JLabel("HP");
		//l7.setBounds(70,210,100,a);
		//add(l7);
		//l8=new JLabel("Dell");
		//l8.setBounds(70,260,100,a);
		//add(l8);
		
		b1=new JButton("view");
		b1.setBounds(350,350,100,30);
		b1.addActionListener(this);
		add(b1);
		
}
public void actionPerformed(ActionEvent ae)
	{
		try
		   {
			
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				Connection con=DriverManager.getConnection("jdbc:odbc:stock1");
			    Statement st=con.createStatement();
				System.out.println("Connection done");
				ResultSet rs=st.executeQuery("select * from s_stock");
				System.out.println("function1");
				int i=0;
					while(rs.next())
                      {
						System.out.println("function2");
						a1[i]=rs.getString(2);
						System.out.println("function3");
						i++;
					  }
				t1.setText(a1[2]);
				t2.setText(a1[1]);
				t7.setText(a1[0]);
				
			
		    }
		catch(Exception e)
		{
			System.out.println(e);
		} 
	}

}
	