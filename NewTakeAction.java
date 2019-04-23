/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hmc;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewTakeAction
{
    NewTakeAction(){
    
                    JFrame frame = new JFrame("panel demo");
                frame.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
                class Container extends JPanel{
                    int id;
                    JButton btn;
                    JLabel lbl1,lbl2,lbl3;
                    public Container(int id){
                        this.id = id;
                        this.setLayout(new GridLayout(1,3));
                        lbl1 = new JLabel("Name");
                        this.add(lbl1);
                        lbl2 = new JLabel("Room");
                        this.add(lbl2);
                        lbl3 = new JLabel("Complaint");
                        this.add(lbl3);
                        btn = new JButton("Action");
                        btn.addActionListener(new ActionListener(){
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                System.out.println("TrancationId :"+id);
                            }
                        });
                        this.add(btn);
                    }
                }
                JPanel panel = new JPanel();
                
                panel.setBounds(20,0,700,900);
                panel.setLayout(new GridLayout(30,1));
                for(int i=0;i<30;i++)
                    panel.add(new Container(i+1));
              /*  for(int i = 0; i<10;i++){
                 panel.add(new Container());
                } */  
                JScrollPane jsp = new JScrollPane(panel);
                frame.add(panel);
                frame.add(jsp);
                frame.setSize(1300,700);
                frame.setVisible(true);
    }
    
    public static void main(String[] args){
            new NewTakeAction();
    }
}