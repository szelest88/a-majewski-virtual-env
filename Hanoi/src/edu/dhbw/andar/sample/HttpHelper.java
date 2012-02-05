package edu.dhbw.andar.sample;
import java.net.*;
import java.io.*;


public class HttpHelper {
public static int getContent(){
//192.168.1.2
int res=-1;
	URL url;
	try {
		url = new URL("http://192.168.1.2/server/data.txt");
	
    URLConnection conn = url.openConnection();
    // Get the response
    BufferedReader rd = new BufferedReader(new 
InputStreamReader(conn.getInputStream()));
    String line = "";
    while ((line = rd.readLine()) != null) {
	res = Integer.parseInt(line);
    }
    
    
	} catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}catch (NumberFormatException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	return res;
}
public static void setNull(){
	//192.168.1.2
	int res=-1;
		URL url;
		try {
			url = new URL("http://192.168.1.2/server/set.php?data=-1");
		//"http://localhost/server/set.php?data=" + current
	    URLConnection conn = url.openConnection();
	    // Get the response
	    BufferedReader rd = new BufferedReader(new 
	InputStreamReader(conn.getInputStream()));
	    String line = "";
	    while ((line = rd.readLine()) != null) {
		res = Integer.parseInt(line);
	    }
	    
	    
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	//	return res;
	}
}
