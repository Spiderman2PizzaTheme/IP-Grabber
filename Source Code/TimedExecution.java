package ipchecker;

//Imports

import java.io.*;
import java.net.URL;

//Class

public class TimedExecution {
    
    public static void main (String[] args) throws Exception{
        TimedExecution.getIp();
    }
    
    /*Use AWS to get user's external IP address. AWS also displays just 
    the IP address on the website and nothing else, so this is the 
    easiest place to pull the info from.*/
    
    public static String getIp() throws Exception {
        URL whatismyip = new URL("http://checkip.amazonaws.com");
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(
                    whatismyip.openStream()));
            String ip = in.readLine();
            System.out.println(ip);
            return ip;
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    System.out.println("Error");
                }
            }
        }
    }
}