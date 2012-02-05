package edu.dhbw.andar.sample;
import java.net.*;
import java.io.*;


public class HttpHelper {
	URL urlGet;
	URL urlSet;

	public HttpHelper(String urlString){ //http://192.168.1.2/server/
		try {
			urlGet = new URL(urlString+"data.txt");
			urlSet = new URL(urlString+"set.php?data=-1");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
public int getContent(){
//192.168.1.2
int res=-1;
	try {
		//urlGet = new URL("http://192.168.1.2/server/data.txt");
	
    URLConnection conn = urlGet.openConnection();
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
public void setNull(){
	//192.168.1.2

		try {
			//urlSet = new URL("http://192.168.1.2/server/set.php?data=-1");
		//"http://localhost/server/set.php?data=" + current
	    URLConnection conn = urlSet.openConnection();
	    // Get the response
	    BufferedReader rd = new BufferedReader(new 
	InputStreamReader(conn.getInputStream()));
	    String line = "";
	    while ((line = rd.readLine()) != null) {
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
		
		
	}
}
