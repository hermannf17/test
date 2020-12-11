package client;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import server.DBconnection;
import java.net.http.HttpClient;



//Login GUI - Erstellung der GUI
public class Gui implements ActionListener{
	
	private static JLabel userLabel;
	private static JTextField userText;
	private static JLabel ipLabel;
	private static JTextField ipText;
	private static JLabel passwordLabel;
	private static JPasswordField passwordText;
	private static JButton button;
	private static JLabel success;
	private static JLabel denied;
	private static JFrame frame = new JFrame();
	private static JPanel panel = new JPanel();
	
	
	httpclient client = new httpclient();
	HttpClient netClient = HttpClient.newHttpClient();
	
	
	public static void createGUI() {
		
		frame.setSize(380, 230);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		frame.setTitle("FancyLogin");
		frame.setLocationRelativeTo(null);
		panel.setLayout(null);
		
		userLabel = new JLabel("User");
		userLabel.setBounds(20, 60, 80, 25);
		panel.add(userLabel);
		
		userText = new JTextField();
		userText.setBounds(150, 60, 165, 25);
		panel.add(userText);
		
		ipLabel = new JLabel("Server-IP");
		ipLabel.setBounds(20, 30, 80, 25);
		panel.add(ipLabel);
		
		ipText = new JTextField();
		ipText.setBounds(150, 30, 165, 25);
		panel.add(ipText);
		
		passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(20, 90, 80, 25);
		panel.add(passwordLabel);
		
		passwordText = new JPasswordField();
		passwordText.setBounds(150, 90, 165, 25);
		panel.add(passwordText);
		
		button = new JButton("Login");
		button.setBounds(20, 130, 80, 25);
		button.addActionListener(new Gui());
		panel.add(button);
					
		success = new JLabel("");
		success.setBounds(20, 130, 400, 25);
		panel.add(success);
		
		denied = new JLabel("");
		denied.setBounds(20, 160, 400, 25);
		panel.add(denied);
		
		frame.setVisible(true);
	
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		try {
			if(ipText.getText().equals("")) {
				denied.setText("Bitte geben Sie eine gültige IP-Adresse ein!");
				
			}else if(httpclient.postRequestLogin(ipText.getText(), netClient, userText.getText(), passwordText.getText()) == 200) {
				String[] str = httpclient.postRequestAllFiles(ipText.getText(), netClient, userText.getText());
				GuiDocumentListe.getDocumentList(ipText.getText(), str, userText.getText());
				frame.dispose();
				ipText.setText("localhost");
				userText.setText("");
				passwordText.setText("");
				denied.setText("");	
			}
	
	        else {
	        	System.out.println("Anmeldung fehlgeschlagen");
	        }

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	
	
	
	

}
