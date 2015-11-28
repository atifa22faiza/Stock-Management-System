import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
public class Login extends JFrame implements ActionListener
{
	ButtonGroup bg;
	JRadioButton r1,r2,r3,r4;
	JLabel l1,l2,l3;
	JTextField id_txt;
	JPasswordField p1;
	JButton b1,b2;
ImageIcon icon = new ImageIcon("im3.jpg");
	public Login()
	{
		super();
		setTitle("Login");
		Container c;
		c=getContentPane();
		c.setLayout(null);
		JPanel panel=new JPanel()
		{
			protected void paintComponent(Graphics g)
			{
				// Scale image to size of component
				Dimension d = getSize();
				g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
				super.paintComponent(g);
			}
		};
		panel.setOpaque( false );
		panel.setSize(500,450 );
		c.add( panel );
		Color col=new Color(224,255,255);
		Font f=new Font("Times New Roman",Font.ITALIC+Font.BOLD,20);
		c.setBackground(col);
		c.setFont(f);
		Font f1=new Font("Times New Roman",Font.ITALIC+Font.BOLD,25);
		l2=new JLabel("Customer Relationship Monitoring");
		l2.setBounds(50,50,700,40);
		c.add(l2);
		l2.setFont(f1);
		l2.setForeground(Color.black);
		bg=new ButtonGroup();
		r1=new JRadioButton("Admin");
		r1.setBackground(col);
		r1.setFont(f);
		r1.setBounds(100,120,120,30);
		bg.add(r1);
		c.add(r1);
		
		r4=new JRadioButton("Customer ");
		r4.setBackground(col);
		r4.setFont(f);
		r4.setSelected(true);
		r4.setBounds(250,120,120,30);
		bg.add(r4);
		c.add(r4);
		
		l1=new JLabel("Password");
		l1.setFocusable(true);
		l1.setBounds(80,230,120,30);
		l1.setFont(f);
		c.add(l1);
		
		l3=new JLabel("ID ");
		l3.setBounds(80,180,120,30);
		l3.setFont(f);
		c.add(l3);
		
		id_txt=new JTextField();
		id_txt.setBounds(200,180,120,30);
		c.add(id_txt);
		
		p1=new JPasswordField();
		p1.setBounds(200,230,120,30);
		c.add(p1);
		b1=new JButton("Sign In");
		b1.setBounds(330,230,120,30);
		b1.setFont(f);
		b1.setBackground(col);
		b1.addActionListener(this);
		c.add(b1);
		
			b2=new JButton("Sign Up");
		b2.setBounds(330,260,120,30);
		b2.setFont(f);
		b2.setBackground(col);
		b2.addActionListener(this);
		c.add(b2);
			
		 Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (int) ((dimension.getWidth() -500) / 2);
    int y = (int) ((dimension.getHeight() - 350) / 2);
   setLocation(x, y);

		setSize(500,350);
		setDefaultCloseOperation(2);
	
	}

	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource().equals(b1)){
			if(r4.isSelected()){
				System.out.println ("Here");
				String pass= new String(p1.getPassword());
				if(id_txt.getText().length()>0 &&pass.length()>0){
							try{
						Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
						Connection con=DriverManager.getConnection("jdbc:odbc:stock1");
					    Statement st=con.createStatement();
					    ResultSet rs;
					    System.out.println ("select * from user_details where uid="+id_txt.getText().trim()+" and password='"+pass+"'");
					     rs=st.executeQuery("select * from user_details where uid="+id_txt.getText().trim()+" and password='"+pass+"'");
					     rs.next();
					      if(rs.getString(2).length()>0)
        					{
        						
	   										MainMenu obj=new MainMenu("customer",Integer.parseInt(id_txt.getText()));
	   										obj=null;	
	   											dispose();
        					}else{
        						JOptionPane.showMessageDialog(this,"Username Or Password Invalid...!","Login",JOptionPane.WARNING_MESSAGE);
        					}
					}catch(Exception e){

						if(e.getMessage().equals("[Microsoft][ODBC Driver Manager] Invalid cursor state"))
          				{
              			  JOptionPane.showMessageDialog(this,"Username Or Password Worng","Login",JOptionPane.ERROR_MESSAGE);
         				 }
					}
				}else{
					JOptionPane.showMessageDialog(this,"Please enter username and password!","Login",JOptionPane.ERROR_MESSAGE);
				}
			
			}else if(r1.isSelected()){
					
				String pass= new String(p1.getPassword());
					System.out.println ("Here111"+id_txt.getText()+"pass "+pass);
				if(id_txt.getText().length()>0 &&pass.length()>0){
					//System.out.println (+" - "+pass+"-- "+id_txt.getText()=="admin"&& pass=="123");
					if(id_txt.getText().equals("admin")&& pass.equals("123")){
						MainMenu ob=new MainMenu("admin",1);
						dispose();
					}
					else{
						JOptionPane.showMessageDialog(this,"Username Or Password Worng","Login",JOptionPane.ERROR_MESSAGE);
					
					}
				}
				else{
					JOptionPane.showMessageDialog(this,"Please enter username and password!","Login",JOptionPane.ERROR_MESSAGE);
				}
				
			}
		}else if(ae.getSource().equals(b2)){
			user_signup bj=new user_signup();
			dispose();
		}
	}
}