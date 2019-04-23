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
import javax.swing.JTextField;

/**
 *
 * @author RGUKT
 */
public class Admin {
    JFrame f1;
    JLabel labe;
    JTextField usrtextremove;
    Admin(){
    
        f1=new JFrame("HMC");
       // f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f1.setBackground(Color.cyan);
        labe = new JLabel("ADMIN",JLabel.CENTER);
	Font myFont=new Font("Serif", Font.PLAIN, 30);
        labe.setFont(myFont);
	labe.setForeground(Color.DARK_GRAY);
        labe.setBackground(Color.blue);
	labe.setBounds(01,01,1360,100);
        labe.setOpaque(true);
	f1.add(labe);
        
        
        JButton addbtn=new JButton("Add User");
	addbtn.setBounds(1050,190,130,35);
        addbtn.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Register r=new Register();   
                // LogIn i=new LogIn();
             }
        });
	addbtn.setBackground(Color.LIGHT_GRAY);
	Font my11=new Font("Serif", Font.BOLD, 20);
	addbtn.setFont(my11);
        
        
        JButton rmbtn=new JButton("Remove User");
	rmbtn.setBounds(1050,290,160,35);
        rmbtn.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
                
                        JFrame f2=new JFrame("remove");
                        //f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        f2.setBackground(Color.cyan);
                        JLabel labe1 = new JLabel("Remove user",JLabel.CENTER);
                        Font myFont=new Font("Serif", Font.PLAIN, 30);
                        labe1.setFont(myFont);
                        labe1.setForeground(Color.DARK_GRAY);
                        labe1.setBackground(Color.blue);
                        labe1.setBounds(0,0,1360,100);
                        labe1.setOpaque(true);
                        f2.add(labe1);
                        
                        Font f = new Font("Serif",Font.PLAIN,25);
                       JLabel usrlabel = new JLabel("UserName");
                        usrlabel.setFont(f);
                        usrlabel.setBounds(500,120, 200, 50);
                        f2.add(usrlabel);

                        usrtextremove = new JTextField();
                        usrtextremove.setFont(f);
                        usrtextremove.setBounds(630, 130, 220, 40);
                        f2.add(usrtextremove);
                        
                             JButton   loginButton = new JButton("Remove");
                            loginButton.setBounds(580, 210, 130, 35);
                            loginButton.addActionListener( new ActionListener(){
                                public void actionPerformed(ActionEvent e){
                                        if(sqlValidateRemove())
                                        {
                                            if(remove()){
                                                JOptionPane.showMessageDialog(null, "User Deleted");
                                                f2.dispose();
                                            }
                                        }
                                        else
                                        {
                                            JOptionPane.showMessageDialog(null, "Invalid UserName");
                                        }
                                    

                                 }
                            });
                            loginButton.setBackground(Color.LIGHT_GRAY);
                            Font buttonFont=new Font("Serif", Font.BOLD, 20);
                            loginButton.setFont(buttonFont);
                            f2.add(loginButton);
                        
                        
                        
                        
                        f2.setBounds(0,0,1380,750);
                        f2.setBackground(Color.blue);
                        f2.setLayout(null);
                        f2.setVisible(true);
                        f2.addWindowListener(new WindowAdapter(){
                        @Override
                        public void windowClosing(WindowEvent we){
                                f2.dispose();}
                        });

                
                
             }
        });
	rmbtn.setBackground(Color.LIGHT_GRAY);
	Font my111=new Font("Serif", Font.BOLD, 20);
	rmbtn.setFont(my111);
        
        f1.add(rmbtn);
        f1.add(addbtn);
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
    
    
    boolean sqlValidateRemove()
    {
       String uname = usrtextremove.getText();
        
        try  
            {  
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root","root");
                        String sql="select * from reg where username='"+uname+"'";
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
    
        boolean remove()
    {
        String uname = usrtextremove.getText();
        try  
            {  
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root","root");
                        String sql3 = "delete from readingroombooking where username='"+uname+"'";
                        String sql2 = "delete from grieveance where uname='"+uname+"'";
                        String sql1 = "delete from roominfo where usrname='"+uname+"'";
                        String sql="delete from reg where username='"+uname+"'";
                        
                        String sql4="select * FROM grieveance WHERE uname='"+uname+"'";
                        PreparedStatement ps4 = con.prepareStatement(sql4);
                        ResultSet rs4 = ps4.executeQuery();
                        while(rs4.next()){
                            String sql5 = "delete from grieveance where uname='"+uname+"'";
                            PreparedStatement ps = con.prepareStatement(sql5);
                            int rs = ps.executeUpdate();
                            
                        }
                        
                        
                        PreparedStatement ps1 = con.prepareStatement(sql1);
                        int rs1 = ps1.executeUpdate();
                        PreparedStatement ps2 = con.prepareStatement(sql);
                        int rs2 = ps2.executeUpdate();
                        if ((rs2>0 && rs1>0))   
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
    
    public static void main(String[] args) {
       new Admin();   
    }
    
}
