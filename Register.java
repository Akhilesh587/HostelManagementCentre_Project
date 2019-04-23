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
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.swing.*;  
import java.awt.*;  
import java.awt.event.*;  
import java.sql.*;  
class Register extends JFrame implements ActionListener   
{   
    private JPanel contentPane;
    private JLabel label,l1, l2, l3, l4, l5,l6;  
    private JTextField tf1, tf2, tf3, tf4;  
    private JButton btn1,btn2,hm,backbtn;  
    private JPasswordField p1; 
    private JFrame f1;
    private String name;
    private int roomno;
    Register()  
    {  
        f1=new JFrame("HMC/LOGIN");
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f1.setBackground(Color.cyan);
        label = new JLabel("REGISTRATION FORM",JLabel.CENTER);
	Font myFont=new Font("Serif", Font.PLAIN, 60);
        label.setFont(myFont);
	label.setForeground(Color.DARK_GRAY);
        label.setBackground(Color.blue);
	label.setBounds(01,01,1360,142);
        label.setOpaque(true);
	f1.add(label);
         
        
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
        
        backbtn = new JButton("Back");
        backbtn.setBounds(1220, 50, 130, 35);
        backbtn.setFont(buttonFont);
        backbtn.setBackground(Color.LIGHT_GRAY);
        backbtn.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
                f1.dispose();
                new Login();
             }
        });
        label.add(backbtn);
        
        
        l2 = new JLabel("UserName:");  
        l3 = new JLabel("Password");
        l4 = new JLabel("Email-ID:");  
        l5 = new JLabel("Address:");    
        l6 = new JLabel("Phone No:");
        tf1 = new JTextField();
        p1 = new JPasswordField();
        p1.setEchoChar('*');
        tf2 = new JTextField();
        tf3 = new JTextField();
        tf4 = new JTextField();
        btn1 = new JButton("Submit"); 
        btn2 = new JButton("Clear");
        btn2.addActionListener(this);
        btn1.addActionListener(this);   
        l2.setBounds(400, 200, 200, 30);  
        l3.setBounds(400, 250, 200, 30);  
        l4.setBounds(400, 300, 200, 30);  
        l5.setBounds(400, 350, 200, 30);
        l6.setBounds(400, 400, 200, 30);
        tf1.setBounds(500, 200, 280, 30);
        p1.setBounds(500, 250, 280, 30);
        tf2.setBounds(500, 300, 280, 30);  
        tf3.setBounds(500, 350, 280, 30);
        tf4.setBounds(500, 400, 280, 30);  
        btn1.setBounds(500, 450, 150, 30);  
        btn1.setFont(buttonFont);
        btn1.setBackground(Color.LIGHT_GRAY);
        
        btn2.setBounds(650, 450, 100, 30);  
        
        f1.add(l2);
        f1.add(tf1);
        f1.add(l3);
        f1.add(p1);
        f1.add(tf2);
        f1.add(l4);
        f1.add(tf3);
        f1.add(l5);
        f1.add(tf4);
        f1.add(l6);
        f1.add(btn1);
        f1.add(btn2);
        f1.setVisible(true);  
        f1.setSize(1380,750);  
        f1.setLayout(null);
    }  
    
    private boolean registerData()
    {
            String string_regex = "^[a-zA-Z]+$";
            String email_regex = "^[A-Za-z0-9+_.-]+@(.+)$";
            String number_regex = "^[6-9][0-9]{9}$";
            String no_regex = "^[0-9]+$";
            name = tf1.getText();
            String password = new String(p1.getPassword());
            String email = tf2.getText();  
            String addr = tf3.getText();
            String PhoneNo = tf4.getText();  
            long phoneno=0;
            
            if(name.equals(""))
            {
                JOptionPane.showMessageDialog(null,"Name field should not be Empty");
                return false;
            }
            if(!name.matches(string_regex))
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
            
            if(email.equals(""))
            {
                JOptionPane.showMessageDialog(null,"Email field should not be Empty");
                return false;
            }
            if(!email.matches(email_regex))
            {
                JOptionPane.showMessageDialog(null,"Email format is wrong","Invalid Email",JOptionPane.WARNING_MESSAGE);
                return false;
            }
            if(addr.equals(""))
            {
                JOptionPane.showMessageDialog(null,"Address field should not be Empty");
                return false;
            }
            if(addr.matches(no_regex))
            {
                JOptionPane.showMessageDialog(null,"It should not be Number","Invalid City",JOptionPane.WARNING_MESSAGE);
                return false;
            }
            
            if(PhoneNo.equals(""))
            {
                JOptionPane.showMessageDialog(null,"PhoneNumber field should not be Empty");
                return false;
            }
            
            else if(!PhoneNo.equals(""))
            {
                if(PhoneNo.matches(number_regex))
                {
                    //System.out.println("yes");
                    phoneno  = Long.parseLong(PhoneNo);
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"Number should be in Indain Phone Number format","Invalid PhoneNo",JOptionPane.WARNING_MESSAGE);
                    return false;
                }
            }
            
            try  
            {  
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root","");
                        PreparedStatement ps = con.prepareStatement("insert into reg values(?,?,?,?,?)");  
                        ps.setString(1, name);
                        ps.setString(2, password);
                        ps.setString(3, email);  
                        ps.setString(4, addr);  
                        ps.setLong(5, phoneno);    
                        int rs = ps.executeUpdate();  
                        if (rs > 0)   
                        {  
                            return true;  
                        }
                        else
                        {
                            return false;
                        }
                }  
                catch(SQLException ex)
                {
                    System.out.println(ex);
                    //String s = "";
                }
                catch (Exception ex)   
                {  
                    System.out.println(ex);  
                }
            return false;
    }
    
    boolean allocateRoom()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root","");
            String sql="select * from roominfo where status=0";
            PreparedStatement ps = con.prepareStatement(sql);  
            ResultSet rs = ps.executeQuery(); 
            while(rs.next())
            {
                roomno = rs.getInt(1);
                System.out.println(roomno);
                sql = "update roominfo set usrname='"+name+"',status=1 where roomno='"+roomno+"'";
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
        }catch(Exception ex)
        {
            System.out.println(ex);
        }
        return false;
    }
    
    
    
    public void actionPerformed(ActionEvent e)   
    {  
        if (e.getSource() == btn1)  
        {  
            if(registerData() )
            {
                if(allocateRoom())
                {
                    JOptionPane.showMessageDialog(btn1, "Data Saved Successfully");
                    Logged l1 = new Logged();
                    l1.setLogged(name);
                    l1.setRoomNo(roomno);
                    f1.dispose();
                    new Student_Page();
                }
                else
                {
                    JOptionPane.showMessageDialog(btn1, "Insufficient Rooms");
                }
            }
            
        }
        else  
        {  
            tf1.setText("");  
            tf2.setText("");  
            tf3.setText(""); 
            tf4.setText(""); 
            p1.setText("");
        }
    }   
    public static void main(String args[])  
    {  
        new Register(); 
    }  
}  
