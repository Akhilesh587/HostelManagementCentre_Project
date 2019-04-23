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

public class Booking {
    private JFrame f1;
    private JButton hm,readroombtn,tvroombtn,playroombtn,backbtn;
    private JLabel label;
    Booking(){
        f1=new JFrame("Booking");
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f1.setBackground(Color.cyan);
        label = new JLabel("Booking",JLabel.CENTER);
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
	hm.setBounds(1100,200,200,35);
        hm.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
               f1.dispose();
               new HMC();
             }
        });
	hm.setBackground(Color.LIGHT_GRAY);
	Font my11=new Font("Serif", Font.BOLD, 20);
	hm.setFont(my11);
       
        
        playroombtn=new JButton("Playing Room");
	playroombtn.setBounds(1100,300,200,35);
	playroombtn.addActionListener( new ActionListener(){
        public void actionPerformed(ActionEvent e){
                //f1.setVisible(false);
                f1.dispose();
                new PlayingRoomBooking();
             }
        });
	playroombtn.setBackground(Color.LIGHT_GRAY);
	Font my111=new Font("Serif", Font.BOLD, 20);
	playroombtn.setFont(my111);
        
        tvroombtn=new JButton("TV Room");
	tvroombtn.setBounds(1100,400,200,35);
	tvroombtn.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
                f1.dispose();
                new TVRoomBooking();
             }
        });
	tvroombtn.setBackground(Color.LIGHT_GRAY);
	Font my1111=new Font("Serif", Font.BOLD, 20);
	tvroombtn.setFont(my1111);
        
        readroombtn=new JButton("Reading Room");
	readroombtn.setBounds(1100,500,200,35);
	readroombtn.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
                f1.dispose();
                new ReadingRoomBooking();
             }
        });
	readroombtn.setBackground(Color.LIGHT_GRAY);
	readroombtn.setFont(my1111);
        
        
        backbtn=new JButton("Back");
	backbtn.setBounds(1100,600,200,35);
	backbtn.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
                f1.dispose();
                new Student_Page();
             }
        });
	backbtn.setBackground(Color.LIGHT_GRAY);
	backbtn.setFont(my1111);
        
        f1.add(hm);
        f1.add(playroombtn);
        f1.add(tvroombtn);
        f1.add(readroombtn);
        f1.add(backbtn);
        
        f1.setSize(1380,750);
	f1.setBackground(Color.blue);
	f1.setLayout(null);
        f1.setVisible(true);
	
    }
    
    public static void main(String[] args) {
       new Booking();   
    }
}
