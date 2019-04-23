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
import static com.sun.java.accessibility.util.AWTEventMonitor.addActionListener;
import javax.swing.*;  
import java.awt.*;  
import java.awt.event.*;  
import java.sql.*;  

public class EditProfile {
    
    private JFrame f1;
    private JTextField phonefield,addrfield,emailfield;
    private JPasswordField passfield;
    private JLabel l,roomlabel,room,phonelabel,addrlabel,emaillabel,label,usrlabel,usrcontent,passlabel;
    private JButton hm,backbtn,updatebtn,show;
    private String uname,password,email,addr,PhoneNo;
    private long phoneno;
    private boolean toggle=false;
    EditProfile()
    {
        f1=new JFrame("HMC/Student/Edit Profile");
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f1.setBackground(Color.cyan);
        label = new JLabel("EDIT PROFILE",JLabel.CENTER);
	Font myFont=new Font("Serif", Font.PLAIN, 60);
        label.setFont(myFont);
	label.setForeground(Color.DARK_GRAY);
        label.setBackground(Color.blue);
	label.setBounds(01,01,1360,142);
        label.setOpaque(true);
	f1.add(label);
        
        
        
        Icon showeye = new ImageIcon("show1.png");
        Icon hideeye = new ImageIcon("hide.png");
    
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
        
        // user name 
        
        Font f = new Font("Serif",Font.PLAIN,15);
        usrlabel = new JLabel("UserName:");
        usrlabel.setFont(f);
        usrlabel.setBounds(400, 135, 200, 50);
        f1.add(usrlabel);
        
        usrcontent = new JLabel(new Logged().getId());
        usrcontent.setFont(f);
        usrcontent.setBounds(550, 135, 280, 50);
        f1.add(usrcontent);
        
        // room no
        
        int roomNo = getRoomNo();
        roomlabel = new JLabel("Room No:");
        roomlabel.setFont(f);
        roomlabel.setBounds(400, 200, 200, 50);
        f1.add(roomlabel);
        
        room = new JLabel(Integer.toString(roomNo));
        room.setFont(f);
        room.setBounds(550, 200, 280, 50);
        f1.add(room);
        
        // password label and text field
        
        passlabel = new JLabel("Password:");
        passlabel.setFont(f);
        passlabel.setBounds(400, 280, 200, 50);
        f1.add(passlabel);
        
        passfield = new JPasswordField();
        passfield.setFont(f);
        passfield.setBounds(550, 280, 280, 50);
        f1.add(passfield);
        
        show=new JButton("show");
        
        show.setFont(f);
        show.setBounds(825, 280, 80, 49);
        show.setBackground(Color.WHITE);
        
        f1.add(show);
        
        show.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String s= new String( passfield.getPassword());
                if(toggle){
                    passfield.setEchoChar('*');
                    show.setText("show");
                           
                    toggle=false;
                }
                else{
                    passfield.setEchoChar((char)0);
                    show.setText("hide");
                    toggle=true;
                }
                    
            }
        });
                
        
        
        // email
        
        emaillabel = new JLabel("Email:");
        emaillabel.setFont(f);
        emaillabel.setBounds(400, 360, 200, 50);
        f1.add(emaillabel);
        
        emailfield = new JTextField();
        emailfield.setFont(f);
        emailfield.setBounds(550, 360, 280, 50);
        f1.add(emailfield);
        
        // address
        
        addrlabel = new JLabel("Address:");
        addrlabel.setFont(f);
        addrlabel.setBounds(400, 440, 200, 50);
        f1.add(addrlabel);
        
        addrfield = new JTextField();
        addrfield.setFont(f);
        addrfield.setBounds(550, 440, 280, 50);
        f1.add(addrfield);
        
        // phone no
        
        phonelabel = new JLabel("Phone No:");
        phonelabel.setFont(f);
        phonelabel.setBounds(400, 520, 200, 50);
        f1.add(phonelabel);
        
        phonefield = new JTextField();
        phonefield.setFont(f);
        phonefield.setBounds(550, 520, 280, 50);
        f1.add(phonefield);
        
        // update btn
        
        updatebtn = new JButton("Update");
        updatebtn.setBounds(570, 590, 150, 35);
        updatebtn.setFont(buttonFont);
        updatebtn.setBackground(Color.LIGHT_GRAY);
        updatebtn.setVisible(true);
        updatebtn.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //f1.dispose();
                //new HMC();
                if(ValidateData())
                {
                    if(UpdateData())
                    {
                        JOptionPane.showMessageDialog(null, "Updated Successfully");
                        f1.dispose();
                        new Student_Page();
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Cannot Update Check Properly");
                    }
                }
             }
        });
        f1.add(updatebtn);
        
        
        
        f1.setSize(1380,750);
	f1.setBackground(Color.blue);
	f1.setLayout(null);
        f1.setVisible(true);
	f1.addWindowListener(new WindowAdapter(){
        @Override
	public void windowClosing(WindowEvent we){
		f1.dispose();}
        });
        getDetails();
    }
    
    private boolean UpdateData()
    {
        try  
        {  
            uname = new Logged().getId();
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root","");
            String sql = "UPDATE reg SET password='"+password+"',email='"+email+"',address='"+addr+"',phoneno='"+phoneno+"' WHERE username = '"+uname+"'";
            PreparedStatement ps = con.prepareStatement(sql);   
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
        catch (Exception ex)   
        {  
            System.out.println(ex);  
        }
        return false;
    }
    
    private boolean ValidateData()
    {
            String string_regex = "^[a-zA-Z]+$";
            String email_regex = "^[A-Za-z0-9+_.-]+@(.+)$";
            String number_regex = "^[6-9][0-9]{9}$";
            String no_regex = "^[0-9]+$";
            password = new String(passfield.getPassword());
            email = emailfield.getText();  
            addr = addrfield.getText();
            PhoneNo = phonefield.getText();
            
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
            return true;
    }
    
    private int getRoomNo()
    {
        try
        {
            String uname = new Logged().getId();
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root","");
            String sql="select * from roominfo where usrname='"+uname+"'";
            //String sql="select * from roominfo where usrname='akhilesh'";
            PreparedStatement ps = con.prepareStatement(sql);  
            ResultSet rs = ps.executeQuery(); 
            while(rs.next())
                return rs.getInt(1);
            /// for assigning data in fields
        }catch(Exception ex)
        {
            System.out.println(ex);
        }
        return 0;
    }
    private void getDetails(){
        try
        {
            String uname = new Logged().getId();
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root","");
            String sql="select * from reg where username='"+uname+"'";
            //String sql="select * from reg where username='akhilesh'";
            PreparedStatement ps = con.prepareStatement(sql);  
            ResultSet rs = ps.executeQuery(); 
            while(rs.next()){
                System.out.println(rs.getString(2));
                System.out.println(rs.getString(3));
                passfield.setText(rs.getString(2));
                //l.setText(rs.getString(2));
                
                emailfield.setText(rs.getString(3));
                addrfield.setText(rs.getString(4));
                phonefield.setText(Long.toString(rs.getLong(5)));
                
            }
        }catch(Exception ex)
        {
            System.out.println(ex);
        }
    }
    
    public static void main(String[] args)
    {
        new EditProfile();
    }
}
