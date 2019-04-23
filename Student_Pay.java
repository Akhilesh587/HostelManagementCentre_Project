/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hmc;

import hmc.Student_Page;
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
public class Student_Pay {

    JFrame f1;
    JButton mess,std,griv,edit,book,hm,logout,cancel,submit;
    JLabel labe,user,month,paid,due,total,amt;
    JLabel [][] l=new JLabel[2][6];
    Student_Pay(){
        f1=new JFrame("HMC/Student/Student_Pay");
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        labe = new JLabel("Student Paymants",JLabel.CENTER);
	Font myFont=new Font("Serif", Font.PLAIN, 60);
        labe.setFont(myFont);
	labe.setForeground(Color.DARK_GRAY);
        labe.setBackground(Color.blue);
	labe.setBounds(01,01,1360,132);
        labe.setOpaque(true);
	f1.add(labe);
       
        /*
        //Image
        JLabel background=new JLabel(new ImageIcon("C:\\Users\\Akhilesh12\\Documents\\NetBeansProjects\\HMc\\src\\hmc\\hall.jpg"));
        background.setBounds(-90,155,1200,750);
        background.setBackground(Color.white);
        background.setLayout(new FlowLayout());
        f1.add(background);
        */
        
        user=new JLabel("UserID");
	user.setBounds(50,200,100,35);
        
	user.setBackground(Color.LIGHT_GRAY);
	Font my11011=new Font("Serif", Font.BOLD, 20);
	user.setFont(my11011);
        user.setForeground(Color.BLUE);
        f1.add(user);
        
        amt=new JLabel("Amount / Month");
	amt.setBounds(170,200,150,35);
        
	amt.setBackground(Color.LIGHT_GRAY);
	//Font my11011=new Font("Serif", Font.BOLD, 20);
	amt.setFont(my11011);
        amt.setForeground(Color.BLUE);
        f1.add(amt);
        
        month=new JLabel("Total Months");
	month.setBounds(330,200,150,35);
        
	month.setBackground(Color.LIGHT_GRAY);
	//Font my11011=new Font("Serif", Font.BOLD, 20);
	month.setFont(my11011);
        month.setForeground(Color.BLUE);
        f1.add(month);
        
        paid=new JLabel("Paid Amount");
	paid.setBounds(480,200,150,35);
        
	paid.setBackground(Color.GRAY);
	//Font my11011=new Font("Serif", Font.BOLD, 20);
	paid.setFont(my11011);
        paid.setForeground(Color.BLUE);
        f1.add(paid);
        
        due=new JLabel("Due Amount");
	due.setBounds(600,200,150,35);
        
	due.setBackground(Color.LIGHT_GRAY);
	//Font my11011=new Font("Serif", Font.BOLD, 20);
	due.setFont(my11011);
        due.setForeground(Color.BLUE);
        f1.add(due);
        
        total=new JLabel("Total Amount");
	total.setBounds(730,200,150,35);
        
	total.setBackground(Color.LIGHT_GRAY);
	//Font my11011=new Font("Serif", Font.BOLD, 20);
	total.setFont(my11011);
        total.setForeground(Color.BLUE);
        f1.add(total);
        int h=220;
        //int a[]=new int [6];
         int a[]={50,180,340,490,610,740};
        for(int i=0;i<2;i++){
            int k=60;
            for(int j=0;j<6;j++){
                l[i][j]=new JLabel(String.valueOf(j));
                l[i][j].setBounds(a[j],h,180,35);
        
                l[i][j].setBackground(Color.LIGHT_GRAY);
                //Font my11011=new Font("Serif", Font.BOLD, 20);
                l[i][j].setFont(my11011);
                 //due.setForeground(Color.BLUE);
                 f1.add(l[i][j]);
                 k=k+100;
            }
            h+=20;
            
        }
        
        
        
        
        //Buttons
        hm=new JButton("Back");
	hm.setBounds(1,80,100,35);
        hm.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
               // LogIn i=new LogIn();
               f1.dispose();
               new Student_Page();
             }
        });
	hm.setBackground(Color.LIGHT_GRAY);
	Font my110=new Font("Serif", Font.BOLD, 20);
	hm.setFont(my110);
        f1.add(hm);
        
        logout=new JButton("LogOut");
	logout.setBounds(1250,80,100,35);
        logout.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
               // LogIn i=new LogIn();
               f1.dispose();
               new HMC();
              //f1.setCursor("Hand");
             }
        });
	logout.setBackground(Color.LIGHT_GRAY);
	Font my1101=new Font("Serif", Font.BOLD, 20);
	logout.setFont(my1101);
        f1.add(logout);
        
        
        
        submit=new JButton("Pay");
	submit.setBounds(960,300,150,35);
        submit.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
               // LogIn i=new LogIn();
             }
        });
	submit.setBackground(Color.LIGHT_GRAY);
	Font my11=new Font("Serif", Font.BOLD, 20);
	submit.setFont(my11);
        
        cancel=new JButton("Cancel");
	cancel.setBounds(1130,300,150,35);
	        cancel.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
               // new Student_Page();
             }
        });
	cancel.setBackground(Color.LIGHT_GRAY);
	Font my111=new Font("Serif", Font.BOLD, 20);
	cancel.setFont(my111);
        
        
        f1.add(cancel);
        f1.add(submit);
        
        
        f1.setSize(1380,750);
	f1.setBackground(Color.blue);
	f1.setLayout(null);
        f1.setVisible(true);
	f1.addWindowListener(new WindowAdapter(){
        @Override
	public void windowClosing(WindowEvent we){
		f1.dispose();}
        });
    
    }  /*     
    public static void main(String[] args) {
 
       new Mess_Bill();
        
    }*/
    
}
