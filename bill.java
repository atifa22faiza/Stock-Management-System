import java.awt.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
class bill extends JFrame implements ActionListener
{
	JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l;
	JTextField t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t;
	JButton b1,b2,b3;
	JComboBox j1,j2,j3,j4,j5,j6;
	int a=30,te;
	//private JFrame frame;
	//Connection con;
	//Statement st,st1;
	//ResultSet rs,rs1;
	//PreparedStatement ps,ps1;

	bill()
	{
        System.out.println("hi");
		call();
		

}
public void call()
{
	System.out.println("check");
	//frame = new JFrame("Main menu");
	//setBounds(50,50,800,500);
		//setVisible(true);
		//createConn();

		setLayout(null);

		l1=new JLabel("BILL");
		l1.setBounds(350,40,100,a);
		add(l1);
		
		l2=new JLabel("Name");
		l2.setBounds(260,110,100,a);
		add(l2);
		
		t1=new JTextField();
		t1.setBounds(380,110,100,a);
		add(t1);
		l3=new JLabel("Address");
		l3.setBounds(500,110,100,a);
		add(l3);
		t2=new JTextField();
		t2.setBounds(620,110,100,a);
		add(t2);
		
		l4=new JLabel("Mobile No");
		l4.setBounds(260,160,100,a);
		add(l4);
		
		t3=new JTextField();
		t3.setBounds(380,160,100,a);
		add(t3);
		
		l5=new JLabel("Total amount");
		l5.setBounds(500,160,100,a);
		add(l5);
		
		l6=new JLabel("Mobile No");
		l6.setBounds(620,160,100,a);
		add(l6);
       
		createConn();
		
		
		
      
		
		b2=new JButton("pay");
		b2.setBounds(450,400,100,30);
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
		try
			{
				System.out.println("keyboard action action");
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				Connection con=DriverManager.getConnection("jdbc:odbc:stock1");
			   Statement st1=con.createStatement();
				System.out.println("Connection done");
				String q="insert into keyboard values('"+t1.getText()+"','"+t2.getText()+"','"+t3.getText()+"','"+l6.getText()+"')";
			//st.executeUpdate("insert into salse values('"+j6.getSelectedItem()+"','"+j1.getSelectedItem()+"','"+j2.getSelectedItem()+"','"+t1.getText()+"','"+t2.getText()+"')");
			    st1.executeUpdate(q);
				createConn();
                //t1.setText("");
				//t2.setText("");
                //t3.setText("");	
         //user p = new user();
	//super("purchase");
	   // b=3;
		//p.setBounds(50,50,800,500);
		//p.setVisible(true);
		//p.setLayout(null);				
				//summary=i+" Records Inserted";
				//JOptionPane.showMessageDialog(null,summary,"Moniter",JOptionPane.INFORMATION_MESSAGE);
			}
			catch(Exception e){System.out.println(e);}	
	}
	void createConn()
	{
		
		
		try
		{
			
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				Connection con=DriverManager.getConnection("jdbc:odbc:stock1");
			    Statement st=con.createStatement();
				System.out.println("Connection done");
				ResultSet rs=st.executeQuery("select * from temp");
				System.out.println("function1");
				int te1=0;
					while(rs.next())
                      {
						System.out.println("function2");
						te=Integer.parseInt(rs.getString(5));
						te1=te+te1;
						System.out.println("function3");
					  }
					
				
				l6.setText(Integer.toString(te1));
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	
	  
	}
}
	