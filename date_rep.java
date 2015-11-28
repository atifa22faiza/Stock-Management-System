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
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import java.util.*;
import org.jfree.chart.ChartUtilities;
public class date_rep extends JInternalFrame implements ActionListener {
	String from,to;
	Connection con;
	Statement st;
	DefaultPieDataset dataset ;
	ResultSet rs;
		JButton save_img;
	JFileChooser jfc;JFreeChart chart ;
    public date_rep(String from,String to) {
    	this.from=from;
    	this.to=to;
    	setLayout(null);
    	jfc=new JFileChooser();
    	dataset = new DefaultPieDataset( ); 
    	chart = ChartFactory.createPieChart(      
         "Sales",  // chart title 
         dataset,        // data    
         true,           // include legend   
         true, 
         false);
		ChartPanel pane=new ChartPanel(chart);
		pane.setSize(600,600);
		JPanel chart_pane=new JPanel();
		chart_pane.setBounds(10,10,650,650);
		chart_pane.add(pane);
		add(chart_pane);
    	try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con=DriverManager.getConnection("jdbc:odbc:stock1");
			st=con.createStatement();
			Vector<String> brand=new Vector<String>();
			brand.add("Mouse");
			brand.add("Keyboard");
			brand.add("Moniter");
			for (int i = 0; i<brand.size(); i++){
				int co=0;
				System.out.println ("select qty from bill where brand='"+brand.get(i)+"' and bdate >=#"+from+"# and bdate <=#"+to+"#");
				rs=st.executeQuery("select qty from bill where brand='"+brand.get(i)+"' and bdate >=#"+from+"# and bdate <=#"+to+"#");
				while(rs.next()){
					co=co+rs.getInt(1);
				}
				System.out.println (" p "+brand.get(i)+" count "+co);
				dataset.setValue(brand.get(i),co);
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

    			 ChartUtilities.saveChartAsJPEG( fi , chart , 650 , 650 );
					}
					catch (Exception ex) {
						System.out.println ("Error "+ex);
					}
					    			
    		}
    	}
    }
  
}