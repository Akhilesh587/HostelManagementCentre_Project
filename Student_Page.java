/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hmc;

//import gui.Grieveance;
//import gui.Student_Pay;
//import gui.home;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author RGUKT
 */
public class Student_Page {
    private JFrame f1;
    private JButton mess,std,griv,edit,book,hm,logout;
    private JLabel label,name,room;
    Student_Page(){
        f1=new JFrame("HMC/Student");
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        name = new JLabel("Hello "+new Logged().getId());
        name.setFont(new Font("Serif",Font.PLAIN,25));
        name.setForeground(Color.green);
        name.setBounds(1100,30,500,30);
     
        /*room = new JLabel("Room No: "+new Logged().getRoomNo());
        room.setFont(new Font("Serif",Font.PLAIN,25));
        room.setForeground(Color.green);
        room.setBounds(1100,50,100,10);
        */
        label = new JLabel("Student Page",JLabel.CENTER);
	Font myFont=new Font("Serif", Font.PLAIN, 60);
        label.setFont(myFont);
	label.setForeground(Color.DARK_GRAY);
        label.setBackground(Color.blue);
	label.setBounds(01,01,1360,142);
        label.setOpaque(true);
        label.add(name);
        //label.add(room);
	f1.add(label);
        
        //Image
        JLabel background=new JLabel(new ImageIcon("C:\\Users\\iiitbasar\\Documents\\NetBeansProjects\\HMC\\src\\hmc\\hall.jpg"));
        background.setBounds(-90,155,1200,750);
        background.setBackground(Color.white);
        background.setLayout(new FlowLayout());
        f1.add(background);
        
        
        //Buttons
        hm=new JButton("Home");
        hm.setBounds(1,80,100,35);
	//hm.setBounds(1,80,10 0,35);
        hm.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
               f1.dispose();
               new HMC();
             }
        });
	hm.setBackground(Color.LIGHT_GRAY);
	Font my110=new Font("Serif", Font.BOLD, 20);
	hm.setFont(my110);
        label.add(hm);
        
        logout=new JButton("LogOut");
	logout.setBounds(1100,80,100,35);
        logout.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
               // LogIn i=new LogIn();
               f1.dispose();
               new Logged().remLogged();
               new HMC();
             }
        });
	logout.setBackground(Color.LIGHT_GRAY);
	Font my1101=new Font("Serif", Font.BOLD, 20);
	logout.setFont(my1101);
        label.add(logout);
        
        
        
        std=new JButton("Student_Pay");
	std.setBounds(1100,200,180,35);
        std.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
               // LogIn i=new LogIn();
                       //f1.dispose();
                       //new Student_Pay();
             }
        });
	std.setBackground(Color.LIGHT_GRAY);
	Font my11=new Font("Serif", Font.BOLD, 20);
	std.setFont(my11);
        
        mess=new JButton("Mess_Pay");
	mess.setBounds(1100,300,180,35);
	        mess.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
                f1.dispose();
                new MessPay();
             }
        });
	mess.setBackground(Color.LIGHT_GRAY);
	Font my111=new Font("Serif", Font.BOLD, 20);
	mess.setFont(my111);
        
        griv=new JButton("Griveance");
	griv.setBounds(1100,400,180,35);
	       griv.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
                f1.dispose();
                new Grieveance();
             }
        });
	griv.setBackground(Color.LIGHT_GRAY);
	Font my1111=new Font("Serif", Font.BOLD, 20);
	griv.setFont(my1111);
        
        book=new JButton("Booking");
	book.setBounds(1100,500,180,35);
	       book.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
                f1.dispose();
                new Booking();
             }
        });
	book.setBackground(Color.LIGHT_GRAY);
	//Font my1111=new Font("Serif", Font.BOLD, 20);
	book.setFont(my1111);
        
        edit=new JButton("Edit Profile");
	edit.setBounds(1100,600,180,35);
	       edit.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
                f1.dispose();
                new EditProfile();
             }
        });
	edit.setBackground(Color.LIGHT_GRAY);
	//Font my1111=new Font("Serif", Font.BOLD, 20);
	edit.setFont(my1111);
        
        
        f1.add(mess);
        f1.add(std);
        f1.add(edit);
        f1.add(book);
        f1.add(griv);
        
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
   public static void main(String[] args) {
 
       new Student_Page();
        
    }
    
}
