package client;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.net.http.HttpClient;
import java.util.Vector;

import javax.swing.*;

import java.awt.Cursor;
import java.awt.event.*;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.util.*;
import java.awt.*;

public class GuiDocumentListe extends JFrame{
	
	public static void getDocumentList(String url, String [] list, String username) {
		
		JFrame documentList = new JFrame();
		JPanel buttonsPanel = new JPanel();
		final JLabel label = new JLabel();            
	     label.setBounds(20,220, 100,30);
		JList liste = new JList();
		JScrollPane scp;
		final Vector data = new Vector();
		
		httpclient client = new httpclient();
		HttpClient netClient = HttpClient.newHttpClient();
		
		JButton uploadButton = new JButton ("Upload");
		JButton downloadButton = new JButton ("Download");
		JButton closeButton = new JButton("Close");
	
		if(list.length == 0) {
			JLabel noFiles = new JLabel("No Documents");
			noFiles.setBounds(20,50,200,300);
			documentList.add(noFiles);
		}else {
			for(int i=0; i<list.length; i++)
				data.addElement(list[i]);	
		}
		
		liste.setListData(data);
		liste.setSelectedIndex(0);
		scp=new JScrollPane(liste);
		documentList.add(scp, BorderLayout.NORTH);
		
		//uploadButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		uploadButton.addActionListener(new ActionListener() {
			
			@Override
	    	public void actionPerformed(ActionEvent ae) {
				final JFileChooser fileChooser = new JFileChooser();
	    		 fileChooser.setCurrentDirectory(FileSystemView.getFileSystemView().getHomeDirectory());
	    		 fileChooser.setAcceptAllFileFilterUsed(false);
	    		 FileNameExtensionFilter filter=new FileNameExtensionFilter("TXT ONLY", "txt");
	    		 fileChooser.addChoosableFileFilter(filter);
	    		 int result=fileChooser.showOpenDialog(new JFrame());
	     	   
	    		 if(result==fileChooser.APPROVE_OPTION) {
	    			 File selectedFile=fileChooser.getSelectedFile();
	    			 httpclient.postRequestUpload(url, netClient, username, selectedFile.getAbsolutePath());
	    			 System.out.println(selectedFile.getAbsolutePath());
	    			 documentList.dispose();
	        	     String[] files = httpclient.postRequestAllFiles(url, netClient, username);
	        	     GuiDocumentListe.getDocumentList(url, files, username);
	    		 }
	    	 }
	    });
		
		
		downloadButton.addActionListener(new ActionListener() {
			
			@Override
	    	public void actionPerformed(ActionEvent ae) {
				final JFileChooser fileChooserDL = new JFileChooser();
        		fileChooserDL.setCurrentDirectory(FileSystemView.getFileSystemView().getHomeDirectory());
        	    int result=fileChooserDL.showSaveDialog(new JFrame());
        	    
        	    if(result==fileChooserDL.APPROVE_OPTION) {
        	    	File selectedFile = fileChooserDL.getCurrentDirectory();
        	    	httpclient.postRequestDownload(url, netClient, username, liste.getSelectedValue().toString(), selectedFile.getAbsolutePath(), fileChooserDL.getSelectedFile().getName());
        	    	documentList.dispose();
        	    	String[] files2 = httpclient.postRequestAllFiles(url, netClient, username);
        	    	GuiDocumentListe.getDocumentList(url, files2, username);
	    		 }
	    	 }
	    });
		
		
		closeButton.addActionListener(new ActionListener() {
			
			@Override
	    	public void actionPerformed(ActionEvent ae) {
				documentList.dispose();
	    		 }
	    });
		
		
		
		documentList.setTitle("Dokumentenliste");
		buttonsPanel.add(downloadButton);
		buttonsPanel.add(uploadButton);
		buttonsPanel.add(closeButton);
	    documentList.add(buttonsPanel, BorderLayout.SOUTH);
	    documentList.setSize(600,275);
	    documentList.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    documentList.setLocationRelativeTo(null);
	    documentList.setVisible(true);  
	 	
	}
	

}
