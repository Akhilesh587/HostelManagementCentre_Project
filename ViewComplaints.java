/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hmc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.sql.*;
import javax.swing.JPanel;
/**
 *
 * @author RGUKT
 */
public class ViewComplaints implements ActionListener{
    private JLabel label; 
    private JPanel contentPane;
    private JButton homebtn,backbtn;
    private JFrame f1;
    private JTable table;
    private int roomno;
    private String[][] StudentData;
    private String uname,sql; 
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    //private String[] columnName;
    ViewComplaints()  
    {  
        f1=new JFrame("HMC/Warden/ViewComplaints");
        //f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane = new JPanel();
        contentPane.setLayout(null);
        f1.setContentPane(contentPane);
        
        roomno = getRoomNo();
        uname = new Logged().getId();
        String status = "pending";
        //System.out.println(uname);
        String[] columnNames = { "Name", "Room Number", "Complaint", "Status" };
        int k=0;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root","");
            sql="select * from grieveance where status = '"+status+"' and uname='"+uname+"'";
            ps = con.prepareStatement(sql);  
            ResultSet rs1 = ps.executeQuery(); 
            
            while(rs1.next())
            {
                System.out.println("yes1");
                StudentData[k][0] = uname;  // username
                StudentData[k][1] = Integer.toString(roomno);   // room no
                StudentData[k][2] = rs1.getString(3);    // Complaints
                StudentData[k][3] = rs1.getString(4);    // status
                k++;
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
        
        table = new JTable(StudentData, columnNames);
        table.setDefaultEditor(Object.class, null);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        
        
        f1.getContentPane().setLayout(new BorderLayout());
        f1.getContentPane().add(scrollPane,BorderLayout.CENTER);
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f1.setBounds(400, 200, 700, 250);
        System.out.println("yes");
        //f1.getContentPane().setVisible(true);
        f1.setVisible(true);
        System.out.println("yes");
        
        /*table.setBounds(30, 40, 200, 300); 

		// adding it to JScrollPane 
		JScrollPane sp = new JScrollPane(table); 
                table.setFillsViewportHeight(true);
		f1.add(sp); 
		// Frame Size 
		f1.setSize(500, 200); 
		// Frame Visible = true 
		f1.setVisible(false);*/
        
	f1.addWindowListener(new WindowAdapter(){
        @Override
	public void windowClosing(WindowEvent we){
		f1.dispose();}
        });
    }
    
    private int getRoomNo()
    {
        try
        {
            String uname = new Logged().getId();
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root","");
            String sql="select * from roominfo where usrname='"+uname+"'";
            PreparedStatement ps = con.prepareStatement(sql);  
            ResultSet rs = ps.executeQuery(); 
            while(rs.next())
                return rs.getInt(1);
        }catch(Exception ex)
        {
            System.out.println(ex);
        }
        return 0;
    }
    
    public void actionPerformed(ActionEvent e)   
    {  
    
        if(e.getSource()== homebtn)
        {
            f1.dispose();
            new HMC();
        }
        else if(e.getSource()==backbtn)
        {
            f1.dispose();
            new Grieveance();
        }
        
    }
    
    public static void main(String[] args)
    {
        new ViewComplaints();
    }
}
