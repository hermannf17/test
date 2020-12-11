package client;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

import server.DBinit;

public class httpclient {
    public static void main(String[] args) {
    	
    	Gui.createGUI();

    }
    

    //Post Request für das Login    
    public static int postRequestLogin(String url, HttpClient client, String username, String password) {
    	int statusCode = 0;
    	try {
    	HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .POST(HttpRequest.BodyPublishers.ofString(username+"&"+password))
                .build();
        /* wait for response */
        HttpResponse<String> response;
		
		response = client.send(request, HttpResponse.BodyHandlers.ofString());
			
		System.out.println(response.statusCode());
	        
	    statusCode = response.statusCode();
	    
    	}catch (Exception e) {
			e.printStackTrace();
			
		}

    	return statusCode;
        
    }
    
    
    
    // Post Request für den Download
    public static void postRequestDownload(String url, HttpClient client, String username, String filename, String dest, String newFilename) {
        //HttpClient client = HttpClient.newHttpClient();
    	try {
    	System.out.println("---- BEGIN ----");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))                
                .POST(HttpRequest.BodyPublishers.ofString(username+"&"+filename))
                .setHeader("download", filename)
                .build();
        
        /* wait for response */
        HttpResponse<String> response;
		
		response = client.send(request, HttpResponse.BodyHandlers.ofString());
		FileWriter myW = new FileWriter(dest + "/" +newFilename);
	    myW.write(response.body());
	    myW.close();
	        
	    System.out.println(response.body());
	    System.out.println("---- END ----");
		} catch (Exception e) {
			e.printStackTrace();
		}

    }
    
    
    
    
    //POST Request um alle Files zu bekommen
    public static String[] postRequestAllFiles(String url, HttpClient client, String username)  {
    	String[] str;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))                
                .POST(HttpRequest.BodyPublishers.ofString(username))
                .setHeader("all", username)
                .build();
               
        /* wait for response */
        HttpResponse<String> response;
		try {
			response = client.send(request,
			        HttpResponse.BodyHandlers.ofString());
			
			str = response.body().split("\r\n");
	        
	        for(String str1 : str) {
	        	System.out.println(str1);
	        }
	        
	        return str;
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			return null;
		}
    
    }
    
    
    //POST Request um ein File hochzuladen!
    public static void postRequestUpload(String url, HttpClient client, String username, String filepath) {
        
    	try {
    		File file = new File(filepath);
        	String filename = file.getName();
        	System.out.println("---- BEGIN ----");
        	
            String content = new String ( Files.readAllBytes( Paths.get(filepath) ) );
            
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))                
                    .POST(HttpRequest.BodyPublishers.ofString(username+"&"+filename + "\r\n" + content ))
                    .setHeader("upload", username)
                    .build();
            
            /* wait for response */
            
            HttpResponse<String> response;
    		
    		response = client.send(request, HttpResponse.BodyHandlers.ofString());
    			
    		System.out.println(response.statusCode());
            
    	}catch(Exception e) {
    		e.printStackTrace();
    		
    	}
    	
    }
    
    
    public static void getRequest(String url, HttpClient client)  {
        try {
        	HttpRequest request = HttpRequest.newBuilder()
            		.uri(URI.create(url))
                    .GET()
                    .build();
            /* wait for response */
            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());

            System.out.println(response.body());
        }catch(Exception e) {
        	e.printStackTrace();
        }
    	
    }
    

}
