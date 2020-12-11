package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DBinit {
	

	public static int createDB() {
		
		Statement stmt = null;
		Connection conn = DBconnection.connect("jdbc:mysql://localhost:3306");
		///?useTimezone=true&serverTimezone=UTC
		try {
			stmt = conn.createStatement();
			
			String sql = "CREATE DATABASE IF NOT EXISTS DocumentManagementDB";
			stmt.executeUpdate(sql);
			return 1;
			
		}catch (Exception e){
			System.out.println();
			return 0;
		}
		
	}
	
	
	public static int createTable (){

		Connection conn = DBconnection.connect(null);
		
        try {
            PreparedStatement create = conn.prepareStatement("CREATE TABLE IF NOT EXISTS Users(username VARCHAR (50), password VARCHAR (150), PRIMARY KEY(username))");
            create.executeUpdate();
            return 1;
        }catch (Exception e){
            System.out.println(e);
            System.out.println("Failed to create table Users");
            return 0;
        }

    }
	
	public static int createTableDocuments (){

		Connection conn = DBconnection.connect(null);
		
        try {
            PreparedStatement create = conn.prepareStatement("CREATE TABLE IF NOT EXISTS Documents(id int NOT NULL AUTO_INCREMENT, username VARCHAR (50), filename VARCHAR (100), path VARCHAR (100), PRIMARY KEY(id))");
            create.executeUpdate();
            return 1;
        }catch (Exception e){
            System.out.println(e);
            System.out.println("Failed to create table Documents");
            return 0;
        }

    }
	
	
	public static int insert(){

		Connection conn = DBconnection.connect(null);
		
        String username = "Irem";
        String password = "test2";
        String username2 = "Daniel";
        String password2 = "test99";

        try{
            PreparedStatement insert = conn.prepareStatement("INSERT INTO Users (username, password) VALUES ('"+username+"','"+password+"')");
            PreparedStatement insert2 = conn.prepareStatement("INSERT INTO Users (username, password) VALUES ('"+username2+"','"+password2+"')");
            //executedUpdated manipulated information or add information, executeQuery only receiving information,
            insert.executeUpdate();
            insert2.executeUpdate();
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
