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
public class HMC {

    /**
     * @param args the command line arguments
     */
    JFrame f1;
    JButton hm,std,emp;
    JLabel labe;
    HMC(){
        f1=new JFrame("HMC");
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f1.setBackground(Color.cyan);
        labe = new JLabel("HALL MANAGEMENT CENTRE",JLabel.CENTER);
	Font myFont=new Font("Serif", Font.PLAIN, 60);
        labe.setFont(myFont);
	labe.setForeground(Color.DARK_GRAY);
        labe.setBackground(Color.blue);
	labe.setBounds(01,01,1360,142);
        labe.setOpaque(true);
	f1.add(labe);
        
        
        //Image
        JLabel background = new JLabel(new ImageIcon(".\\home\\akhilesh\\Documents\\HMC\\src\\hmc\\hall.jpg"));
        background.setBounds(-90,155,1200,750);
        background.setBackground(Color.white);
        background.setLayout(new FlowLayout());
        f1.add(background);
        
        
        
        //Buttons
        hm=new JButton("Home");
	hm.setBounds(1100,300,130,35);
        hm.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
               // LogIn i=new LogIn();
             }
        });
	hm.setBackground(Color.LIGHT_GRAY);
	Font my11=new Font("Serif", Font.BOLD, 20);
	hm.setFont(my11);
       
        
        std=new JButton("Student");
	std.setBounds(1100,400,130,35);
	        std.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //f1.setVisible(false);
                //System.out.println(new Logged().getId());
                //f1.dispose();
                String uname = new Logged().getId();
                if(uname.equals("NA"))
                {
                    f1.dispose();
                    new Login();
                }
                else if(uname.equals("messadmin"))
                {
                    JOptionPane.showMessageDialog(null, "MessManager is already logined");
                }
                else if(uname.equals("wardenadmin"))
                {
                    JOptionPane.showMessageDialog(null, "Warden is already logined");
                }
                else
                {
                    f1.dispose();
                    new Student_Page();
                }
             }
        });
	std.setBackground(Color.LIGHT_GRAY);
	Font my111=new Font("Serif", Font.BOLD, 20);
	std.setFont(my111);
        
        emp=new JButton("Employee");
	emp.setBounds(1100,500,130,35);
	       emp.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //f1.dispose();
                String uname = new Logged().getId();
                if(uname.equals("NA")){
                    f1.dispose();
                    new EmpLogin();
                }
                else if(uname.equals("wardenadmin"))
                {
                    f1.dispose();
                    new Warden();
                }
                else if(uname.equals("messadmin"))
                {
                    //f1.dispose();
                    //new ();
                    System.out.println("yes");
                }
                else
                {
                    //System.out.println("Employee");
                    JOptionPane.showMessageDialog(null, "Student is already logined");
                }
             }
        });
	emp.setBackground(Color.LIGHT_GRAY);
	Font my1111=new Font("Serif", Font.BOLD, 20);
	emp.setFont(my1111);
        
        
        f1.add(hm);
        f1.add(std);
        f1.add(emp);
        
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
       new HMC();   
    }
}
