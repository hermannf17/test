package client;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.*;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import server.DBconnection;


public class Requests{
	

	//Auflistung aller Files des Users
	public static String[] getAllFiles(String username, Connection con) {
			
		//Connection conn = DBconnection.connect(null);
		
        try {
            PreparedStatement statement = con.prepareStatement("Select filename FROM Documents WHERE username = '"+username+"'");
            ResultSet result = statement.executeQuery();
            
            ArrayList<String> arrayList = new ArrayList<String>();
            while(result.next()) {
            	//System.out.println(result.getString(1));
            	arrayList.add(result.getString(1));
            }
            
            System.out.println("Documents selected");
            String[] array = new String[arrayList.size()];
            for(int i = 0; i < array.length; i++) {
            	array[i] = arrayList.get(i);
            	System.out.println(array[i]);
            }
            
            return array;
        }
               
        catch (Exception e){
            System.out.println(e);
            System.out.println("Failed to select Files");
            return null;
            
        }
	
	}
	

	//Upload des Pfades des Hochgeladenen Dokumentes
	public static int updateDB(String filename, String username) {
		
		Connection conn = DBconnection.connect(null);
		String path = username+"/"+filename;
		
        try{
            PreparedStatement insert = conn.prepareStatement("INSERT INTO Documents (username, filename, path) VALUES ('"+username+"','"+filename+"','"+path+"')");
            insert.executeUpdate();
            return 1;

        }catch (Exception e){
            System.out.println(e);
            System.out.println("failed to insert data");
            return 0;
        }finally {
        	try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }
	
	}
	

}
