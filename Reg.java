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
//import java.awt.*;  
import java.awt.event.*;  
import java.sql.*;  
class Reg extends JFrame implements ActionListener   
{   
    private JPanel contentPane;
    private JLabel labels[],label;
    private JButton buttons[];
    private int size;
    private ResultSet rs;
    private String usernames[];
    private String complaints[];
    Reg()
    {
        contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        //JScrollPane sp = new JScrollPane(contentPane);
        //getContentPane().add(sp);
        label = new JLabel("User id");
        label.setBounds(150,50,150,30);
        //getContentPane().add(label);
        try
        {
            String status = "pending";
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root","");
            String sql="select * from grieveance where status='"+status+"'";
            PreparedStatement ps=con.prepareStatement(sql);
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs=ps.executeQuery();
            int i=0,y=200;
            if(rs.last())
            {
                size = rs.getRow();
                rs.beforeFirst();
            }
            usernames = new String[size];
            complaints = new String[size];
            labels = new JLabel[size];
            buttons = new JButton[size];
            System.out.println(size);
            rs.beforeFirst();
            int k=0;
            while(rs.next())
            {
                //System.out.println(rs.getString(2)+" "+i);
                usernames[k] = rs.getString(1);
                complaints[k] = rs.getString(2);
                k++;
                labels[i] = new JLabel(rs.getString(2));
                labels[i].setBounds(150, y, 1500, 50);
                y+=50;
                buttons[i] = new JButton("TakeAction");
                buttons[i].setBounds(150, y, 150, 30);
                buttons[i].addActionListener(this);
                i++;
                y+=50;
            }
            for(i=0; i<size; i++)
            {
                getContentPane().add(labels[i]);
                getContentPane().add(buttons[i]);
                System.out.println(usernames[i]+" "+complaints[i]);
            }
            setVisible(true);  
            setSize(700, 700);  
            setLayout(null);
            
        }catch(Exception ex)
        {
            System.out.println(ex);
        }
    }
    public void actionPerformed(ActionEvent e)   
    {  
        for(int i=0; i<size; i++){
            if (e.getSource() == buttons[i])  
            {  
                    int j=0;
                    try{
                        rs.beforeFirst();
                        while(rs.next())
                        {
                            if(j==i)
                            {
                                Class.forName("com.mysql.jdbc.Driver");
                                Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root","");
                                String sql = "UPDATE grieveance SET status='accept' WHERE complaints ='"+complaints[j]+"' and uname = '"+usernames[j]+"'";
                                PreparedStatement ps=con.prepareStatement(sql);
                                int rs = ps.executeUpdate();
                                if(rs>0)
                                {
                                    System.out.println("yes");
                                }
                                break;
                            }
                            j++;
                        }
                    
                    //JOptionPane.showMessageDialog(buttons[0], "Action Takened ");
                    System.out.println(rs.getString(2));
                    }catch(Exception ex){
                        System.out.println(ex);
                    }
                    buttons[i].setText("Action takenend ");
                    break;
                    //dispose();
                 
            }
        }
    }  
}  
