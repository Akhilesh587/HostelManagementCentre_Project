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
import java.awt.List;
import javax.swing.*;    
import java.awt.event.*;  
import java.sql.*; 
import java.util.ArrayList;


public class TakeAction extends JFrame implements ActionListener{
    private JPanel contentPane;
    private JLabel label,unamelbl,roomlbl,complaintlbl,actionlbl,roomnolbl,nocomplaints;
    private JButton hm,backbtn,actionbtn;
    private int size;
    private ResultSet rs;
   // private JTextField roomfield;
    ArrayList<String> usernames= new ArrayList<String>();
    
    private String rooms[];
    private String status = "pending";
    private JFrame f1;
    
    private String getName,getComplaint;
    private int getRoomno;
    
    private JComboBox name,complaint,room,temp;
    
    
    TakeAction()
    {
        contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        //JScrollPane sp = new JScrollPane(contentPane);
        //getContentPane().add(sp);
        
        //labels
        label = new JLabel("Take Action",JLabel.CENTER);
	Font myFont=new Font("Serif", Font.PLAIN, 60);
        label.setFont(myFont);
	label.setForeground(Color.DARK_GRAY);
        label.setBackground(Color.blue);
	label.setBounds(01,01,1360,100);
        label.setOpaque(true);
        getContentPane().add(label);
        
        hm=new JButton("Home");
	hm.setBounds(10,50,130,35);
        hm.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //f1.dispose();
                //new HMC();
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
                //f1.dispose();
                //new Student_Page();
             }
        });
        backbtn.setBackground(Color.LIGHT_GRAY);
        backbtn.setVisible(true);
        label.add(backbtn);
        
        //Username Label
        unamelbl = new JLabel("Username");
        unamelbl.setBounds(180,150,250,30);
        getContentPane().add(unamelbl);
        
        //Room Label
        roomlbl = new JLabel("Room No");
        roomlbl.setBounds(450,150,150,30);
        getContentPane().add(roomlbl);
        
        //Complaint Label
        complaintlbl = new JLabel("Complaints");
        complaintlbl.setBounds(750,150,150,30);
        getContentPane().add(complaintlbl);
        
        //Action/Performance Label
        actionlbl = new JLabel("Performance");
        actionlbl.setBounds(1050,150,150,30);
        getContentPane().add(actionlbl);
        
       // getting usernames
        try
        {
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root","");
            String sql="select distinct uname from grieveance where status='pending'";
            PreparedStatement ps=con.prepareStatement(sql);
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs=ps.executeQuery();
            int i=0,y=200;
            if(rs.last())
            {
                size = rs.getRow();
                rs.beforeFirst();
            }
            
            usernames.add("Select");
            while(rs.next()){
                usernames.add(rs.getString(1));
            }
            
            
            
        }catch(Exception ex)
        {
            System.out.println(ex);
        }
       //getUserFields();
        
    
   
            //getting room number
           // String username[]={"Select","yousuf","penta","naveen","akhilesh"};
            //String rooms[]={"Select","206","207","208","209"};
            //String complaints[]={"Select","AB","ASX","PSG","KSG","DSG"};

           name=new JComboBox();
           for(String item:usernames){
               name.insertItemAt(item,name.getItemCount());
           }
           name.setSelectedIndex(0);
           
           name.setBounds(150,200,250,30);
           getContentPane().add(name);
           name.setBackground(Color.LIGHT_GRAY);
           
           //dynamic change of roomnumber
           name.addActionListener(new ActionListener() {  
                    public void actionPerformed(ActionEvent e){       
                        getName = (String) name.getItemAt(name.getSelectedIndex());
                        actionbtn.setText("Pending");
                        System.out.println(getName);
                        if(getName!="Select"){
                            
                             

                            getRoomno=getRoom(getName);
                            
                            if(getRoomno!=0){
                                roomnolbl.setText(String.valueOf(getRoomno));
                            }
                            else{
                                roomnolbl.setText("");
                            }
                            /// Complaints Combo Box
                            if(getComplaints(getName)){
                                
                                
                                //complaint.addItem(complaint);
                                
                            }
                            else{
                                    Font f = new Font("Serif",Font.PLAIN,30);
                                    nocomplaints=new JLabel();
                                    nocomplaints.setFont(f);
                                    nocomplaints.setBounds(750, 200, 250, 30);
                                    getContentPane().add(nocomplaints);
                                    complaint.setEnabled(false);
                                     }

                        }

                       else{
                            roomnolbl.setText("");
                            complaint.removeAllItems();

                        }
         }
       });
           // getting usernames
           
            

           //room Label
           Font f = new Font("Serif",Font.PLAIN,30);
           roomnolbl=new JLabel();
           roomnolbl.setFont(f);
           roomnolbl.setBounds(450, 200, 250, 30);
           getContentPane().add(roomnolbl);
           
           complaint=new JComboBox();
          
           complaint.setBounds(750,200,250,30);
           getContentPane().add(complaint);
           complaint.setBackground(Color.LIGHT_GRAY);
           complaint.addActionListener(new ActionListener() {  
                    public void actionPerformed(ActionEvent e){       
                        getName = (String) name.getItemAt(name.getSelectedIndex());
                        actionbtn.setText("Pending");
                      }});
            
           //Action Button
            actionbtn=new JButton(status);
            actionbtn.setBounds(1050,200,250,30);
            actionbtn.addActionListener(this);
            actionbtn.setBackground(Color.LIGHT_GRAY);
            actionbtn.addActionListener(this);
            
            Font my11=new Font("Serif", Font.BOLD, 20);
            actionbtn.setFont(my11);
            getContentPane().add(actionbtn);
           
        
        
        
        setVisible(true);  
        setSize(1380, 700);  
        setLayout(null);
        //getUserFields();
        
    }
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==actionbtn){
            getComplaint=(String) complaint.getItemAt(complaint.getSelectedIndex());
            ArrayList<String> complaints;
            complaints = new ArrayList<String>();
            try{
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root","");
                    String sql="UPDATE grieveance SET status='accept' WHERE uname='"+getName+"' and complaints='"+getComplaint+"'";
                    PreparedStatement ps=con.prepareStatement(sql);
                    //Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    int rs=ps.executeUpdate();
                    if(rs>0){
                        
                        System.out.println(getComplaint);
                        getComplaints(getName);
                        actionbtn.setText("Accept");
                    }
            }catch(Exception ee){
                
            }
            
            
        }
    }
    
     int getRoom(String name){
        int room=0;
        try{
            Class.forName("com.mysql.jdbc.Driver");
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root","");
                String sql="select distinct roomno from grieveance where uname='"+name+"'";
                PreparedStatement ps=con.prepareStatement(sql);
                Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                rs=ps.executeQuery();
                if(rs.next())
                    room=rs.getInt(1);
        }catch(Exception e){
            
        }
       return room; 
    }
    boolean getComplaints(String uname){
        actionbtn.setText("Pending");
       ArrayList<String> complaints;
       complaints = new ArrayList<String>();
       boolean check=false;
       if(uname!="Select"){
        try{
            Class.forName("com.mysql.jdbc.Driver");
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root","");
                String sql="select distinct complaints from grieveance where uname='"+uname+"' and status='pending'";
                PreparedStatement ps=con.prepareStatement(sql);
                Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                rs=ps.executeQuery();
                if(rs.last())
            {
                size = rs.getRow();
                rs.beforeFirst();
            }
                System.out.println(size);
                size+=1;
                //complaints=new String[size];
                complaints.add("Select");
                
                while(rs.next()){
                    complaints.add(rs.getString(1));
                    System.out.println(rs.getString(1));
                }
                        complaint.removeAllItems();
                        int j=0;
                        for(String item:complaints){
                            complaint.insertItemAt(item, complaint.getItemCount());
                        }
                        complaint.setSelectedIndex(0);
                /// after all compalints User ComboBox wiil be change                
                 if(complaints.size()==1){
                     //getUserFields();
                 }   
            }catch(Exception e){
                return false;
            }
            return true;
           }
       else
           return false;
        //return check;
    }
    void getUserFields(){
        //ArrayList<String> usernames;
          //  usernames = new ArrayList<String>();
        try
        {
            usernames.clear();
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root","");
            String sql="select distinct uname from grieveance where status='pending'";
            PreparedStatement ps=con.prepareStatement(sql);
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs=ps.executeQuery();
            int i=0,y=200;
            if(rs.last())
            {
                size = rs.getRow();
                rs.beforeFirst();
            }
            usernames.add("Select");
            while(rs.next()){
                usernames.add(rs.getString(1));
            }
            name.removeAll();
            for(String item:usernames ){
                name.insertItemAt(item, name.getItemCount());
            }
            name.setSelectedIndex(0);
            
            
            
        }catch(Exception ex)
        {
            System.out.println(ex);
        }
    }
    
    public static void main(String[] args)
    {
        new TakeAction();
    }
    
}