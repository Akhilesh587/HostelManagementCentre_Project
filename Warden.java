/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hmc;

import javax.swing.*;  
import java.awt.*;  
import java.awt.event.*;  
import java.sql.*;  

/**
 *
 * @author RGUKT
 */
public class Warden extends JFrame{
    private JFrame f1;
    private JButton hm,std,ta,logoutbtn;
    private JLabel label;
    Warden(){
        f1=new JFrame("Warden");
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f1.setBackground(Color.cyan);
        label = new JLabel("WARDEN",JLabel.CENTER);
	Font myFont=new Font("Serif", Font.PLAIN, 60);
        label.setFont(myFont);
	label.setForeground(Color.DARK_GRAY);
        label.setBackground(Color.blue);
	label.setBounds(01,01,1360,142);
        label.setOpaque(true);
	f1.add(label);
        
        //Image
        JLabel background=new JLabel(new ImageIcon("C:\\Users\\iiitbasar\\Documents\\NetBeansProjects\\HMC\\src\\hmc\\hall.jpg"));
        background.setBounds(-90,155,1200,750);
        background.setBackground(Color.white);
        background.setLayout(new FlowLayout());
        f1.add(background);
        
        
        
        //Buttons
        hm=new JButton("Home");
	hm.setBounds(1100,300,200,35);
        hm.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
               f1.dispose();
               new HMC();
             }
        });
	hm.setBackground(Color.LIGHT_GRAY);
	Font my11=new Font("Serif", Font.BOLD, 20);
	hm.setFont(my11);
       
        
        std=new JButton("ViewStudents");
	std.setBounds(1100,400,200,35);
	        std.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //f1.setVisible(false);
                //f1.dispose();
                new ViewStudents();
             }
        });
	std.setBackground(Color.LIGHT_GRAY);
	Font my111=new Font("Serif", Font.BOLD, 20);
	std.setFont(my111);
        
        ta=new JButton("TakeAction");
	ta.setBounds(1100,500,200,35);
	       ta.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
                f1.dispose();
                new TakeAction();
             }
        });
	ta.setBackground(Color.LIGHT_GRAY);
	Font my1111=new Font("Serif", Font.BOLD, 20);
	ta.setFont(my1111);
        
        
        logoutbtn=new JButton("Logout");
	logoutbtn.setBounds(1100,600,200,35);
	       logoutbtn.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
                f1.dispose();
                new Logged().remLogged();
                new HMC();
             }
        });
	logoutbtn.setBackground(Color.LIGHT_GRAY);
	logoutbtn.setFont(my1111);
        
        f1.add(hm);
        f1.add(std);
        f1.add(ta);
        f1.add(logoutbtn);
        
        f1.setSize(1380,750);
	f1.setBackground(Color.blue);
	f1.setLayout(null);
        f1.setVisible(true);
	
    }
    
    public static void main(String[] args) {
       new Warden();   
    }
}
