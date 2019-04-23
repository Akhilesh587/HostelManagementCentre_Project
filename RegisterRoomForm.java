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

public class RegisterRoomForm {
    private JFrame f1;
    private JLabel roomlabel,room,readingroomlabel,readingroom,usrlabel,usrcontent,pricelabel,price;
    private JButton addbtn;
    private String Roomno;
    private double Price;
    RegisterRoomForm(String Roomno, double Price)
    {
        this.Roomno = Roomno;
        this.Price = Price;
        f1=new JFrame("HMC/Student/Edit Profile");
        f1.setBackground(Color.cyan);
        
        // user name 
        
        Font f = new Font("Serif",Font.PLAIN,30);
        usrlabel = new JLabel("UserName:");
        usrlabel.setFont(f);
        usrlabel.setBounds(100, 100, 200, 50);
        f1.add(usrlabel);
        
        usrcontent = new JLabel(new Logged().getId());
        usrcontent.setFont(f);
        usrcontent.setBounds(350, 100, 280, 50);
        f1.add(usrcontent);
        
        // room no
        
        int roomNo = getRoomNo();
        roomlabel = new JLabel("Room No:");
        roomlabel.setFont(f);
        roomlabel.setBounds(100, 150, 200, 50);
        f1.add(roomlabel);
        
        room = new JLabel(Integer.toString(roomNo));
        room.setFont(f);
        room.setBounds(350, 150, 280, 50);
        f1.add(room);
        
        // reading room no
        
        readingroomlabel = new JLabel("Reading RoomNo:");
        readingroomlabel.setFont(f);
        readingroomlabel.setBounds(100, 200, 300, 50);
        f1.add(readingroomlabel);
        
        readingroom = new JLabel(Roomno);
        readingroom.setFont(f);
        readingroom.setBounds(350, 200, 280, 50);
        f1.add(readingroom);
        
        // price 
        
        pricelabel = new JLabel("Price:");
        pricelabel.setFont(f);
        pricelabel.setBounds(100, 250, 200, 50);
        f1.add(pricelabel);
        
        price = new JLabel("200.00");
        price.setFont(f);
        price.setBounds(350, 250, 280, 50);
        f1.add(price);
        
        Font buttonFont=new Font("Serif", Font.BOLD, 20);
        addbtn = new JButton("Add");
        addbtn.setBounds(350, 300, 135, 35);
        addbtn.setFont(buttonFont);
        addbtn.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(RegisterData())
                {
                    JOptionPane.showMessageDialog(null, "Added Successfully");
                    f1.dispose();
                }
             }
        });
        addbtn.setBackground(Color.LIGHT_GRAY);
        addbtn.setVisible(true);
        f1.add(addbtn);
        
        f1.setBounds(300,200,700,400);
	f1.setBackground(Color.blue);
	f1.setLayout(null);
        f1.setVisible(true);
    }
    
    private boolean RegisterData()
    {
        try
        {
            String uname = new Logged().getId();
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root","");
            String sql="UPDATE readingroombooking SET username='"+uname+"',status=1,price='"+Price+"' WHERE roomno='"+Roomno+"'";
            PreparedStatement ps = con.prepareStatement(sql);  
            int rs = ps.executeUpdate();
            if(rs>0)
            {
                return true;
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
        return false;
    }
    
    private int getRoomNo()
    {
        try
        {
            String uname = new Logged().getId();
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root","");
            String sql="select * from roominfo where usrname='"+uname+"'";
            PreparedStatement ps = con.prepareStatement(sql);  
            ResultSet rs = ps.executeQuery(); 
            while(rs.next())
                return rs.getInt(1);
        }catch(Exception ex)
        {
            System.out.println(ex);
        }
        return 0;
    }
    
    public static void main(String[] args)
    {
        new RegisterRoomForm("R201",200.00);
    }
}
