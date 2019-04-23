/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hmc;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Akhilesh12
 */
public class EmpLogin {
    
    private JFrame f1;
    private JTextField usrtext;
    private JPasswordField passfield;
    private JLabel label,usrlabel,passlabel;
    private JButton hm,loginButton,clearButton;
    private String uname,password;
    EmpLogin()
    {
        f1=new JFrame("HMC/LOGIN");
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f1.setBackground(Color.cyan);
        label = new JLabel("EMPLOYEE LOGIN",JLabel.CENTER);
	Font myFont=new Font("Serif", Font.PLAIN, 60);
        label.setFont(myFont);
	label.setForeground(Color.DARK_GRAY);
        label.setBackground(Color.blue);
	label.setBounds(01,01,1360,142);
        label.setOpaque(true);
	f1.add(label);
        
        //username label and text field
        Font f = new Font("Serif",Font.PLAIN,30);
        usrlabel = new JLabel("UserName");
        usrlabel.setFont(f);
        usrlabel.setBounds(400, 200, 200, 50);
        f1.add(usrlabel);
        
        usrtext = new JTextField();
        usrtext.setFont(f);
        usrtext.setBounds(550, 200, 280, 50);
        f1.add(usrtext);
        
        // password label and text field
        
        passlabel = new JLabel("Password");
        passlabel.setFont(f);
        passlabel.setBounds(400, 300, 200, 50);
        f1.add(passlabel);
        
        passfield = new JPasswordField();
        passfield.setFont(f);
        passfield.setBounds(550, 300, 280, 50);
        f1.add(passfield);
        
        //home button
        
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
        f1.add(hm);
        
        //Login Button
        
        loginButton = new JButton("Login");
        loginButton.setBounds(550, 400, 130, 35);
        loginButton.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(ValidateData())
                {
                    if(sqlValidate())
                    {
                        System.out.println(uname);
                        //JOptionPane.showMessageDialog(null, "Successfully Login\nRedirect to Student Page");
                        f1.dispose();
                        Logged l1 = new Logged();
                        
                        l1.setLogged(uname);
                        if(uname.equals("wardenadmin")){
                            new Warden();
                        }
                        else if(uname.equals("messadmin"))
                        {
                            System.out.println("yes");
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Invalid Credentials");
                    }
                }
                
             }
        });
	loginButton.setBackground(Color.LIGHT_GRAY);
	loginButton.setFont(buttonFont);
        f1.add(loginButton);
        
        // Clear Button
        
        clearButton = new JButton("Register");
        clearButton.setBounds(700, 400, 130, 35);
        clearButton.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
                f1.dispose();
                JOptionPane.showMessageDialog(null, "Please contact System Administator");
             }
        });
	clearButton.setBackground(Color.LIGHT_GRAY);
	clearButton.setFont(buttonFont);
        f1.add(clearButton);

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
    
    
    boolean ValidateData()
    {
        String string_regex = "^[a-zA-Z]+$";
        String uname = usrtext.getText();
        String password = new String(passfield.getPassword());
        
            if(uname.equals(""))
            {
                JOptionPane.showMessageDialog(null,"Name field should not be Empty");
                return false;
            }
            if(!uname.matches(string_regex))
            {
                JOptionPane.showMessageDialog(null,"It should be String","Invalid Name",JOptionPane.WARNING_MESSAGE);
                //tf1.requestFocusInWindow();
                return false;
            }
            
            if(password.equals(""))
            {
                JOptionPane.showMessageDialog(null,"Password field should not be Empty");
                return false;
            }
        return true;
    }
    
    boolean sqlValidate()
    {
        uname = usrtext.getText();
        password = new String(passfield.getPassword());
        try  
            {  
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root","");
                        String sql="select * from empreg where username='"+uname+"' and password='"+password+"'";
                        PreparedStatement ps = con.prepareStatement(sql);  
                        ResultSet rs = ps.executeQuery();  
                        if (rs.next())   
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
    
    
    public static void main(String [] args){
        new EmpLogin();
    }
    
}

