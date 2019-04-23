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

public class PlayingRoomBooking {
    private JFrame f1;
    private JButton hm,backbtn,addroombtn;
    private JLabel label,roomlabel,room;
    private String uname,sql;
    private Connection con;
    private ResultSet rs;
    private PreparedStatement ps;
    private int size;
    private JLabel selectlabel;
    private String[] rooms;
    private String roomno;
    private JComboBox cb;
    PlayingRoomBooking()
    {
        f1=new JFrame("HMC/Student/Booking");
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f1.setBackground(Color.cyan);
        label = new JLabel("BOOKING PLAYING ROOM",JLabel.CENTER);
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
        
        //back button
       
        backbtn = new JButton("Back");
        backbtn.setBounds(1220, 50, 130, 35);
        backbtn.setFont(buttonFont);
        backbtn.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
                f1.dispose();
                new Booking();
             }
        });
        backbtn.setBackground(Color.LIGHT_GRAY);
        backbtn.setVisible(true);
        label.add(backbtn);
        
        uname = new Logged().getId();
        roomno = findRoomNo(uname);
        System.out.println(roomno);
        if(roomno.equals("NULL"))
        {
            //System.out.println("yes");
            displayReadingRooms();
        }
        else
        {
            DisplayReadingRoom();
        }
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
  
    private String findRoomNo(String uname)
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root","");
            sql = "select roomno from playingroombooking WHERE username = '"+uname+"'";
            ps = con.prepareStatement(sql);   
            rs = ps.executeQuery();
            if(rs.next())
            {
                System.out.println(rs.getString(1));
                return rs.getString(1);
            }
            else
            {
                return "NULL";
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
        return "NULL";
    }
    
    
    private void displayReadingRooms()
    {
        int status = 0;
        try
        {
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            sql = "select roomno from playingroombooking WHERE status='"+status+"'";
            ps = con.prepareStatement(sql);   
            rs = ps.executeQuery();
            
            if(rs.next()==false)
            {
                JOptionPane.showMessageDialog(null, "All rooms are filled");
            }
            else
            {
                
                selectlabel = new JLabel("Select the Room",JLabel.CENTER);
                selectlabel.setBounds(01,143,1360,100);
                selectlabel.setFont(new Font("Serif",Font.PLAIN,30));
                f1.add(selectlabel);
                
                if(rs.last())
                {
                    size = rs.getRow();
                    rs.beforeFirst();
                }
                System.out.println(size);
                rooms = new String[size];
                int i=0;
                rs.beforeFirst();
                while(rs.next())
                {
                    rooms[i]=rs.getString(1);
                    i++;
                }
                cb = new JComboBox(rooms);
                
                /*for(int j=0; j<size; j++){
                System.out.print(rooms[j]+" ");}*/
                
                cb.setBounds(500,250,350,50);
                cb.setBackground(Color.LIGHT_GRAY);
                cb.setFont(new Font("Serif",Font.PLAIN,30));
                f1.add(cb);
                
                addroombtn = new JButton("Add Room");
                addroombtn.setBounds(500,350,200,35);
                addroombtn.setBackground(Color.LIGHT_GRAY);
                addroombtn.setFont(new Font("Serif",Font.PLAIN,30));
                addroombtn.addActionListener( new ActionListener(){
                        public void actionPerformed(ActionEvent e){
                        Object s = (cb.getItemAt(cb.getSelectedIndex()));
                        roomno = s.toString();
                        new PlayingRoomRegistrationForm(roomno,400.00);
                        cb.setVisible(false);
                        addroombtn.setVisible(false);
                        selectlabel.setVisible(false);
                        DisplayReadingRoom();
                    }
                });
                f1.add(addroombtn);
            }
        }
        
        catch(Exception ex)
        {
            System.out.println(ex);
        }
    }
    
    private void DisplayReadingRoom()
    {
        roomlabel = new JLabel("Playing Room No:");
        roomlabel.setBounds(500,200,350,50);
        roomlabel.setFont(new Font("Serif",Font.PLAIN,30));
        f1.add(roomlabel);
        
        room = new JLabel(roomno);
        room.setBounds(750,200,350,50);
        room.setFont(new Font("Serif",Font.PLAIN,30));
        f1.add(room);
    }
    
    public static void main(String[] args)
    {
        new PlayingRoomBooking();
    }
}
