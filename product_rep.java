/**
 * @(#)product_rep.java
 *
 *
 * @author 
 * @version 1.00 2015/11/1
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
public class product_rep extends JInternalFrame implements ActionListener{
	String tab;
	Connection con;
	Statement st;
	DefaultCategoryDataset dataset ;
	ResultSet rs;
	JButton save_img;
	JFileChooser jfc;JFreeChart barChart ;
    public product_rep(String tab) {
    	this.tab=tab;
    	setLayout(null);
    	dataset = new DefaultCategoryDataset( ); 
    	  barChart = ChartFactory.createBarChart3D(
         "No of items sold","Supplier", "Score",       
         dataset,            
         PlotOrientation.VERTICAL,             
         true, true, false);
         
         jfc=new JFileChooser();
         
		ChartPanel pane=new ChartPanel(barChart);
		pane.setSize(600,600);
		JPanel chart_pane=new JPanel();
		chart_pane.setBounds(10,10,650,650);
		chart_pane.add(pane);
		add(chart_pane);
    	try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con=DriverManager.getConnection("jdbc:odbc:stock1");
			st=con.createStatement();
			Vector<String> sup=new Vector<String>();
			rs=st.executeQuery("select suppler_name from b_name");
			while(rs.next()){
				sup.add(rs.getString(1));
			}
			for (int i = 0; i<sup.size(); i++){
				int co=0;
				System.out.println ("select qty from bill where brand='"+tab+"' and s_name='"+sup.get(i)+"'");
				rs=st.executeQuery("select qty from bill where brand='"+tab+"' and s_name='"+sup.get(i)+"'");
				while(rs.next()){
					co=co+rs.getInt(1);
					System.out.println ("Value "+co+" - "+sup.get(i));
				}
				dataset.addValue(co,"Supplier name",sup.get(i));
			}
			
		}
		catch (Exception ex) {
			System.out.println (ex);
		}
		
		save_img=new JButton("Save as image");
		save_img.setBounds(700,30,120,30);
		save_img.addActionListener(this);
		add(save_img);
		
		setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setSize(1020,650);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
    	if(ae.getSource().equals(save_img)){
    		int ret_val=jfc.showSaveDialog(this);
    		if(ret_val==JFileChooser.APPROVE_OPTION){
    			try {
    			File fi=jfc.getSelectedFile();
    			String filePath = fi.getAbsolutePath();
				if(!filePath.endsWith(".jpg")) {
				    fi = new File(filePath + ".jpg");
				}

    			 ChartUtilities.saveChartAsJPEG( fi , barChart , 650 , 650 );
					}
					catch (Exception ex) {
						System.out.println ("Error "+ex);
					}
					    			
    		}
    	}
    }
}