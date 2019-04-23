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
// Packages to import 
import javax.swing.JFrame; 
import javax.swing.JScrollPane; 
import javax.swing.JTable; 

public class JTableExample { 
	// frame 
	JFrame f; 
	// Table 
	JTable j; 

	// Constructor 
	JTableExample() 
	{ 
		// Frame initiallization 
		f = new JFrame(); 

		// Frame Title 
		f.setTitle("JTable Example"); 

		// Data to be displayed in the JTable 
		String[][] data = { 
			{ "Kundan Kumar Jha jakdj aksdjlfk aksjd asdjlk aklsdj flasdj falsd kfajsdl jf", "4031", "CSE" }, 
			{ "Anand Jha", "6014", "IT" },
                        { "Anand Jha", "6014", "IT" },
                        { "Anand Jha", "6014", "IT" },
                        { "Anand Jha", "6014", "IT" },
                        { "Anand Jha", "6014", "IT" },
                        { "Anand Jha", "6014", "IT" },
                        { "Anand Jha", "6014", "IT" },
                        { "Anand Jha", "6014", "IT" },
                        { "Anand Jha", "6014", "IT" },
                        { "Anand Jha", "6014", "IT" }
		}; 

		// Column Names 
		String[] columnNames = { "Name", "Room Number", "Complaint" }; 

		// Initializing the JTable 
		j = new JTable(data, columnNames); 
		j.setBounds(30, 40, 200, 300); 

		// adding it to JScrollPane 
		JScrollPane sp = new JScrollPane(j); 
                j.setFillsViewportHeight(true);
		f.add(sp); 
		// Frame Size 
		f.setSize(500, 200); 
		// Frame Visible = true 
		f.setVisible(true); 
	} 

	// Driver method 
	public static void main(String[] args) 
	{ 
		new JTableExample(); 
	} 
} 

