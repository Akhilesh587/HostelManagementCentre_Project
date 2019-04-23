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

import javax.swing.*;  
import java.awt.*;  
import java.awt.event.*;  
import java.sql.*; 

public class MessPay {
    private JFrame f1;
    private JButton hm,backbtn,paybtn;
    private JLabel label,paylabel;
    private JTextField payfield;
    private String uname,sql;
    private Connection con;
    private ResultSet rs;
    private PreparedStatement ps;
    MessPay()
    {
        f1=new JFrame("HMC/Student/Mess Pay");
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f1.setBackground(Color.cyan);
        label = new JLabel("MESS PAY",JLabel.CENTER);
	Font myFont=new Font("Serif", Font.PLAIN, 60);
        label.setFont(myFont);
	label.setForeground(Color.DARK_GRAY);
        label.setBackground(Color.blue);
	label.setBounds(01,01,1360,142);
        label.setOpaque(true);
	f1.add(label);
    
        Font f = new Font("Serif",Font.PLAIN,30);
        paylabel = new JLabel("Pay:");
        paylabel.setFont(f);
        paylabel.setBounds(400, 250, 200, 50);
        f1.add(paylabel);
        
        double amt = calculatePay();
        
        payfield = new JTextField();
        payfield.setFont(f);
        payfield.setText(Double.toString(amt));
        payfield.setBounds(500, 250, 280, 50);
        f1.add(payfield);
        
        
        hm=new JButton("Home");
	hm.setBounds(10,50,130,35);
        hm.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
                f1.dispose();
                new HMC();
             }
        });
	hm.setBackground(Color.LIGHT_GRAY);
	Font buttonFont=new Font("Serif", Font.BOLD, 20);
	hm.setFont(buttonFont);
        label.add(hm);
        
        //back button
       
        backbtn = new JButton("Back");
        backbtn.setBounds(1220, 50, 130, 35);
        backbtn.setFont(buttonFont);
        backbtn.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
                f1.dispose();
                new Student_Page();
             }
        });
        backbtn.setBackground(Color.LIGHT_GRAY);
        backbtn.setVisible(true);
        label.add(backbtn);
        
        // pay btn
        
        paybtn = new JButton("Pay");
        paybtn.setBounds(500, 320, 130, 35);
        paybtn.setFont(buttonFont);
        
            paybtn.addActionListener( new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    if(amt>0.00)
                    {
                        if(updateMessPay())
                        {
                            JOptionPane.showMessageDialog(null, "Payed Successfully");
                            f1.dispose();
                            new Student_Page();
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "U can't pay");
                        //paybtn.setVisible(false);
                    }
                }
            });
        paybtn.setBackground(Color.LIGHT_GRAY);
        paybtn.setVisible(true);
        f1.add(paybtn);
        
        
        
        f1.setSize(1380,750);
	f1.setBackground(Color.blue);
	f1.setLayout(null);
        f1.setVisible(true);
	f1.addWindowListener(new WindowAdapter(){
        @Override
	public void windowClosing(WindowEvent we){
		f1.dispose();}
        });
    }
    
    boolean updateMessPay()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root","");
            sql = "update pay set messpay=0.00,messpaystatus=1 WHERE username = '"+uname+"'";
            ps = con.prepareStatement(sql);   
            int r = ps.executeUpdate();
            if(r>0)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
        return false;
    }
    
    double calculatePay()
    {
        uname = new Logged().getId();
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root","");
            sql = "select messpay from pay WHERE username = '"+uname+"'";
            ps = con.prepareStatement(sql);   
            rs = ps.executeQuery();
            while(rs.next())
            {
                return rs.getDouble(1);
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
        return 0.00;
    }
    
    public static void main(String[] args)
    {
        new MessPay();
    }
}
