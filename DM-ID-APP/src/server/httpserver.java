package server;

import com.sun.net.httpserver.*;

import client.Requests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class httpserver implements HttpHandler {
	public static void main(String[] args) throws IOException {
	
		
		DBinit.createDB();
    	DBinit.createTable();
    	DBinit.createTableDocuments();
    	DBinit.insert();
    	
		HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
		server.createContext("/", new httpserver());
		server.setExecutor(null);
		server.start();
		System.out.println("Sever started");
		System.out.println("Server is listening on port 8000: ...");
		
	}

	
	@Override
	public void handle(HttpExchange httpExchange) throws IOException {
		OutputStream os = httpExchange.getResponseBody();
		BufferedReader reader = new BufferedReader(new InputStreamReader(httpExchange.getRequestBody()));
		Connection con = DBconnection.connect(null);
	
		System.out.println("---- BEGIN ----");
		if (httpExchange.getRequestMethod().equalsIgnoreCase("POST")) {

			if (httpExchange.getRequestHeaders().containsKey("download")) {
				String[] str = reader.readLine().split("&");
				System.out.println(str[0] + "/" + str[1]);
				Path path = Paths.get(str[0] + "/" + str[1]);
				byte[] data = Files.readAllBytes(path);

				System.out.println("Post received");
				String response = "Thx";
				httpExchange.sendResponseHeaders(200, data.length);

				os.write(data);
				os.close();

			} else if (httpExchange.getRequestHeaders().containsKey("upload")) {
				String[] str = reader.readLine().split("&");
				System.out.println("Speicherort des Files:");
				System.out.println(str[0] + "/" + str[1]);
				StringBuilder sb = new StringBuilder();
				
				String input;
				System.out.println();
				System.out.println("Input des Files:");
				
				while((input = reader.readLine()) != null) {
					System.out.println(input);
					sb.append(input);
					sb.append("\r\n");
			
				}
				FileWriter myW = new FileWriter(str[0] + "/" + str[1]);
		        myW.write(sb.toString());
		        myW.close();
		        
		        Requests.updateDB(str[1], str[0]);

				
				
				Path path = Paths.get(str[0] + "/" + str[1]);
				byte[] data = Files.readAllBytes(path);

				System.out.println();
				System.out.println("Post received");
				
				httpExchange.sendResponseHeaders(200, data.length);

				
				os.write(data);
				os.close();

			} else if (httpExchange.getRequestHeaders().containsKey("all")) {
					String username = reader.readLine();
					String[] allFiles = Requests.getAllFiles(username, con);
					
					
					StringBuilder sb = new StringBuilder();
					for(String str : allFiles) {
						sb.append(str);
						sb.append("\r\n");
					}
					String response = sb.toString();
					//byte[] data = Files.readAllBytes(response);
					
					System.out.println("Post received");
					//String response = "Test";
					// printRequestInfo(httpExchange);
					httpExchange.sendResponseHeaders(200, response.getBytes().length);// response code and length
					os.write(response.getBytes());
					os.close();
					
					
				}else {

				//System.out.println(reader.readLine());
				System.out.println("POST received");
				String[] str = reader.readLine().split("&");
				System.out.println("Username/Passwort");
				System.out.println(str[0] + "/" + str[1]);
				String response = "Unauthorized";
				int responseCode = 401;
				String username = str[0];
				String password = str[1];
				
				
				
				if(verifyLogin(username, password, con) == 1) {
				response = "Ok";
				responseCode = 200;
					
				}
				// printRequestInfo(httpExchange);
				httpExchange.sendResponseHeaders(responseCode, response.getBytes().length);// response code and length
				os.write(response.getBytes());
				os.close();
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
		}
		System.out.println("---- END ----");
	}

	
	public static int verifyLogin(String username, String password, Connection con) {
		try {
						
			Statement stm = con.createStatement();
			String sql = "select * from Users where username='"+username+"' and Password='"+password+"'";
			ResultSet rs = stm.executeQuery(sql);
			
			if(rs.next()) {
	
				System.out.println("Anmeldung erfolgreich");
				return 1;
				
			}else if(username.isEmpty() || password.isEmpty()) {
				//denied.setText("Bitte geben Sie Username und Password ein!");
				System.out.println("Anmeldung fehlgeschlagen");
				return 2;
			}
			else {
					//denied.setText("Username oder Password ist falsch!");
					System.out.println("Anmeldung fehlgeschlagen");
					return 3;
				}
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	
		
	}
	
	
}