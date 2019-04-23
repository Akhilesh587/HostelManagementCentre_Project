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
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
/**
 *
 * @author RGUKT
 */
    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


class Grieveance implements ActionListener   
{
    private JLabel roomlabel,room,label,usrlabel,cmplabel,usrnamelabel;
    private JTextArea cmparea;
    private JButton submitButton,clearButton,homebtn,backbtn,viewbtn;
    private JFrame f1;
    private String uname,  getComplaint;
    private int roomNo;
    private JComboBox comp;
    Grieveance()  
    {  
        f1=new JFrame("HMC/Student/Grieveance");
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        label = new JLabel("GRIEVEANCE",JLabel.CENTER);
	Font myFont=new Font("Serif", Font.PLAIN, 60);
        label.setFont(myFont);
	label.setForeground(Color.DARK_GRAY);
        label.setBackground(Color.blue);
	label.setBounds(01,01,1360,142);
        label.setOpaque(true);
	f1.add(label);
        
        // username label and text field
        
        Font f = new Font("Serif",Font.PLAIN,30);
        usrlabel = new JLabel("UserName:");
        usrlabel.setFont(f);
        usrlabel.setBounds(400, 200, 200, 50);
        f1.add(usrlabel);
        
        uname = new Logged().getId();
        usrnamelabel = new JLabel(uname);
        usrnamelabel.setFont(f);
        usrnamelabel.setBounds(550, 200, 350, 50);
        f1.add(usrnamelabel);
        
        // room no label
        
        roomNo = getRoomNo();
        roomlabel = new JLabel("Room No:");
        roomlabel.setFont(f);
        roomlabel.setBounds(400, 250, 200, 50);
        f1.add(roomlabel);
        
        room = new JLabel(Integer.toString(roomNo));
        room.setFont(f);
        room.setBounds(550, 250, 350, 50);
        f1.add(room);
        
        // complaint label and text area
        String [] complaints={"Select","Clean the Room","Bulb is not Working","Fan is not Working","Window/Door repairing","Tap is not Working",""};
        cmplabel = new JLabel("Complaint:");  
        cmplabel.setFont(f);
        cmplabel.setBounds(400, 300, 280, 100);
        f1.add(cmplabel);
        
        Font f2 = new Font("Serif",Font.PLAIN,20);
        comp = new JComboBox(complaints);
        comp.setFont(f2);
        //JScrollPane sp = new JScrollPane(cmparea);
        comp.setBounds(550, 330, 370, 30);
        //f1.add(sp);
        comp.setBackground(Color.LIGHT_GRAY);
   
        f1.add(comp);
        
        comp.addActionListener(new ActionListener() {  
                    public void actionPerformed(ActionEvent e){       
                        String getName = (String) comp.getItemAt(comp.getSelectedIndex());
                        if(getName=="Select"){
                            JOptionPane.showMessageDialog(submitButton, "Choose One Option");
                            
                        }
                    }});
        
        
        //Button Font
        Font buttonFont=new Font("Serif", Font.BOLD, 20);
        
        //submit Button
        submitButton = new JButton("Submit");
        submitButton.setBounds(550, 500, 130, 35);
        submitButton.setFont(buttonFont);
        submitButton.setBackground(Color.LIGHT_GRAY);
        f1.add(submitButton);
        
        //clear Button
        clearButton = new JButton("Clear");
        clearButton.setBounds(710, 500, 130, 35);
        clearButton.setFont(buttonFont);
        clearButton.setBackground(Color.LIGHT_GRAY);
        f1.add(clearButton);
        
        
        // Home Button
        
        homebtn = new JButton("Home");
        homebtn.setBounds(10,50,130,35);;
        homebtn.setFont(buttonFont);
        homebtn.setBackground(Color.LIGHT_GRAY);
        label.add(homebtn);
        
        // back Buttton
        backbtn = new JButton("Back");
        backbtn.setBounds(1220, 50, 130, 35);
        backbtn.setFont(buttonFont);
        backbtn.setBackground(Color.LIGHT_GRAY);
        label.add(backbtn);
        
        // viewbtn
        
        viewbtn = new JButton("ViewComplaints");
        viewbtn.setBounds(550, 550, 292, 35);
        viewbtn.setFont(buttonFont);
        viewbtn.setBackground(Color.LIGHT_GRAY);
        //f1.add(viewbtn);
        
        
        submitButton.addActionListener(this);
        clearButton.addActionListener(this);  
        homebtn.addActionListener(this); 
        backbtn.addActionListener(this);
        viewbtn.addActionListener(this);
        
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
    
    private boolean registerData()
    {
            
             getComplaint = (String) comp.getItemAt(comp.getSelectedIndex());
            
            try  
            {  
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root","");
                        PreparedStatement ps = con.prepareStatement("insert into grieveance values(?,?,?,?)");  
                        ps.setString(1, uname); 
                        ps.setInt(2, roomNo);
                        ps.setString(3,getComplaint);
                        ps.setString(4,"pending");
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
                    //throw new MySQLIntegrityConstraintViolationException(ex);
                }
                catch (Exception ex)   
                {  
                    System.out.println(ex);  
                }
            return false;
    }
    public void actionPerformed(ActionEvent e)   
    {  
        if (e.getSource() == submitButton)  
        {  
            if(registerData())
            {
                JOptionPane.showMessageDialog(submitButton, "\t"+getComplaint+" is\nSuccessfully sent to\n\tThe Warden\n\t\tThank You .....!");
                f1.dispose();
                new Student_Page();
            }
            else{
                JOptionPane.showMessageDialog(submitButton, "Complaint Not Sent to Warden...\n try again");
            }
            
        }
        else if(e.getSource()== homebtn)
        {
            f1.dispose();
            new HMC();
        }
        else if(e.getSource()==backbtn)
        {
            f1.dispose();
            new Student_Page();
        }
        else if(e.getSource()==viewbtn)
        {
            new ViewComplaints();
        }
        else  
        {  
            cmparea.setText("");  
        }
    }   
    public static void main(String args[])  
    {  
        new Grieveance(); 
    }  
}  
