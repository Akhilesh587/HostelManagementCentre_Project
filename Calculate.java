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
import java.awt.BorderLayout;
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
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author RGUKT
 */
public class Calculate {
    
    
    JFrame f1;
    JLabel labe,la;
    JTextField fee;
    ResultSet rs2;
    private JTable table;
    private double total_fee=0;
    Calculate(){
    
        f1=new JFrame("calculate");
       // f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f1.setBackground(Color.cyan);
        labe = new JLabel("Calculate",JLabel.CENTER);
	Font myFont=new Font("Serif", Font.PLAIN, 40);
        labe.setFont(myFont);
	labe.setForeground(Color.DARK_GRAY);
        labe.setBackground(Color.blue);
	labe.setBounds(01,01,1360,100);
        labe.setOpaque(true);
	f1.add(labe);
        
        JButton hm=new JButton("Home");
	hm.setBounds(10,50,130,35);
        hm.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
                f1.dispose();
                new HMC();
             }
        });
        labe.add(hm);
        JButton bc=new JButton("Back");
	bc.setBounds(1200,50,130,35);
        bc.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
                f1.dispose();
                new HMC();
             }
        });
        labe.add(bc);
        
        
        Font f = new Font("Serif",Font.PLAIN,25);
        JLabel usrlabel = new JLabel("Fee :");
        usrlabel.setFont(f);
        usrlabel.setBounds(530,120, 200, 50);
        f1.add(usrlabel);
        
        fee = new JTextField();
        fee.setFont(f);
        fee.setBounds(600, 130, 220, 40);
        f1.add(fee);
        
        
        
        
        JButton comp=new JButton("CalculatePay");
	comp.setBounds(600,200,130,35);
        comp.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
               if(validate()){ 
                   
                if(calculatefees()){
                   
                    
                    JOptionPane.showMessageDialog(null, "Calucating Fees completed \n"+"Toatal Amount:"+Double.toString(total_fee));
                    
                    JFrame f2=new JFrame("HMC/Warden/ViewStudents");
                    //f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    JPanel  contentPane = new JPanel();
                    contentPane.setLayout(null);
                    f2.setContentPane(contentPane);
                    int size=0; 
                    String[][] StudentData;
                
                        try{

                            Class.forName("com.mysql.jdbc.Driver");
                            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root","root");
                            String sql="select * from pay";
                            PreparedStatement ps = con.prepareStatement(sql);  
                            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                             rs2=ps.executeQuery();

                            if(rs2.last())
                            {
                                size = rs2.getRow();
                                rs2.beforeFirst();
                            }
                            //System.out.println(size);
                            }catch(Exception ex)
                            {
                                System.out.println(ex);
                            }
                            StudentData = new String[size][2];    
                            int k=0;
                            try{

                                while(rs2.next())
                                {
                                        StudentData[k][0] = rs2.getString(1);    //usernames
                                        StudentData[k][1] = rs2.getString(2); //messpay
                                        k++;
                                        
                                }
                            }catch(Exception ex)
                            {
                                System.out.println(ex);
                            }                    
                    
                                String[] columnNames = {"Name","Mess Fee"};
        
                                table = new JTable(StudentData, columnNames);
                                table.setDefaultEditor(Object.class, null);
                                JScrollPane scrollPane = new JScrollPane(table);
                                table.setFillsViewportHeight(true);


                                f2.getContentPane().setLayout(new BorderLayout());
                                f2.getContentPane().add(scrollPane,BorderLayout.CENTER);
                                //f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                f2.setBounds(400, 200, 700, 250);
                                f2.setVisible(true);
                    
                       
                }
                else{
                    JOptionPane.showMessageDialog(null, "Not Completed");
                }
            } 
             }
        });
        f1.add(comp);
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
    boolean calculatefees()
    {
        
        try  
            {           int number_of_students=0;
                        double mess_fee = Double.parseDouble(fee.getText());
                        int count=0;
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root","root");
                        String sql="select * from pay ";
                        PreparedStatement ps = con.prepareStatement(sql);  
                        ResultSet rs = ps.executeQuery();
                        total_fee=0;
                        while (rs.next())   
                        {      
                              String un=rs.getString(1);
                              String sql1="Update pay set messpay= '"+mess_fee+"'  where username='"+un+"'";
                              PreparedStatement ps1 = con.prepareStatement(sql1);
                             
                              int r = ps1.executeUpdate();
                              
                              number_of_students++;
                              if(r>0)
                                  count++;
                        }

                            System.out.println(count);
                            System.out.println(number_of_students);
                        if(count==number_of_students){
                            total_fee=count*mess_fee;
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
 boolean validate(){
     String string_regex = "^[a-zA-Z]+$";
     String mess = fee.getText();
         
     try{
                           double mess_fee = Double.parseDouble(fee.getText());
              if(mess.matches(string_regex))
              {
                JOptionPane.showMessageDialog(null,"It should be Number","wrong",JOptionPane.WARNING_MESSAGE);
                //tf1.requestFocusInWindow();
                return false;
               }


                if(mess_fee<1000 || mess_fee >10000){
                
                 JOptionPane.showMessageDialog(null,"Amount Between 1000 and 10000","Wrong",JOptionPane.WARNING_MESSAGE);
                //tf1.requestFocusInWindow();
                return false;
                }
         
         
     }catch(Exception E)
     {
        // System.out.println(E);
         JOptionPane.showMessageDialog(null,"Enter Valid Amount","Wrong",JOptionPane.WARNING_MESSAGE);
                //tf1.requestFocusInWindow();
         return false;
     }
     return true;
 }   
    public static void main(String args[]){
    
        new Calculate();
    }
}

