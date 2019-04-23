/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hmc;

/**
 *
 * @author RGUKT
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.*;    
import java.awt.event.*;  
import java.sql.*; 

public class ViewStudents{
    private JPanel contentPane;
    private JFrame f1;
    private JLabel label;
    private JButton homebtn,backbtn;
    private int size;
    private ResultSet rs;
    private String[][] StudentData;
    private JTable table;
    
    ViewStudents()  
    {  
        
        f1=new JFrame("HMC/Warden/ViewStudents");
        //f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane = new JPanel();
        contentPane.setLayout(null);
        f1.setContentPane(contentPane);
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root","");
            String sql="select * from reg";
            PreparedStatement ps = con.prepareStatement(sql);  
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs=ps.executeQuery();
            if(rs.last())
            {
                size = rs.getRow();
                rs.beforeFirst();
            }
            //System.out.println(size);
        }catch(Exception ex)
        {
            System.out.println(ex);
        }
        
        StudentData = new String[size][5];
        int k=0;
        try{
            rs.beforeFirst();
            while(rs.next())
            {
                    StudentData[k][0] = rs.getString(1);    //usernames
                    StudentData[k][1] = Integer.toString(getRoomNo(rs.getString(1)));   // room no
                    StudentData[k][2] = rs.getString(3);    // emails
                    StudentData[k][3] = rs.getString(4);    //address
                    StudentData[k][4] = Long.toString(rs.getLong(5));      //phoneno
                    k++;
            }
        }catch(Exception ex)
        {
            System.out.println(ex);
        }
        
        String[] columnNames = {"Name","Room No","Email","Address","PhoneNo"};
        
        table = new JTable(StudentData, columnNames);
        table.setDefaultEditor(Object.class, null);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        
        
        f1.getContentPane().setLayout(new BorderLayout());
        f1.getContentPane().add(scrollPane,BorderLayout.CENTER);
        //f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f1.setBounds(400, 200, 700, 250);
        f1.setVisible(true);
           
    }
    
    private int getRoomNo(String uname)
    {
        try
        {
            //String uname = new Logged().getId();
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
    
    public static void main(String[] args)
    {
        new ViewStudents();
    }
}
